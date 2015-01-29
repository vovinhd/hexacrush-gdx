package io.github.vovinhd.hexacrush.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;


public class TileActor extends Actor {
    private Sprite tileSprite;
    private boolean flip = false;

    protected TileActor() { this.setVisible(false);};
    public TileActor(Sprite sprite) {
        this.tileSprite = new Sprite(sprite);
        this.setSize(tileSprite.getWidth(), tileSprite.getHeight());
        this.setOrigin(this.getWidth()/2, this.getHeight()/2);
        this.addListener(new TileActorGestureListener());
    }

    public TileActor(Sprite sprite, boolean flip) {
        this(sprite);
        this.flip = flip;
    }
/*
    @Override
    public void act(float delta) {
        Array<Action> actions = this.getActions();
        for(Action action : actions) {

            Actions actionType = Actions.valueOf(action.getClass().getSimpleName());
            switch (actionType) {
                case ScaleByAction:
                    float scaleX = (((ScaleByAction) action).getAmountX());
                    float scaleY = (((ScaleByAction) action).getAmountY());
                    this.setScale(scaleX,scaleY);
                    Gdx.app.log(getClass().getCanonicalName(), "Scale " + scaleX + " " + scaleY);

                    break;
                default: break;
            }

        }
        super.act(delta);
    }
*/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        //TODO put in more appropriate place, so it doesn't fire each frame even if the Actor isn't changed
        tileSprite.setScale(getScaleX(), getScaleY());
        tileSprite.setPosition(getX(), getY());
        tileSprite.draw(batch);
    }

    @Override
    public void setPosition(float x, float y) {
        tileSprite.setPosition(x, y);
        super.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        tileSprite.setSize(width, height);
        super.setSize(width, height);
    }

    public void flip() {
        flip = !flip;
        tileSprite.flip(flip, false);
    }

    public void focus() {
        ScaleByAction zoomAction = new ScaleByAction();
        zoomAction.setAmount(1.2f);
        zoomAction.setDuration(0.1f);
        addAction(zoomAction);
        Gdx.app.log(getClass().getCanonicalName(), getActions().toString());

    }


    enum Actions {
        ScaleByAction

    }

    private class TileActorGestureListener extends ActorGestureListener {

        @Override
        public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.app.log(getClass().getCanonicalName(), "touchDown at " + x + " " + y);
            focus();
        }

        @Override
        public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
            getStage();
            super.pan(event, x, y, deltaX, deltaY);
        }
    }


}
