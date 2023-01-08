package com.zickezacke.scenes;


import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class SettingScene extends GameWorld {
    public SettingScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
        gameObjects.add(new BackGround(301, "setting_background"));
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.ingame));
    }

    @Override
    public void worldUpdate() {
        super.worldUpdate();
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.ingame));
    }
}
