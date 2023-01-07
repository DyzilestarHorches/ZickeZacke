package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class BackButton extends Button {
    public BackButton(int id, String type){
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
    public void objectLateUpdate() {

    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        Gdx.app.log("isActive", Boolean.toString(isActive));

        if (!isActive) return;
        if(button == 0) {
            thisMenuScene.getGameObjects().get(8).setActive(false);
            this.setActive(false);
        }
    }
}