package io.github.vovinhd.hexacrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.vovinhd.hexacrush.graphics.HexaCrushStage;
import io.github.vovinhd.hexacrush.graphics.TileActor;
import io.github.vovinhd.hexacrush.graphics.TileActorFactory;
import io.github.vovinhd.hexacrush.graphics.TriGrid;
import io.github.vovinhd.hexacrush.input.HexaCrushStageTouchInputProcessor;
import io.github.vovinhd.hexacrush.simulation.GameState;
import io.github.vovinhd.hexacrush.simulation.Tile;
import io.github.vovinhd.hexacrush.simulation.TriCoords;

public class GameScreen extends ScreenAdapter {

    private Viewport viewport;
    private SpriteBatch batch;

    private GameState gameState;
    private TriGrid triGrid;
    private HexaCrushStage stage;
    private OrthographicCamera camera;

    @Override
    public void show() {
        camera = new OrthographicCamera(480,800);
        viewport = new ScreenViewport(camera);
        batch = new SpriteBatch();
        stage = new HexaCrushStage(viewport,batch);

        gameState = new GameState();
        HexaCrushStageTouchInputProcessor hip = new HexaCrushStageTouchInputProcessor(stage);

        //Setup Screen/UI



        //fill field


        Gdx.input.setInputProcessor(hip);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

}
