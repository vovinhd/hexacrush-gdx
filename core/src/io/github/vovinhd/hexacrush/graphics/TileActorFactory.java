package io.github.vovinhd.hexacrush.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.vovinhd.hexacrush.service.AssetService;
import io.github.vovinhd.hexacrush.simulation.Tile;
import io.github.vovinhd.hexacrush.simulation.TriCoords;

public class TileActorFactory {

    private static TileActorFactory instance;

    private final TextureAtlas atlas;

    private final TextureRegion redTri;
    private final TextureRegion redTriFlop;
    private final TextureRegion yellowTri;
    private final TextureRegion yellowTriFlop;
    private final TextureRegion blueTri;
    private final TextureRegion blueTriFlop;
    private final TextureRegion blackTri;
    private final TextureRegion blackTriFlop;
    private final TextureRegion greenTri;
    private final TextureRegion greenTriFlop;
    private final TextureRegion purpleTri;
    private final TextureRegion purpleTriFlop;

    private TileActorFactory() {
        this.atlas = AssetService.getInstance().getTextureAtlas();
        this.redTri = atlas.findRegion("assets-tri-red");
        this.redTriFlop = atlas.findRegion("assets-tri-red-flop");
        this.yellowTri = atlas.findRegion("assets-tri-yellow");
        this.yellowTriFlop = atlas.findRegion("assets-tri-yellow-flop");
        this.blueTri = atlas.findRegion("assets-tri-blue");
        this.blueTriFlop = atlas.findRegion("assets-tri-blue-flop");
        this.blackTri = atlas.findRegion("assets-tri-black");
        this.blackTriFlop = atlas.findRegion("assets-tri-black-flop");
        this.greenTri = atlas.findRegion("assets-tri-green");
        this.greenTriFlop = atlas.findRegion("assets-tri-green-flop");
        this.purpleTri = atlas.findRegion("assets-tri-purple");
        this.purpleTriFlop = atlas.findRegion("assets-tri-purple-flop");
    }

    private static TileActorFactory getInstance() {
        if (instance == null)
            instance = new TileActorFactory();
        return instance;
    }

    public static TileActor generate(Tile t, int side) {

        TileActorFactory instance = getInstance();

        TileActor product; // TODO load different assets for different screen sizes

        if (side == TriCoords.LEFT) {
            switch (t) {
                case RED:
                    product = new TileActor(new Sprite(instance.redTriFlop));
                    break;
                case YELLOW:
                    product = new TileActor(new Sprite(instance.yellowTriFlop));
                    break;
                case BLUE:
                    product = new TileActor(new Sprite(instance.blueTriFlop));
                    break;
                case BLACK:
                    product = new TileActor(new Sprite(instance.blackTriFlop));
                    break;
                case GREEN:
                    product = new TileActor(new Sprite(instance.greenTriFlop));
                    break;
                case PURPLE:
                    product = new TileActor(new Sprite(instance.purpleTriFlop));
                    break;
                default:
                    return null;
            }
        } else {
            switch (t) {
                case RED:
                    product = new TileActor(new Sprite(instance.redTri));
                    break;
                case YELLOW:
                    product = new TileActor(new Sprite(instance.yellowTri));
                    break;
                case BLUE:
                    product = new TileActor(new Sprite(instance.blueTri));
                    break;
                case BLACK:
                    product = new TileActor(new Sprite(instance.blackTri));
                    break;
                case GREEN:
                    product = new TileActor(new Sprite(instance.greenTri));
                    break;
                case PURPLE:
                    product = new TileActor(new Sprite(instance.purpleTri));
                    break;
                default:
                    return null;
            }
        }

        float width = Gdx.graphics.getWidth()/10;
        float height = product.getHeight() * (width/product.getWidth());
        product.setSize(width,height);


        return product;
    }

}
