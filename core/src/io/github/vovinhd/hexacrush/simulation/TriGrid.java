package io.github.vovinhd.hexacrush.simulation;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import io.github.vovinhd.hexacrush.graphics.TileActor;
import io.github.vovinhd.hexacrush.graphics.TileActorFactory;

public class TriGrid extends Group {

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
                addActor(leftTA);
            }

            if (triPair.getRight() != null) {
                TileActor rightTA = TileActorFactory.generate(rightT, TriCoords.RIGHT);
                rightTA.setPosition(triPair.getRight().getX(), triPair.getRight().getY());
                addActor(rightTA);
            }
        }
    }

}
