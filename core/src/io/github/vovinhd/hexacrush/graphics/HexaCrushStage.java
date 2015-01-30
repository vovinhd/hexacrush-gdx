package io.github.vovinhd.hexacrush.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.vovinhd.hexacrush.service.AssetService;
import io.github.vovinhd.hexacrush.simulation.GameState;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by vovin on 28.01.15.
 */
public class HexaCrushStage extends Stage {

    private final Vector2 VERTICAL = new Vector2(0,1);
    private final Vector2 RISING = new Vector2(1,0).rotate(30);
    private final Vector2 FALLING = new Vector2(1,0).rotate(-30);
    //Services
    AssetService assetService = AssetService.getInstance();

    //UI elements
    private ImageButton menuButton;
    private ProgressBar timerBar;
    private Label score, scoreLabel;

    //Other scene objects
    private Sprite fieldBackground;
    private TileActor focused;
    private TileActor nullTile;

    private GameState gameState;
    private TriGrid triGrid;
    private Group focusedRow;

    //random junk
    private Vector2 touchDownAt;

    public HexaCrushStage(Viewport viewport, Batch batch) {
        super(viewport,batch);
        gameState = new GameState();

        Drawable menuDrawable = new TextureRegionDrawable(assetService.getTextureAtlas().findRegion("assets-menu"));
        Drawable menuDownDrawable = new TextureRegionDrawable(assetService.getTextureAtlas().findRegion("assets-menu-pressed"));
        menuButton = new ImageButton(menuDrawable,menuDownDrawable);
        menuButton.setPosition(0, Gdx.graphics.getHeight() - menuButton.getHeight());

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for(Actor a : triGrid.getChildren()) {
                    a.setVisible(true);
                }
            }
        });

        nullTile = new NullTileActor(); // NullObject
        focused = nullTile;

        triGrid = new TriGrid(gameState.getCoordinates());
        focusedRow = new Group();

        this.addActor(focusedRow);
        this.addActor(nullTile);
        this.addActor(triGrid);

        this.addActor(menuButton);
    }

    public void setGameOptions() {
        //TODO make Gamestate Configurable from the outside
        throw new NotImplementedException();
    }

    protected void select(Vector2 line, Vector2 origin) {
        double lineAngle = line.angle();
        Gdx.app.log(this.getClass().getSimpleName() + " lineAngle", Double.toString(lineAngle));
        selecFalling();
    }

    private void  selectColumn () {
        for(Actor t : triGrid.getChildren()) {
            if (t.getX() == focused.getX()) {
                focusedRow.addActor(t);
            }
        }
    }

    private void selectRising () {
        Gdx.app.log(this.getClass().getSimpleName() + " RISING", Double.toString(RISING.angle()));

        for(Actor t : triGrid.getChildren()) {
            Vector2 positionT = new Vector2(t.getX(), t.getY());
            Vector2 positionFocused = new Vector2(focused.getX(), focused.getY());
            Vector2 lineSegment = positionFocused.add(RISING);

            Vector2 conn = positionT.sub(positionFocused);
            Gdx.app.log(this.getClass().getSimpleName() + " conn ", conn.toString() + " angle: " + conn.angle() + " difference: " + (conn.angle() - RISING.angle()));


            Polygon bounds = new Polygon();
            bounds.setVertices(new float[] {t.getX(Align.bottomLeft), t.getY(Align.bottomLeft),
                                            t.getX(Align.bottomRight), t.getY(Align.bottomRight),
                                            t.getX(Align.topRight), t.getY(Align.topRight),
                                            t.getX(Align.topLeft), t.getY(Align.topRight)});
            if (Intersector.intersectLinePolygon(positionFocused, lineSegment, bounds)) {
                t.setVisible(false);
                focusedRow.addActor(t);
            }
        }
    }

    private void selecFalling () {
        Gdx.app.log(this.getClass().getCanonicalName(), "actor: " + focused.toString());

        if (focused instanceof NullTileActor) return;

        for (TileActor t : focused.falling) {
            Gdx.app.log(this.getClass().getCanonicalName(), "selected: " + t.toString());

            ScaleByAction zoomAction = Actions.scaleBy(1.2f,1.2f,0.1f);
            t.addAction(zoomAction);

            t.toFront();

        }
    }

    private enum Direction {
        POSITIVE,
        NEGATIVE
    }

    protected void focus(TileActor actor) {
        if (actor == focused){  // we acutally want to compare memory addresses
            actor.setScale(1, 1);
            focused = nullTile;
        } else {
            focused.setScale(1, 1);
            actor.toFront();
            Action zoomAction = Actions.sequence(Actions.scaleTo(1.2f,1.2f,0.1f), Actions.scaleTo(1f,1f));

            actor.addAction(zoomAction);
            focused = actor;
        }


    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 screenCoords = new Vector2(screenX,screenY);
        Vector2 stageCoords = this.screenToStageCoordinates(screenCoords);
        this.touchDownAt = screenCoords;
        Actor target = hit(stageCoords.x, stageCoords.y, true);
        if (target == null || !(target instanceof TileActor)) {
            return super.touchDown(screenX, screenY, pointer, button);
        } else {
            focus((TileActor) target);
            return true;
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(touchDownAt == null) return false;

        Vector2 origin = touchDownAt;
        Vector2 target = new Vector2(screenX,screenY);
        Vector2 line = origin.sub(target);

        select(origin,line);

        touchDownAt = null;
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public TileActor getFocused() {
        return focused;
    }

    public void setFocused(TileActor focused) {
        this.focused = focused;
    }
}