package com.zickezacke.scenes;


import com.zickezacke.gameObjectStore.MenuScene.DirectButton;
import com.zickezacke.gameObjectStore.MenuScene.settingBackGround;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class SettingScene extends GameWorld {
    public SettingScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
        gameObjects.add(new settingBackGround(301, true));
        gameObjects.add(new DirectButton(301, "back_btn",1,1,1));
    }
}
