/**
 * PlayButton is used to direct user to game scene.
 */
package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.scenes.GameScene;


public class PlayButton extends Button {
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
        isActive = true;
        position2D = new Vector2(5* CELL_WIDTH, 2* CELL_HEIGHT);
        size2D = new Vector2(2* CELL_WIDTH,1* CELL_HEIGHT);
    }

    @Override
    /**
     * updates play button
     */
    public void objectUpdate() {
        thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
    }

    @Override
    /**
     * checks for number of player and sends notification to meet play requirement.
     */
    public void MouseDown(int x, int y, int pointer, int button) {
        ZickeZacke.getSoundSystem().cucTaCucTac();

        ZickeZacke.playerCount = 0;
        for(boolean i : ZickeZacke.playerList) {if(i){ZickeZacke.playerCount++;}}
        if(button == 0){
            if(ZickeZacke.playerCount < 2){
                thisMenuScene.getGameObjects().get(8).setActive(true);
                thisMenuScene.getGameObjects().get(9).setActive(true);
            }else{
                thisMenuScene.getGameObjects().get(8).setActive(false);
                thisMenuScene.getGameObjects().get(9).setActive(false);
                ZickeZacke.inGame = 0;
                GameScene.isBuilt = false;
                ZickeZacke.getInstance().setScreen(0);
            }
        }
    }
}
