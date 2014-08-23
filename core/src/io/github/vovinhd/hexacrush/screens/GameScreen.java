package io.github.vovinhd.hexacrush.screens;

import io.github.vovinhd.hexacrush.simulation.Simulation;
import io.github.vovinhd.hexacrush.simulation.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {

	
	private static final int TRI_SIZE = 70;
	private static final Vector2 TRI_FIELD_OFFSET = new Vector2(Gdx.graphics.getWidth() /2,Gdx.graphics.getHeight() /2);
	private Viewport viewport; 
	private SpriteBatch batch; 
	private Simulation simulation; 

	@Override
	public void render(float delta) {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		simulation.act(delta);
		simulation.draw();
		
		super.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void show() {
		viewport = new ScreenViewport(); 
		batch = new SpriteBatch(); 
		simulation = new Simulation(viewport, batch); 
				
		super.show();
	}

	

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	} 
	
	
	
}
