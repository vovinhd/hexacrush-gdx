package io.github.vovinhd.hexacrush.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class TileActor extends Actor {
    private Sprite tileSprite;
    private boolean flip = false;

    public TileActor(Sprite sprite) {
        this.tileSprite = new Sprite(sprite);
        this.setSize(tileSprite.getWidth(), tileSprite.getHeight());
    }

    public TileActor(Sprite sprite, boolean flip) {
        this(sprite);
        this.flip = flip;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

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


}
