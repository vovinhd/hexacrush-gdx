package io.github.vovinhd.hexacrush.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.vovinhd.hexacrush.input.Directions;
import io.github.vovinhd.hexacrush.service.AssetService;
import io.github.vovinhd.hexacrush.simulation.GameState;

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

    //TODO remove this
    private ImageButton selectDirButton;
    private Directions[] dirs = new Directions[]{Directions.COLUMN, Directions.FALLING, Directions.RISING};
    private Directions dir;
    private Directions oldDir;
    private int dirPos = 0;

    //Other scene objects
    private Sprite fieldBackground;
    private TileActor focused;
    private TileActor nullTile;

    private GameState gameState;
    private TriGrid triGrid;
    private Group focusedGroup;
    private Array<TileActor> focusedRow;

    //random junk
    private Vector2 touchDownAt;

    public HexaCrushStage(Viewport viewport, Batch batch) {
        super(viewport,batch);
        gameState = new GameState();

        Drawable menuDrawable = new TextureRegionDrawable(assetService.getTextureAtlas().findRegion("assets-menu"));
        Drawable menuDownDrawable = new TextureRegionDrawable(assetService.getTextureAtlas().findRegion("assets-menu-pressed"));
        menuButton = new ImageButton(menuDrawable,menuDownDrawable);
        menuButton.setPosition(0, Gdx.graphics.getHeight() - menuButton.getHeight());

        dir = dirs[dirPos];
        selectDirButton = new ImageButton(menuDownDrawable);
        selectDirButton.setPosition(Gdx.graphics.getWidth() - selectDirButton.getWidth(), Gdx.graphics.getHeight() - selectDirButton.getHeight());
        selectDirButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dirPos = (dirPos + 1) % dirs.length;
                dir = dirs[dirPos];
                Gdx.app.log("DIR", dir.toString());
            }
        });

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for(Actor a : triGrid.getChildren()) {
                    a.setVisible(true);
                    a.setScale(1);
                }
            }
        });

        nullTile = new NullTileActor(); // NullObject
        focused = nullTile;

        triGrid = new TriGrid(gameState.getCoordinates());
        focusedGroup = new Group();

        this.addActor(focusedGroup);
        this.addActor(nullTile);
        this.addActor(triGrid);

        this.addActor(menuButton);
        this.addActor(selectDirButton);
    }

    public void setGameOptions() {
        //TODO make Gamestate Configurable from the outside
    }

    public void moveRow(Array<TileActor> focusedRow, Vector2 line, Directions direction) {
        for (TileActor t : focusedRow) {
            focusedGroup.addActor(t);
            focusedGroup.toFront();
        }

        Vector2 movement;
        switch (direction) {
            case COLUMN:
                movement = project(VERTICAL, line);
                break;
            case FALLING:
                movement = project(FALLING, line);
                break;
            case RISING:
                movement = project(RISING, line);
                break;
            default:
                movement = new Vector2(0, 0);
        }

        focusedGroup.setPosition(movement.x, movement.y);
    }

    private Vector2 project(Vector2 v, Vector2 u) {
        Vector2 vNorm = new Vector2(v).nor();
        u.dot(v);
        u.scl(vNorm);
        return u;
    }

    public TileActor getFocused() {
        return focused;
    }

    public void setFocused(TileActor focused) {
        this.focused = focused;
    }

}