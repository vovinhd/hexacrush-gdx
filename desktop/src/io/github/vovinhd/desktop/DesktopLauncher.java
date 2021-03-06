package io.github.vovinhd.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.vovinhd.hexacrush.HexaCrush;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 720;
        config.height = 1280;

        config.samples = 4;
        new LwjglApplication(new HexaCrush(), config);
	}
}
