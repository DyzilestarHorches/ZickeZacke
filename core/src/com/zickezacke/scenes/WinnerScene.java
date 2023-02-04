package com.zickezacke.scenes;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
/**
 * winner scene class is used to display when a player win and finish game scene
 */
public class WinnerScene extends GameWorld {
    /**
     * Constructor for SettingScene class
     *
     * @param has3DCamera - boolean - the scene has 3d camera
     * @param has2DCamera - boolean - the scene has 2d camera
     */
    public WinnerScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    /**
     * adds objects into scene
     */
    @Override
    public void Begin() {
        //adds background.
        gameObjects.add(new BackGround(901,"winner_background"));
        //adds  exit button.
        gameObjects.add(new FunctionalButton(902,"exit_btn",5,2,2,1));
    }

    /**
     * displays the updated winner portrait.
     */
    @Override
    public void Show() {
        //updates the portrait of the winner.
        String winner = "winner_" + String.valueOf(ZickeZacke.winner);
        FunctionalButton winnerPortrait = new FunctionalButton(903,winner,5,6,2,3);
        //adds the portrait to the scene.
        gameObjects.add(winnerPortrait);
        //starts the portrait.
        winnerPortrait.Start();
    }
}
