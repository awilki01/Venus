package com.paranoidalien.com.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.paranoidalien.com.Venus;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.useGL30 = false;
        config.width = 1280;
        config.height = 720;
        config.resizable = true;

		new LwjglApplication(new Venus(), config);
	}
}
