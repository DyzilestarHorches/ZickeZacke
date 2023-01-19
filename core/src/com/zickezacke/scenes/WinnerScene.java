package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class WinnerScene extends GameWorld {
    public WinnerScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
        gameObjects.add(new BackGround(901,"winner_background"));
        gameObjects.add(new FunctionalButton(902,"exit_btn",5,2,2,1));
    }

    @Override
    public void Show() {
        String winner = "winner_" + String.valueOf(ZickeZacke.winner);
        FunctionalButton winnerPortrait = new FunctionalButton(903,winner,5,6,2,3);
        gameObjects.add(winnerPortrait);
        winnerPortrait.Start();
    }

}
