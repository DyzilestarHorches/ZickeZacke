package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.scenes.GameScene;

/**
 * PlayButton is used to direct user to game scene.
 */
public class PlayButton extends Button {
    //refer to Menu object in this game
    private GameWorld thisMenuScene;

    /**
     * constructor for play button
     *
     * @param id - int - unique identifier for object
     * @param type - String -  image file of button
     */
    public PlayButton(int id, String type){super(id, type);}

    @Override
    /**
     * initiates play button
     */
    public void objectInit() {
        super.objectInit();
        //initiates texture for button based on file path.
        position2D = new Vector2(5* CELL_WIDTH, 2* CELL_HEIGHT);
        //initiates size for button.
        size2D = new Vector2(2* CELL_WIDTH,1* CELL_HEIGHT);
    }

    @Override
    /**
     * updates menu object
     */
    public void objectUpdate() {
        thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
    }

    @Override
    /**
     * Clicks to play game, directs to GameScene
     */
    public void MouseDown(int x, int y, int pointer, int button) {
        //calls sound effect.
        ZickeZacke.getSoundSystem().cucTaCucTac();
        //checks for number of player.
        ZickeZacke.playerCount = 0;
        for(boolean i : ZickeZacke.playerList) {if(i){ZickeZacke.playerCount++;}}
        if(button == 0){
            //sends a notification about an invalid number of players.
            if(ZickeZacke.playerCount < 2){
                //activates notification banner.
                thisMenuScene.getGameObjects().get(8).setActive(true);
                thisMenuScene.getGameObjects().get(9).setActive(true);
            }else{
                //inputs valid number of players.
                //inactivates notification banner.
                thisMenuScene.getGameObjects().get(8).setActive(false);
                thisMenuScene.getGameObjects().get(9).setActive(false);
                ZickeZacke.inGame = 0;
                GameScene.isBuilt = false;
                //direct to GameScene
                ZickeZacke.getInstance().setScreen(0);
            }
        }
    }

}
