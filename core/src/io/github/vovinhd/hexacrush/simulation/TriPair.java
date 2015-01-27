package io.github.vovinhd.hexacrush.simulation;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by vovin on 27.01.15.
 */
public class TriPair {

    private TriCoords left;
    private TriCoords right;

    TriPair() {

    }

    TriPair(TriCoords left, TriCoords right) {
        this.left = left;
        this.right = right;
    }

    public TriCoords getLeft() {
        return left;
    }

    public void setLeft(TriCoords left) {
        this.left = left;
    }

    public TriCoords getRight() {
        return right;
    }

    public void setRight(TriCoords right) {
        this.right = right;
    }
}
