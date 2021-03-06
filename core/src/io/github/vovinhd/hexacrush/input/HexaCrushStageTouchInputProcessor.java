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

    private Directions dir;
    private Directions oldDir;

    private TileActor focused;
    private Array<TileActor> focusedRow;
    private Group focusedGroup;

    private Vector2 touchDownAt;

    private State state = State.FREE;


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
        if (actor == null) return;
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
            //Gdx.app.log(((Object) this).getClass().getCanonicalName(), "Focused pos in grid: " + focused.printLocation());

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
            state = State.ACTOR_SELECTED;
            return true;
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        //if (state != State.ACTOR_SELECTED) return false;

        Vector2 origin = touchDownAt;
        Vector2 target = parent.screenToStageCoordinates(new Vector2(screenX, screenY));
        Vector2 line = new Vector2(target).sub(origin); // submutates the original vector2 for no apparent reason other than madness

        if (line.len2() < 2) {
            //Gdx.app.log(((Object) this).getClass().getSimpleName(), "touch dragged for less than 20 units " + " line: " + line.toString() + " " + line.len2());
            return false;
        }

        //Gdx.app.log(((Object) this).getClass().getSimpleName(), "touch dragged at" + touchDownAt.toString() + " touch up at" + target + " line: " + line.toString());

        Array<TileActor> selectedNow = select(line, origin);
        //lock direction
        if (oldDir == null) {
            oldDir = dir;
        }

        //select row if this is the first time
        if (focusedRow == null) {
            focusedRow = selectedNow;
        }

        //lock selected row
        if (focusedRow == selectedNow) {
            parent.moveRow(selectedNow, line, oldDir);
        }
        state = State.ROW_SELECTED;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        state = State.FREE;
        oldDir = null;
        focusedRow = null;
        focus(null);
        return true;
    }


    protected Array<TileActor> select(Vector2 line, Vector2 origin) {

        double lineAngle = line.angle();
        Gdx.app.log(((Object) this).getClass().getSimpleName() + "Line: " + line.toString() + " lineAngle", Double.toString(lineAngle));
        dir = dirForAngle(lineAngle); // oh sideeffects
        switch (dir) {
            case COLUMN_NEG:
                return selectColumn();
            case COLUMN_POS:
                return selectColumn();
            case FALLING_NEG:
                return selectFalling();
            case FALLING_POS:
                return selectFalling();
            case RISING_NEG:
                return selectRising();
            case RISING_POS:
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
        Gdx.app.log(((Object) this).getClass().getCanonicalName(), "actor: " + focused.toString());

        if (focused instanceof NullTileActor || array == null) return null;

        return array;

    }

    public Directions dirForAngle(double dir) {
        if (120 >= dir && dir > 60){
            //Gdx.app.log(((Object) this).getClass().getCanonicalName(), "COLUMN " + dir);
            return Directions.COLUMN_POS;
        } else if (300 >= dir && dir > 240) {
            return Directions.COLUMN_NEG;
        } else if (180 >= dir && dir > 120) {
            //Gdx.app.log(((Object) this).getClass().getCanonicalName(), "FALLING " + dir);
            return Directions.FALLING_POS;
        }else if (360 >= dir && dir > 300) {
            return Directions.FALLING_NEG;
        } else if (60 >= dir && dir > 0) {
            return Directions.RISING_POS;
        } else if ( (240 >= dir && dir > 180)) {
            //Gdx.app.log(((Object) this).getClass().getCanonicalName(), "RISING " + dir);
            return Directions.RISING_NEG;
        } else {
            //Gdx.app.log(((Object) this).getClass().getCanonicalName(), "dirForAngleError " + dir);
            return Directions.COLUMN_POS;
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

    private enum State {
        FREE,
        ACTOR_SELECTED,
        ROW_SELECTED
    }


}
