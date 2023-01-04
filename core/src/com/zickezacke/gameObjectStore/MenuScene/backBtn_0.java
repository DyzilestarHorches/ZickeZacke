package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class backBtn_0 extends Btn {
    public backBtn_0(int id, String type){
        super(id,type);
        setActive(false);
    }

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2((float) 5.67*cellWidth,(float) 2.5*cellHeight);
        size2D = new Vector2(1*cellHeight,1*cellHeight);

    }

    @Override
    public void objectUpdate() {
        thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(button == 0) {
            thisMenuScene.getGameObjects().get(8).setActive(false);
            this.setActive(false);
        }
    }
}
