package com.zickezacke.scenes;
/**
 * winner scene class is used to display when a player win and finish game scene
 */
import com.badlogic.gdx.Gdx;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class WinnerScene extends GameWorld {
    /**
     * Constructor for SettingScene class
     *
     * @param has3DCamera - boolean - the scene has 3d camera
     * @param has2DCamera - boolean - the scene has 2d camera
     *
     * @return WinnerScene - a scene for celebrating winner
     */
    public WinnerScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    /**
     * adds objects into scene
     */
    public void Begin() {
        //adds background
        gameObjects.add(new BackGround(901,"winner_background"));
        //adds winner portrait
        gameObjects.add(new FunctionalButton(902,"exit_btn",5,2,2,1));
    }

    @Override
    /**
     * displays updated winner portrait.
     */
    public void Show() {
        String winner = "winner_" + String.valueOf(ZickeZacke.winner);
        FunctionalButton winnerPortrait = new FunctionalButton(903,winner,5,6,2,3);
        gameObjects.add(winnerPortrait);
        winnerPortrait.Start();
    }

}
