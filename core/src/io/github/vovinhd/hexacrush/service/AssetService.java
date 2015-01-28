package io.github.vovinhd.hexacrush.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetService {

    private static AssetService instance;
    private TextureAtlas textureAtlas;
    private Skin uiSikn;

    private AssetService() {
        textureAtlas = new TextureAtlas(Gdx.files.internal("assets.atlas"));
    }

    public static AssetService getInstance() {
        if (instance == null) {
            instance = new AssetService();
            return instance;
        } else {
            return instance;
        }
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public Skin getUiSikn() {
        return uiSikn;
    }
}
