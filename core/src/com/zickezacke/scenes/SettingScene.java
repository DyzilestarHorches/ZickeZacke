package com.zickezacke.scenes;


import com.zickezacke.gameObjectStore.MenuScene.Btn;
import com.zickezacke.gameObjectStore.MenuScene.backBtn;
import com.zickezacke.gameObjectStore.MenuScene.settingBackGround;
import com.zickezacke.gameObjectStore.demo.dragZone;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class SettingScene extends GameWorld {
    public SettingScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
        gameObjects.add(new settingBackGround(301, true));
        gameObjects.add(new backBtn(302, Btn.back_btn));
        gameObjects.add(new dragZone(303));

    }
}
