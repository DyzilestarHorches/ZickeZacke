package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.EggTiles;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.OctTiles;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends GameWorld {
    private Color[] colors = {Color.ORANGE, Color.RED, Color.GREEN, Color.BLUE};

    // determines if the game is in player's turn
    private boolean isRunning;
    // determines if one player have win
    private  boolean isEnd;
    private int currentPlayer;
    private int totalPlayer;

    private boolean isJumping = false;

    private int nextTile;

    private int currentTile;

    public String typeClicked = "-1";

    // list to manage player
    private List<Chicken> players = new ArrayList<>();

    // list to manage OctTile
    private List<OctTiles> octTiles = new ArrayList<>();

    // list to manage EggTile
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
        int distancing = (24 - totalPlayer) / totalPlayer + 1;
        for (int i = 1000; i < 1000+totalPlayer; i++) {
            Chicken chicken = new Chicken(i, eggTilePosition[(i - 1000)*distancing][0],
                    eggTilePosition[(i - 1000)*distancing][1],
                    eggTilePosition[(i - 1000)*distancing][2],
                    colors[i - 1000],
                    (i-1000)*distancing);

            players.add(chicken);
            gameObjects3D.add(chicken);

            eggTiles.get((i-1000)*distancing).setOccupy(true);

            chicken.Start();
        }
    }


    @Override
    public void worldUpdate() {

        if (isRunning) {
            currentTile = players.get(currentPlayer).getTile();
            nextTile = findUnOccupy();

            if (eggTiles.get(nextTile).getType().equals(typeClicked)) {
                setPosition();
                typeClicked = "-1";
                eggTiles.get(nextTile).setOccupy(true);

                eggTiles.get(currentTile).setOccupy(false);

                players.get(currentPlayer).setTile(nextTile);
                Gdx.app.log("Player tile" + Integer.toString(currentPlayer), Integer.toString(players.get(currentPlayer).getTile()));



            } else if (!eggTiles.get(nextTile).getType().equals(typeClicked) && typeClicked != "-1") {
                startNextPlayer();
                typeClicked = "-1";
            }


        } else {
            Gdx.app.log("End", "Winner " + Integer.toString(currentPlayer));
        }

        moveTail();
    }

    /**
     * ends current player turn and starts the next player turns
     */
    public void startNextPlayer() {
        currentPlayer = (currentPlayer + 1) % 4;
        System.out.println("Next player! " + currentPlayer);
    }

    public void randomizeTiles() {

    }

    public void setPosition() {
            players.get(currentPlayer).setDesPosition(eggTilePosition[nextTile][0],
                    eggTilePosition[nextTile][1],
                    eggTilePosition[nextTile][2]);

    }

    public int findUnOccupy() {
        int i = players.get(currentPlayer).getTile();
        for (int j = 1; j < 23; j++) {
            if (!eggTiles.get((i+j)%24).getOccupy()) {
                return (i+j)%24;
            }
        }
        return -1;
    }
}
