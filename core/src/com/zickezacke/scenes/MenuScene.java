package com.zickezacke.scenes;

import com.zickezacke.gameObjectStore.MenuScene.backGround;
import com.zickezacke.gameObjectStore.MenuScene.logo;
import com.zickezacke.nclib.screens.helpers.GameWorld;

public class MenuScene extends GameWorld {

    public MenuScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    public void Begin(){
        //gameObjects.add(new backGround(102));
        gameObjects.add(new backGround(101, true));
        gameObjects.add(new logo(102, true));
    }
}
