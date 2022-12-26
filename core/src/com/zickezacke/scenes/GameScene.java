package com.zickezacke.scenes;

import com.zickezacke.nclib.screens.helpers.GameWorld;
import com.zickezacke.gameObjectStore.testObject;


public class GameScene extends GameWorld {
    public GameScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera, has2DCamera);
    }
    public void Begin(){
        gameObjects3D.add(new testObject(101));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
    }
}
