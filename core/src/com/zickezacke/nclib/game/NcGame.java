package com.zickezacke.nclib.game;

import com.badlogic.gdx.Game;
import com.zickezacke.nclib.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class NcGame extends Game {
    protected List<GameScreen> gameScreens = new ArrayList<>();	//store all screens

    //singleton
    protected static NcGame instance = new NcGame();

    public static NcGame getInstance(){
        return instance;
    }

    public NcGame(){} //prevent new object

    @Override
    public void create () {

    }

    public void setScreen(int id) {
        instance.setScreen(gameScreens.get(id));
    }

    //getters
    public List<GameScreen> getGameScreens(){
        return instance.gameScreens;
    }
}
