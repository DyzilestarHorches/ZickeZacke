package com.zickezacke.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.zickezacke.gameObjectStore.SoundSystem.SoundSystem;
import com.zickezacke.nclib.game.screens.GameScreen;
import com.zickezacke.scenes.GameScene;
import com.zickezacke.scenes.HowScene;
import com.zickezacke.scenes.MenuScene;
import com.zickezacke.scenes.SettingScene;
import com.zickezacke.scenes.WinnerScene;

import java.util.ArrayList;
import java.util.List;

public class ZickeZacke extends Game {
	private List<GameScreen> gameScreens = new ArrayList<>();	//store all screens

	//singleton
	private static ZickeZacke instance = new ZickeZacke();

	private static final SoundSystem soundSystem = new SoundSystem();

	public static SoundSystem getSoundSystem() {
		return soundSystem;
	}

	public static ZickeZacke getInstance(){
		return instance;
	}

	public ZickeZacke(){} //prevent new object

	public static boolean[] playerList;
	public static int playerCount;
	public static int winner;
	public static float brightnessVol = 1f;
	public static boolean isCloud;
	public static int ingame = 1;


	@Override
	public void create () {
		soundSystem.SoundInIt();

		instance.gameScreens.add(new GameScreen(0, new GameScene(true, true)));	//default game screen is 0
		instance.gameScreens.add(new GameScreen(1, new MenuScene(false, true)));
		instance.gameScreens.add(new GameScreen(2, new HowScene(false,true)));
		instance.gameScreens.add(new GameScreen(3, new SettingScene(false,true)));
		instance.gameScreens.add(new GameScreen(4, new WinnerScene(false,true)));

		instance.setScreen(gameScreens.get(1));
	}

	public void setScreen(int id) {
		for (GameScreen gameScreen: gameScreens) {
			if (gameScreen.getId() == id){
				instance.setScreen(gameScreen);
			}
		}
	}

	public List<GameScreen> getGameScreens(){
		return this.gameScreens;
	}

	//region support
	private static int waitFrameCount = 0;
	public static boolean waitFrame(int numFrames){
		if (waitFrameCount < numFrames) {
			waitFrameCount++;
			return false;
		}
		waitFrameCount = 0;
		return true;
	}
	//endregion
}
