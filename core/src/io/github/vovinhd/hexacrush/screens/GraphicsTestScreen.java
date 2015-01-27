package io.github.vovinhd.hexacrush.screens;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;

import io.github.vovinhd.hexacrush.graphics.TileActor;
import io.github.vovinhd.hexacrush.graphics.TileActorFactory;
import io.github.vovinhd.hexacrush.simulation.Tile;
import io.github.vovinhd.hexacrush.simulation.TriCoords;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.text.ParseException;

public class GraphicsTestScreen extends ScreenAdapter{

    static final float scaleFactor = 2.5f;

    //Housekeeping
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    private ShapeRenderer sr;



    TileActor size = TileActorFactory.generate(Tile.RED, TriCoords.RIGHT);

    TextButton applyButton, removeButton;
    Label labelGridSize, labelLStart, labelRStart, labelLCutOff, labelRCCutOff;
    TextField tfGridSize, tfLStart, tfRStart, tfLCutOff, tfRCCutOff;

    Table table;

    Array tris;

    int x_offset = 0;
    int y_offset = 0;
    int intersectHeight = (int) size.getHeight() / 2;

    Skin skin;

    private int distance = 90;

    @Override
    public void show() {
        viewport = new ScreenViewport();
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        sr = new ShapeRenderer();
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        BitmapFont defaultFont = skin.getFont("default-font");
        defaultFont.scale(scaleFactor);
        skin.remove("default-font", BitmapFont.class);
        skin.add("default-font", defaultFont, BitmapFont.class);
        table = new Table();
        tris = new Array<Actor>();
        applyButton = new TextButton("apply",skin.get(TextButton.TextButtonStyle.class));
        removeButton = new TextButton("remove", skin.get(TextButton.TextButtonStyle.class));
        removeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clearTris();
            }
        });
        applyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    int gridSize = Integer.parseInt(tfGridSize.getText());
                    int lStart = Integer.parseInt(tfLStart.getText());
                    int rStart = Integer.parseInt(tfRStart.getText());
                    int lCutOff = Integer.parseInt(tfLCutOff.getText());
                    int rCutOff = Integer.parseInt(tfRCCutOff.getText());

                    if(tris.size > 0) {
                        clearTris();
                    }

                    addTris(gridSize, lStart, rStart, lCutOff, rCutOff);
                } catch (NumberFormatException e) {
                    Gdx.app.log("Apply Button Listener", e.getMessage());
                }
            }

            ;
        });
//       applyButton.setSize(400,100);
//        applyButton.setCenterPosition(applyButton.getWidth()/2, applyButton.getHeight()/2);

        labelGridSize = new Label("Grid size", skin);
        labelLCutOff = new Label("l Cutoff", skin);
        labelRCCutOff = new Label("r Cutoff", skin);
        labelLStart = new Label("l Start", skin);
        labelRStart = new Label("r Start", skin);

        tfGridSize = new TextField("8", skin);
        tfLStart = new TextField("4", skin);
        tfRStart = new TextField("5", skin);
        tfLCutOff = new TextField("11", skin);
        tfRCCutOff = new TextField("12", skin);





        table.add(labelGridSize);
        table.add(tfGridSize);
        table.row();
        table.add(labelLStart);
        table.add(tfLStart);
        table.row();
        table.add(labelLCutOff);
        table.add(tfLCutOff);
        table.row();
        table.add(labelRStart);
        table.add(tfRStart);
        table.row();
        table.add(labelRCCutOff);
        table.add(tfRCCutOff);
        table.row();
        table.add(applyButton);
        table.add(removeButton);
        table.setPosition(Gdx.graphics.getWidth() / 2, 3 * Gdx.graphics.getHeight() / 4);

        stage.addActor(table);

        //add one of each in lower left corner




        /*
        for (Tile t : Tile.values()) {
            TileActor actor = TileActorFactory.generate(t, TriCoords.RIGHT);
            actor.setPosition(x_offset, y_offset);
            stage.addActor(actor);
            y_offset += actor.getHeight();
        }

        y_offset = 0;
        x_offset += size.getWidth();

        for (Tile t : Tile.values()) {
            TileActor actor = TileActorFactory.generate(t, TriCoords.LEFT);
            actor.setPosition(x_offset, y_offset);
            stage.addActor(actor);
            y_offset += actor.getHeight();
        }
        */
        //draw a intersecting strip of actors
        /*
        x_offset = (int) size.getWidth() * 3;
        y_offset = 0;


        for (int i = 0; i < 10; i++) {
            TileActor actor_l = TileActorFactory.generate(Tile.BLUE, TriCoords.LEFT);
            TileActor actor_r = TileActorFactory.generate(Tile.BLUE, TriCoords.RIGHT);
            actor_l.setPosition(x_offset, y_offset);
            actor_r.setPosition(x_offset, y_offset + intersectHeight);

            stage.addActor(actor_l);
            stage.addActor(actor_r);

            y_offset += size.getHeight();
        }

        x_offset = (int) size.getWidth() * 6;
        y_offset = 0;
        */

        Gdx.input.setInputProcessor(stage);
        super.show();
    }

    void addTris(int gridSize, int lStart, int rStart, int lCutOff, int rCutOff) {

        for (int j = 0; j < gridSize + 1; j++) {
            for (int i = 0; i < gridSize; i++) {
                y_offset += size.getHeight() / 2;
                x_offset += size.getWidth();

                if ( i + j >= lStart && i + j <= lCutOff) {
                    TileActor actor_l = TileActorFactory.generate(Tile.BLUE, TriCoords.LEFT);
                    actor_l.setPosition(x_offset, y_offset + intersectHeight);
                    stage.addActor(actor_l);
                    tris.add(actor_l);
                } /* else {
                    TileActor actor_l = TileActorFactory.generate(Tile.RED, TriCoords.LEFT);
                    actor_l.setPosition(x_offset, y_offset + intersectHeight);
                    stage.addActor(actor_l);
                    tris.add(actor_l);
                } */

                if ( i + j >= rStart && i + j <= rCutOff) {
                    TileActor actor_r = TileActorFactory.generate(Tile.BLUE, TriCoords.RIGHT);
                    actor_r.setPosition(x_offset, y_offset);
                    stage.addActor(actor_r);
                    tris.add(actor_r);
                } /*else {
                    TileActor actor_r = TileActorFactory.generate(Tile.RED, TriCoords.RIGHT);
                    actor_r.setPosition(x_offset, y_offset);
                    stage.addActor(actor_r);
                    tris.add(actor_r);
                } */


            }
            x_offset = 0;
            y_offset = j * (int) size.getHeight();
        }

        x_offset = 0;
        y_offset = 0;
    }

    void clearTris() {
        stage.getActors().removeAll(tris, true);
        tris.clear();
        x_offset = 0;
        y_offset = 0;
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        sr.setProjectionMatrix(batch.getProjectionMatrix());
        for (Actor a : stage.getActors()) {
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(new Color(1,0,0,0));
            sr.rect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
            sr.end();
        }

    }


}
