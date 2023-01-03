package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.Chicken1;
import com.zickezacke.gameObjectStore.GameScene.Chicken2;
import com.zickezacke.gameObjectStore.GameScene.Chicken3;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.MenuScene.Btn;
import com.zickezacke.gameObjectStore.MenuScene.playBtn;
import com.zickezacke.gameObjectStore.MenuScene.selectBtn0;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.gameObjectStore.testObject;


public class GameScene extends GameWorld {

    public GameScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera, has2DCamera);
    }

    public void Begin(){
        //gameObjects3D.add(new testObject(100));
        gameObjects3D.add(new Ground(101));
        gameObjects3D.add(new Chicken(105));
        gameObjects3D.add(new Chicken1(106));
        gameObjects3D.add(new Chicken2(107));
        gameObjects3D.add(new Chicken3(108));
        //Gdx.app.log("Number of GO3D", Integer.toString(gameObjects3D.size()));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
    }


}
