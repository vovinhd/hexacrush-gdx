package io.github.vovinhd.hexacrush.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by vovin on 28.01.15.
 */
public class NullTileActor extends TileActor {

    public NullTileActor() {
        setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    }

    @Override
    public void flip() {
    }

    @Override
    public void act(float delta) {
    }
}
