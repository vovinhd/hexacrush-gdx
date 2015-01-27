package io.github.vovinhd.hexacrush;

import com.badlogic.gdx.Game;

import io.github.vovinhd.hexacrush.screens.GameScreen;
import io.github.vovinhd.hexacrush.screens.GraphicsTestScreen;

public class HexaCrush extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }

}
