package com.zickezacke.scenes;


import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.Ground_1;
import com.zickezacke.gameObjectStore.GameScene.Obj;
import com.zickezacke.gameObjectStore.GameScene.Obj;
import com.zickezacke.gameObjectStore.GameScene.Obj;
import com.zickezacke.gameObjectStore.GameScene.card;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.gameObjectStore.testObject;


public class GameScene extends GameWorld {

    public GameScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera, has2DCamera);
    }
    public void Begin(){

        gameObjects3D.add(new Ground(101));


        gameObjects3D.add(new Ground(101));

    }
}
