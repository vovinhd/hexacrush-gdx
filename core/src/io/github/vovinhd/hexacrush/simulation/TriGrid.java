package io.github.vovinhd.hexacrush.simulation;

import com.badlogic.gdx.utils.Array;

import io.github.vovinhd.hexacrush.graphics.TileActor;
import io.github.vovinhd.hexacrush.graphics.TileActorFactory;

public class TriGrid {

    int gridSize;
    Array<TileActor> tris = new Array<TileActor>();

    public TriGrid(CoordinateGrid coords) {
        int gridSize = coords.getGridSize();
        int triHeight = coords.getTriSideLength();
        int triWidth = triHeight * (int) Math.sin(Math.toRadians(60));
        for (TriPair triPair : coords.getPositions()) {
            Tile leftT = Tile.random();
            Tile rightT = Tile.random();
            if (triPair.getLeft() != null) {
                TileActor leftTA = TileActorFactory.generate(leftT, TriCoords.LEFT);
                leftTA.setPosition(triPair.getLeft().getX(), triPair.getLeft().getY());
                tris.add(leftTA);
            }

            if (triPair.getRight() != null) {
                TileActor rightTA = TileActorFactory.generate(rightT, TriCoords.RIGHT);
                rightTA.setPosition(triPair.getRight().getX(), triPair.getRight().getY());
                tris.add(rightTA);
            }
        }
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public Array<TileActor> getTris() {
        return tris;
    }

    public void setTris(Array<TileActor> tris) {
        this.tris = tris;
    }
}
