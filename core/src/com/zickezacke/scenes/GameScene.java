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
//        float a = 1.5f;
        float a = 1.2f;

        //gameObjects3D.add(new testObject(100));
        //gameObjects3D.add(new Ground(101));
        gameObjects3D.add(new Ground_1(102));

        /*for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                gameObjects3D.add(new Obj(104,(float)(i*a),(float)(-j*a)));
                gameObjects3D.add(new Obj(105,(float)(i*a),(float)(-j*a)));
                gameObjects3D.add(new Obj(106,(float)(i*a),(float)(-j*a)));
                gameObjects3D.add(new Obj(107,(float)(i*a),(float)(-j*a)));
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                gameObjects3D.add(new card(104,(float)(i*a),(float)(-j*a)));
                gameObjects3D.add(new card(105,(float)(i*a),(float)(-j*a)));
                gameObjects3D.add(new card(106,(float)(i*a),(float)(-j*a)));
                gameObjects3D.add(new card(107,(float)(i*a),(float)(-j*a)));
            }
        }*/
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                gameObjects3D.add(new card(104,(float)(i*a),(float)(-j*a)));
                gameObjects3D.add(new card(105,(float)(-i*a),(float)(-j*a)));
                gameObjects3D.add(new card(106,(float)(-i*a),(float)(j*a)));
                gameObjects3D.add(new card(107,(float)(i*a),(float)(j*a)));
            }
        }




    }
}
