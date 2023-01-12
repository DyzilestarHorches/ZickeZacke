package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.scenes.GameScene;


public class PlayButton extends Button {
    public PlayButton(int id, String type){super(id, type);}
    GameWorld thisMenuScene;
    @Override
    public void objectInit() {
        super.objectInit();
        isActive = true;
        position2D = new Vector2(5*cellWidth, 2*cellHeight);
        size2D = new Vector2(2*cellWidth,1*cellHeight);
    }

    @Override
    public void objectUpdate() {
        thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        ZickeZacke.getSoundSystem().click();

        ZickeZacke.getSoundSystem().playBackgroundMusicOnLoop();

        ZickeZacke.getSoundSystem().setIsCucTaCucTac(false);



        ZickeZacke.playerCount = 0;
        for(boolean i : ZickeZacke.playerList) {if(i){ZickeZacke.playerCount++;}}
        if(button == 0){
            if(ZickeZacke.playerCount < 2){
                thisMenuScene.getGameObjects().get(8).setActive(true);
                thisMenuScene.getGameObjects().get(9).setActive(true);
            }else{
                thisMenuScene.getGameObjects().get(8).setActive(false);
                thisMenuScene.getGameObjects().get(9).setActive(false);
                ZickeZacke.ingame = 0;
                GameScene.isBuilt = false;
                ZickeZacke.getInstance().setScreen(0);
            }
        }
    }
}
