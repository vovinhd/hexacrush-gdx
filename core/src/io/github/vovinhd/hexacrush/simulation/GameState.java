package io.github.vovinhd.hexacrush.simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import io.github.vovinhd.hexacrush.graphics.TileActor;
import io.github.vovinhd.hexacrush.graphics.TileActorFactory;

public class GameState {

    private CoordinateGrid coordinates;
    private float timePerTurn = 15.0f;
    private int score = 0;
    private int turns = 0;
    private float time = 0.0f;
    private boolean paused = false;

    public GameState() {
        TileActor ref = TileActorFactory.generate(Tile.random(), TriCoords.LEFT);
        int gridSize = 8;
        this.coordinates = new CoordinateGrid((int) ref.getWidth(),
                                              (int) ref.getHeight(),
                                              triGridPosition(),
                                              gridSize);
    }


    Vector2 triGridPosition() {
        Vector2 vec = new Vector2();

        int h = Gdx.graphics.getHeight();
        int w = Gdx.graphics.getWidth();

        vec.x = w / 2;
        vec.y = h / 2;
        return vec;
    }


    public CoordinateGrid getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinateGrid coordinates) {
        this.coordinates = coordinates;
    }

    public float getTimePerTurn() {
        return timePerTurn;
    }

    public void setTimePerTurn(float timePerTurn) {
        this.timePerTurn = timePerTurn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


}
