package io.github.vovinhd.hexacrush.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import io.github.vovinhd.hexacrush.graphics.TileActor;
import io.github.vovinhd.hexacrush.graphics.TileActorFactory;
import io.github.vovinhd.hexacrush.simulation.CoordinateGrid;
import io.github.vovinhd.hexacrush.simulation.Tile;
import io.github.vovinhd.hexacrush.simulation.TriCoords;
import io.github.vovinhd.hexacrush.simulation.TriPair;

public class TriGrid extends Group {


    private Array<Array<Array<TileActor>>> grid;
    private Array<Array<TileActor>> columns;
    private Array<Array<TileActor>> rising;
    private Array<Array<TileActor>> falling;

    int gridSize;

    public TriGrid(CoordinateGrid coords) {
        gridSize = coords.getGridSize();

        columns = initRow(gridSize + 1);
        rising = initRow(gridSize + 1);
        falling = initRow(gridSize + 1);

        grid = new Array<Array<Array<TileActor>>>(3);
        grid.add(columns);
        grid.add(rising);
        grid.add(falling);


        int triHeight = coords.getTriSideLength();
        int triWidth = triHeight * (int) Math.sin(Math.toRadians(60));
        for (TriPair triPair : coords.getPositions()) {
            Tile leftT = Tile.random();
            Tile rightT = Tile.random();
            TriCoords triL = triPair.getLeft();
            TriCoords triR = triPair.getRight();
            if (triPair.getLeft() != null) {
                addTileActor(triPair.getxCoord(), triPair.getyCoord(), triPair.getLeft());
            }

            if (triPair.getRight() != null) {
                addTileActor(triPair.getxCoord(), triPair.getyCoord(), triPair.getRight());
            }
        }
    }

    private void addTileActor(int x, int y, TriCoords coords) {



        TileActor actor = TileActorFactory.generate(Tile.random(), coords.getSide());
        actor.setPosition(coords.getX(), coords.getY());
        addActor(actor);
        columns.get(x).add(actor);
        rising.get(y).add(actor);

        actor.column = columns.get(x);
        actor.rising =  rising.get(y);

        if (coords.getSide() == TriCoords.LEFT) {
            int z = x + y -4;
            falling.get(z).add(actor);
            actor.falling =  falling.get(z);
            Gdx.app.log(this.getClass().getSimpleName(), "x: " + x + " y: " + y + " z: " + z);

        } else {
            int z = x + y - 5;
            falling.get(z).add(actor);
            actor.falling =  falling.get(z);
            Gdx.app.log(this.getClass().getSimpleName(), "x: " + x + " y: " + y + " z: " + z);

        }


    }

    private static Array<Array<TileActor>> initRow(int cap) {
        Array<Array<TileActor>> row = new Array<Array<TileActor>>(cap);
        for (int i = 0; i < cap; i++) {
            row.add(new Array<TileActor>());
        }
        return row;
    }

}
