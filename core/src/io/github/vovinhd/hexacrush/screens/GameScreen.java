package io.github.vovinhd.hexacrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.vovinhd.hexacrush.graphics.HexaCrushStage;
import io.github.vovinhd.hexacrush.graphics.TileActor;
import io.github.vovinhd.hexacrush.graphics.TileActorFactory;
import io.github.vovinhd.hexacrush.simulation.GameState;
import io.github.vovinhd.hexacrush.simulation.Tile;
import io.github.vovinhd.hexacrush.simulation.TriCoords;
import io.github.vovinhd.hexacrush.simulation.TriGrid;

public class GameScreen extends ScreenAdapter {

    private Viewport viewport;
    private SpriteBatch batch;

    private GameState gameState;
    private TriGrid triGrid;
    private HexaCrushStage stage;

    @Override
    public void show() {
        viewport = new ScreenViewport();
        batch = new SpriteBatch();
        stage = new HexaCrushStage(viewport,batch);

        //TODO decide which asset size to use
        TileActor ref =  TileActorFactory.generate(Tile.RED, TriCoords.LEFT);

        stage.addActor(ref);

        gameState = new GameState();

        //Setup Screen/UI



        //fill field


        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

}
