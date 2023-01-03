package com.zickezacke.scenes;

import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.OctTiles;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.gameObjectStore.testObject;


import java.io.Console;


public class GameScene extends GameWorld {

    private  int currentFrame = 0;
    // determines if the game is in player's turn
    private boolean isRunning;
    // determines if one player have win
    private  boolean isEnd;
    private int currentPlayer;
    private int totalPlayer;

    public GameScene(boolean has3DCamera, boolean has2DCamera)
    {
        super(has3DCamera, has2DCamera);
        isRunning = true;
        isEnd = false;
        currentPlayer = 0;
        totalPlayer = 4;
    }

    public void Begin(){
        //gameObjects3D.add(new testObject(100));
        gameObjects3D.add(new Ground(101));
        gameObjects3D.add(new Chicken(105));
        OctTiles num = new OctTiles(106);
        num.setPosition(0,0,1);
        gameObjects3D.add(num);

        //Gdx.app.log("Number of GO3D", Integer.toString(gameObjects3D.size()));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
    }


    @Override
    public void Update() {
        super.Update();
        currentFrame++;

        if (isRunning) {

        } else {
            startNextPlayer();
        }

        if (isEnd) {
            System.out.println("Winner winner chicken dinner");
        }
    }

    /**
     * ends current player turn and starts the next player turns
     */
    public void startNextPlayer() {
        currentPlayer = (currentPlayer + 1) % 4;
        System.out.println("Next player! " + currentPlayer);
        isRunning = true;
    }

}
