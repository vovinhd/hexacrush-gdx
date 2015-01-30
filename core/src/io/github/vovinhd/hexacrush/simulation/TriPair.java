package io.github.vovinhd.hexacrush.simulation;

/**
 * Created by vovin on 27.01.15.
 */
public class TriPair {

    private TriCoords left;
    private TriCoords right;

    private int xCoord;
    private int yCoord;

    TriPair() {}

    TriPair(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    TriPair(TriCoords left, TriCoords right, int x, int y) {
        this.left = left;
        this.right = right;
        this.xCoord = x;
        this.yCoord = y;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
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
