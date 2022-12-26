package com.zickezacke.scenes;

import com.zickezacke.gameObjectStore.backGround;
import com.zickezacke.nclib.screens.GameScreen;
import com.zickezacke.nclib.screens.helpers.GameWorld;

public class MenuScene extends GameWorld {
    public MenuScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    public void Begin(){
        gameObjects.add(new backGround(102));
    }
}
