package io.github.vovinhd.hexacrush.simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CoordinateGrid {

    private static final float cos45 = (float) Math.cos(Math.PI / 4);
    private static final float sin45 = (float) Math.sin(Math.PI / 4);

    private Array<TriPair> positions;
    private int triWidth;
    private int triHeight;
    private Vector2 offset;
    private int gridSize;

    public CoordinateGrid(int triWidth, int triHeight, Vector2 offset, int gridSize) {
        this.triWidth = triWidth;
        this.triHeight = triHeight;
        this.offset = offset;
        this.gridSize = gridSize;

        offsetToCenter();
        this.positions = recalculatePositions();
        Gdx.app.log("Coordi"
                + "nates", positions.toString());
    }

    private void offsetToCenter () {
        //height 6.5
        //width 4
        offset.x = offset.x - triWidth * ((gridSize/2) + 1);
        offset.y = offset.y - (float) (triHeight * (3 * gridSize/4) + 0.5 * triHeight);
    }

    private Array<TriPair> recalculatePositions() {
        Array<TriPair> positions = new Array<TriPair>();

        int xOffset = (int) offset.x;
        int yOffset = (int) offset.y;
        int intersectHeight = triHeight / 2;


        int lStart = gridSize / 2;
        int rStart = 1 + gridSize / 2;
        int lCutoff = 2 * gridSize - rStart;
        int rCutoff = 2 * gridSize - lStart;

        for (int i = 0; i < gridSize + 1; i++) {
            //fill current row
            for (int j = 0; j < gridSize; j++) {
                TriPair pair = new TriPair();

                xOffset += triWidth;
                yOffset += triHeight / 2;
                if (i + j >= lStart && i + j <= lCutoff) {
                    pair.setLeft(new TriCoords(xOffset, yOffset + intersectHeight, TriCoords.LEFT));
                }

                if (i + j >= rStart && i + j <= rCutoff) {
                    pair.setRight(new TriCoords(xOffset, yOffset, TriCoords.RIGHT));
                }
                positions.add(pair);
            }
            //shift row
            xOffset = (int) offset.x;
            yOffset = (int) offset.y + i * triHeight;
        }

        return positions;
    }

    public Array<TriPair> getPositions() {
        return positions;
    }

    public void setPositions(Array<TriPair> positions) {
        this.positions = positions;
    }

    public int getTriSideLength() {
        return triWidth;
    }

    public void setTriSideLength(int triSideLength) {
        this.triWidth = triSideLength;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

}
