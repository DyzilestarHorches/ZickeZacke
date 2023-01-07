package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.EggTiles;
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

    // list to manage OctTile
    private List<EggTiles> eggTiles = new ArrayList<>();

    private  float eggOffset = 1.3f;
    private float[][] eggTilePosition = {{4f*eggOffset, 0, 0f},
                                        {3.75f*eggOffset, 0, 1f*eggOffset},
                                        {3.5f*eggOffset, 0, 2f*eggOffset},
                                        {3f*eggOffset, 0, 3f*eggOffset},
                                        {2f*eggOffset, 0, 3.5f*eggOffset},
                                        {1f*eggOffset, 0, 3.75f*eggOffset},

                                        {0, 0, 4f*eggOffset},
                                        {-1f*eggOffset, 0, 3.75f*eggOffset},
                                        {-2f*eggOffset, 0, 3.5f*eggOffset},
                                        {-3f*eggOffset, 0, 3f*eggOffset},
                                        {-3.5f*eggOffset, 0, 2f*eggOffset},
                                        {-3.75f*eggOffset, 0, 1f*eggOffset},

                                        {-4f*eggOffset, 0, 0},
                                        {-3.75f*eggOffset, 0, -1f*eggOffset},
                                        {-3.5f*eggOffset, 0, -2f*eggOffset},
                                        {-3f*eggOffset, 0, -3f*eggOffset},
                                        {-2f*eggOffset, 0, -3.5f*eggOffset},
                                        {-1f*eggOffset, 0, -3.75f*eggOffset},

                                        {0, 0, -4f*eggOffset},
                                        {1f*eggOffset, 0, -3.75f*eggOffset},
                                        {2f*eggOffset, 0, -3.5f*eggOffset},
                                        {3f*eggOffset, 0, -3f*eggOffset},
                                        {3.5f*eggOffset, 0, -2f*eggOffset},
                                        {3.75f*eggOffset, 0, -1f*eggOffset}};
    float octOffset = 1.3f;
    private float[][] octTilePosition = {{0.5f*octOffset, 0, 0.5f*octOffset},
                                        {1.5f*octOffset, 0, 0.5f*octOffset},
                                        {0.5f*octOffset, 0, 1.5f*octOffset},
                                        {-0.5f*octOffset, 0, -0.5f*octOffset},
                                        {-1.5f*octOffset, 0, -0.5f*octOffset},
                                        {-0.5f*octOffset, 0, -1.5f*octOffset},
                                        {0.5f*octOffset, 0, -0.5f*octOffset},
                                        {1.5f*octOffset, 0, -0.5f*octOffset},
                                        {0.5f*octOffset, 0, -1.5f*octOffset},
                                        {-0.5f*octOffset, 0, 0.5f*octOffset},
                                        {-1.5f*octOffset, 0, 0.5f*octOffset},
                                        {-0.5f*octOffset, 0, 1.5f*octOffset}
    };

    public GameScene(boolean has3DCamera, boolean has2DCamera)
    {
        super(has3DCamera, has2DCamera);
        isRunning = true;
        isEnd = false;
        currentPlayer = 0;
    }

    public void Begin() {


        gameObjects3D.add(new Ground(101));

        for (int i = 0; i < 24; i++) {
            EggTiles eggTile = new EggTiles(i+3000, (int)(i/2),eggTilePosition[i][0],
                                                        eggTilePosition[i][1],
                                                        eggTilePosition[i][2], 270f+(360f/24f)*i);

            gameObjects3D.add(eggTile);
            eggTiles.add(eggTile);
        }

        for (int i = 0; i < 12; i++) {
             OctTiles octTile = new OctTiles(i+2000,i,octTilePosition[i][0],
                                                          octTilePosition[i][1],
                                                          octTilePosition[i][2]);

            gameObjects3D.add(octTile);
            octTiles.add(octTile);
        }

        //Gdx.app.log("Number of GO3D", Integer.toString(gameObjects3D.size()));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
    }

    public void Show() {
        totalPlayer = ZickeZacke.playerCount;
        int distancing = (24 - totalPlayer) / totalPlayer;
        for (int i = 1000; i < 1000+totalPlayer; i++) {
            Chicken chicken = new Chicken(i, eggTilePosition[(i - 1000)*distancing][0],
                    eggTilePosition[(i - 1000)*distancing][1],
                    eggTilePosition[(i - 1000)*distancing][2],
                    colors[i - 1000]);

            players.add(chicken);
            gameObjects3D.add(chicken);

            chicken.Start();
        }
    }


    @Override
    public void worldUpdate() {
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
