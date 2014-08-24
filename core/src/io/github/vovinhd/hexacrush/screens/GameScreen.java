package io.github.vovinhd.hexacrush.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {

	
	private Stage stage;
	private Viewport viewport; 
	private SpriteBatch batch; 

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
	}

	@Override
	public void show() {
		viewport = new ScreenViewport(); 
		batch = new SpriteBatch(); 
		stage = new Stage(viewport, batch); 
		super.show();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		batch.dispose();
		simulation.dispose();
		super.dispose();
	} 
	
	
	
}
