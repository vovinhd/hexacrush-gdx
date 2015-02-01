package io.github.vovinhd.hexacrush.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import io.github.vovinhd.hexacrush.graphics.HexaCrushStage;
import io.github.vovinhd.hexacrush.graphics.NullTileActor;
import io.github.vovinhd.hexacrush.graphics.TileActor;

/**
 * Created by timo on 01.02.2015.
 */
public class HexaCrushStageTouchInputProcessor implements InputProcessor {

    private HexaCrushStage parent;

    private Directions[] dirs = new Directions[]{Directions.COLUMN, Directions.FALLING, Directions.RISING};
    private Directions dir;
    private Directions oldDir;
    private int dirPos = 0;

    private TileActor focused;
    private Array<TileActor> focusedRow;
    private Group focusedGroup;

    private Vector2 touchDownAt;


    public HexaCrushStageTouchInputProcessor(HexaCrushStage parent) {
        this.parent = parent;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    public void focus(TileActor actor) {
        if (actor == focused) {  // we acutally want to compare memory addresses
            actor.setScale(1, 1);
            focused = null;
        } else {

            if (focused != null) {
                focused.setScale(1, 1);
            }

            focused = actor;

            actor.toFront();
            Action zoomAction = Actions.sequence(Actions.scaleTo(1.2f, 1.2f, 0.1f), Actions.scaleTo(1f, 1f));

            actor.addAction(zoomAction);
            focused = actor;
            Gdx.app.log(getClass().getCanonicalName(), "Focused pos in grid: " + focused.printLocation());

        }


    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 screenCoords = new Vector2(screenX, screenY);
        Vector2 stageCoords = parent.screenToStageCoordinates(screenCoords); //mutates params; Madness
        touchDownAt = stageCoords;
        Actor target = parent.hit(stageCoords.x, stageCoords.y, true);
        if (target == null || !(target instanceof TileActor)) {
            focus(null);
            return true;
        } else {
            focus((TileActor) target);
            return true;
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if (touchDownAt == null || focused == null) return false;


        Vector2 origin = touchDownAt;
        Vector2 target = parent.screenToStageCoordinates(new Vector2(screenX, screenY));
        Vector2 line = new Vector2(target).sub(origin); // submutates the original vector2 for no apparent reason other than madness

        if (line.len2() < 20) {
            Gdx.app.log(getClass().getSimpleName(), "touch dragged for less than 20 units " + " line: " + line.toString() + " " + line.len2());

            return false;
        }

        Gdx.app.log(getClass().getSimpleName(), "touch dragged at" + touchDownAt.toString() + " touch up at" + target + " line: " + line.toString());

        Array<TileActor> selectedNow = select(line, origin);
        if (oldDir == null) {
            oldDir = dir;
        }
        if (focusedRow != null) {
            parent.moveRow(focusedRow, line, oldDir);
        } else {
            focusedRow = selectedNow;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        /*
        if(touchDownAt == null) return false;


        Vector2 origin = touchDownAt;
        Vector2 target = screenToStageCoordinates(new Vector2(screenX, screenY));
        Vector2 line = new Vector2(target).sub(origin); // submutates the original vector2 for no apparent reason other than madness

        Gdx.app.log(getClass().getSimpleName(), "touch down at " + touchDownAt.toString() + " touch up at" + target + " line: " + line.toString());

        select(line, origin);

        touchDownAt = null;
        */
        return false;
    }


    protected Array<TileActor> select(Vector2 line, Vector2 origin) {

        double lineAngle = line.angle();
        Gdx.app.log(this.getClass().getSimpleName() + "Line: " + line.toString() + " lineAngle", Double.toString(lineAngle));
        dir = dirForAngle(lineAngle); // oh sideeffects
        Array<TileActor> array;
        switch (dir) {
            case COLUMN:
                return selectColumn();
            case FALLING:
                return selectFalling();
            case RISING:
                return selectRising();
        }
        return null;
    }


    public Array<TileActor> selectFalling() {
        return selectRowFrom(focused.getFalling());
    }

    public Array<TileActor> selectRising() {
        return selectRowFrom(focused.getRising());
    }

    public Array<TileActor> selectColumn() {
        return selectRowFrom(focused.getColumn());
    }

    private Array<TileActor> selectRowFrom(Array<TileActor> array) {
        Gdx.app.log(this.getClass().getCanonicalName(), "actor: " + focused.toString());

        if (focused instanceof NullTileActor || array == null) return null;

        return array;

    }

    public Directions dirForAngle(double dir) {
        if ((120 >= dir && dir > 60) || (300 >= dir && dir > 240)) {
            Gdx.app.log(getClass().getCanonicalName(), "COLUMN " + dir);
            return Directions.COLUMN;
        } else if ((180 >= dir && dir > 120)
                || (360 >= dir && dir > 300)) {
            Gdx.app.log(getClass().getCanonicalName(), "FALLING " + dir);
            return Directions.FALLING;
        } else if ((60 >= dir && dir > 0) || (240 >= dir && dir > 180)) {
            Gdx.app.log(getClass().getCanonicalName(), "RISING " + dir);
            return Directions.RISING;
        } else {
            Gdx.app.log(getClass().getCanonicalName(), "dirForAngleError " + dir);
            return Directions.COLUMN;
        }
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
