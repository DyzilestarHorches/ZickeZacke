package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.OctTiles;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import sun.jvm.hotspot.gc.shared.Space;


public class GameScene extends GameWorld {
    private Color[] colors = {Color.ORANGE, Color.RED, Color.GREEN, Color.BLUE};

    private  int currentFrame = 0;
    // determines if the game is in player's turn
    private boolean isRunning;
    // determines if one player have win
    private  boolean isEnd;
    private int currentPlayer;
    private int totalPlayer;

    private List<Chicken> players = new ArrayList<>();
    private int[][] eggTilePosition = {{0, 0, 0},
                                        {0, 0, 1},
                                        {0, 0, 2},
                                        {0, 0, 3}};

    public GameScene(boolean has3DCamera, boolean has2DCamera)
    {
        super(has3DCamera, has2DCamera);
        isRunning = true;
        isEnd = false;
        currentPlayer = 0;
        totalPlayer = 4;
    }

    public void Begin() {
        gameObjects3D.add(new Ground(101));
        for (int i = 105; i < 109; i++) {
            Chicken chicken = new Chicken(i, eggTilePosition[i-105][0], eggTilePosition[i-105][1], eggTilePosition[i-105][2], colors[i-105]);
            players.add(chicken);
            gameObjects3D.add(chicken);
        }

        gameObjects3D.add(new OctTiles(106,0,0,2));



        //Gdx.app.log("Number of GO3D", Integer.toString(gameObjects3D.size()));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
    }


    @Override
    public void Update() {
        super.Update();
        currentFrame++;

        if (isEnd) {
            System.out.println("Winner winner chicken dinner");
        } else {
            if (isRunning) {
                if (currentFrame % 60 == 0) {
                    isRunning = false;
                } else if (currentFrame % 350 == 0) {
                    isEnd = true;
                }

                if (spaceKeyPressed()) {
                    getMovement();
                }


            } else {
                startNextPlayer();
            }
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

    public void randomizeTiles() {

    }

    public void getMovement() {
        players.get(currentPlayer+105).move();
    }

    public boolean spaceKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}
