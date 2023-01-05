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

    // list to manage player
    private List<Chicken> players = new ArrayList<>();

    // list to manage OctTile
    private List<OctTiles> octTiles = new ArrayList<>();

    private int[][] eggTilePosition = {{0, 0, 0},
                                        {0, 0, 1},
                                        {0, 0, 2},
                                        {0, 0, 3},
                                        {0, 0, 4},
                                        {0, 0, 5},
                                        {0, 0, 6},
                                        {0, 0, 7},
                                        {0, 0, 8},
                                        {0, 0, 9},
                                        {0, 0, 10},
                                        {0, 0, 11},
                                        {0, 0, 12},
                                        {0, 0, 13},
                                        {0, 0, 14},
                                        {0, 0, 15},
                                        {0, 0, 16},
                                        {0, 0, 17},
                                        {0, 0, 18},
                                        {0, 0, 19},
                                        {0, 0, 20},
                                        {0, 0, 21},
                                        {0, 0, 22},
                                        {0, 0, 23}};

    private int[][] octTilePosition = {{0, 0, 0},
                                        {0, 0, 1},
                                        {0, 0, 2},
                                        {0, 0, 3},
                                        {0, 0, 4},
                                        {0, 0, 5},
                                        {0, 0, 6},
                                        {0, 0, 7},
                                        {0, 0, 8},
                                        {0, 0, 9},
                                        {0, 0, 10},
                                        {0, 0, 11}};

    public GameScene(boolean has3DCamera, boolean has2DCamera)
    {
        super(has3DCamera, has2DCamera);
        isRunning = true;
        isEnd = false;
        currentPlayer = 0;
    }

    public void Begin() {


        gameObjects3D.add(new Ground(101));

        totalPlayer = ZickeZacke.playerCount;
        int distancing = (24 - totalPlayer) / totalPlayer;
        for (int i = 1000; i < 1000+totalPlayer; i++) {
            Chicken chicken = new Chicken(i, eggTilePosition[(i - 1000)*distancing][0],
                                            eggTilePosition[(i - 1000)*distancing][1],
                                            eggTilePosition[(i - 1000)*distancing][2],
                                            colors[i - 1000]);

            players.add(chicken);
            gameObjects3D.add(chicken);

        }

        for (int i = 0; i < 12; i++) {
            OctTiles octTile = new OctTiles(i+2000,octTilePosition[i][0],
                                                        octTilePosition[i][1],
                                                        octTilePosition[i][2]);

            gameObjects3D.add(octTile);
            octTiles.add(octTile);
        }

        //Gdx.app.log("Number of GO3D", Integer.toString(gameObjects3D.size()));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
    }


    @Override
    public void Update() {
        super.Update();
        totalPlayer = ZickeZacke.playerCount;

        currentFrame++;

        if (isEnd) {
            //System.out.println("Winner winner chicken dinner");
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
        players.get(currentPlayer).move();
    }

    public boolean spaceKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}
