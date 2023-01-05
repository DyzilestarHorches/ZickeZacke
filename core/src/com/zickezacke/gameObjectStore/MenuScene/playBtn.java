package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;


public class playBtn extends Btn{
    public playBtn(int id, String type){super(id, type);}

    @Override
    public void objectInit() {
        super.objectInit();
        isActive = true;
        position2D = new Vector2(5*cellWidth, 2*cellHeight);
        size2D = new Vector2(2*cellWidth,1*cellHeight);
    }


    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        //thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
        ZickeZacke.playerCount = 0;
        for(boolean i : ZickeZacke.playerList) {if(i){ZickeZacke.playerCount++;}}
        if(button == 0){
            if(ZickeZacke.playerCount < 2){
                thisMenuScene.
                        getGameObjects().
                        get(8).setActive(true);
                thisMenuScene.getGameObjects().get(9).setActive(true);
            }else{
                thisMenuScene.getGameObjects().get(8).setActive(false);
                thisMenuScene.getGameObjects().get(9).setActive(false);
                ZickeZacke.getInstance().setScreen(0);}
        }

    }
}
