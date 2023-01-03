package com.zickezacke.scenes;

import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.OctTiles;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.gameObjectStore.testObject;

public class GameScene extends GameWorld {

    public GameScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera, has2DCamera);
    }

    public void Begin(){
        //gameObjects3D.add(new testObject(100));
        gameObjects3D.add(new Ground(101));
        gameObjects3D.add(new Chicken(105,0,0,0));
        gameObjects3D.add(new OctTiles(106,0,0,2));



        //Gdx.app.log("Number of GO3D", Integer.toString(gameObjects3D.size()));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
    }

}
