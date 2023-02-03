package com.zickezacke.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.zickezacke.game.ZickeZacke;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		//ZickeZacke game = new ZickeZacke();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("zickezacke");
		config.setResizable(false);
		//config.setWindowedMode(1920, 1080);
		//config.setWindowedMode(1280, 720);
		config.setWindowedMode(1600, 900);

		new Lwjgl3Application(ZickeZacke.getInstance(), config);
	}
}
