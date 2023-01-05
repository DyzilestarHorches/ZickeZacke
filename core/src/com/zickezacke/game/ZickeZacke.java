package com.zickezacke.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.zickezacke.nclib.game.screens.GameScreen;
import com.zickezacke.scenes.GameScene;
import com.zickezacke.scenes.HowScene;
import com.zickezacke.scenes.MenuScene;
import com.zickezacke.scenes.SettingScene;

import java.util.ArrayList;
import java.util.List;

public class ZickeZacke extends Game {
	private List<GameScreen> gameScreens = new ArrayList<>();	//store all screens


	//singleton
	private static ZickeZacke instance = new ZickeZacke();

	public static ZickeZacke getInstance(){
		return instance;
	}

	public ZickeZacke(){} //prevent new object
	public static boolean[] playerList;
	public static int playerCount = 0;

	@Override
	public void create () {
		instance.gameScreens.add(new GameScreen(0, new GameScene(true, true)));	//default game screen is 0
		instance.gameScreens.add(new GameScreen(1, new MenuScene(false, true)));
		instance.gameScreens.add(new GameScreen(2, new HowScene(false,true)));
		instance.gameScreens.add(new GameScreen(3, new SettingScene(false,true)));
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
}
