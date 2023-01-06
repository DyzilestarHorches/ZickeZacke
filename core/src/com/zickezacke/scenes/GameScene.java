package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.EggTiles;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.OctTiles;
import com.zickezacke.gameObjectStore.GameScene.Tail;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameScene extends GameWorld {
    private final Color[] colors = {Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW};

    // determines if the game is in player's turn
    private boolean isRunning;

    private int currentPlayer;

    // manages current Tile and the next Tile to jump to
    private int nextTile;
    private int currentTile;

    private int tileChecked;

    public String typeClicked = "-1";

    // signals ending
    private boolean isEnd = false;

    // list to manage player
    private List<Chicken> players = new ArrayList<>();

    // list to manage tails
    private  List<Tail> tails = new ArrayList<>();

    // list to manage OctTile
    private List<OctTiles> octTiles = new ArrayList<>();

    // list to manage EggTile
    private List<EggTiles> eggTiles = new ArrayList<>();

    private final float eggOffset = 1.3f;
    private final float[][] eggTilePosition = {{4f*eggOffset, 0, 0f},
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

    private final float octOffset = 1.3f;
    private final float[][] octTilePosition = {{0.5f*octOffset, 0, 0.5f*octOffset},
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
        createChickenTail();
        updateTilesForPLayer();
    }

    // creates Chickens and Tails based on number of player
    public void createChickenTail() {
        int distancing = (24 - ZickeZacke.playerCount) / ZickeZacke.playerCount + 1;
        for (int i = 1000; i < 1000+ ZickeZacke.playerCount; i++) {
            Chicken chicken = new Chicken(i, eggTilePosition[(i - 1000)*distancing][0],
                    eggTilePosition[(i - 1000)*distancing][1],
                    eggTilePosition[(i - 1000)*distancing][2],
                    colors[i - 1000],
                    (i-1000)*distancing,i-1000, 1);

            //add chickens
            players.add(chicken);
            gameObjects3D.add(chicken);
            chicken.Start();

            //set Tile to occupy
            eggTiles.get((i-1000)*distancing).setOccupy(true);

            //add tails
            Tail tmpTail = new Tail(i + 10, i-1000, i-1000);
            tails.add(tmpTail);
            gameObjects3D.add(tmpTail);
            tmpTail.Start();
        }
    }

    @Override
    public void worldUpdate() {
        if (isRunning) {
            if (eggTiles.get(nextTile).getType().equals(typeClicked)) {
                moveChicken();
                resetTypeChecked();

                Gdx.app.log("Player tile" + currentPlayer, Integer.toString(players.get(currentPlayer).getTile()));

            } else if (!eggTiles.get(nextTile).getType().equals(typeClicked) && !Objects.equals(typeClicked, "-1")) {
                startNextPlayer();
                resetTypeChecked();
            }

        } else {
            if (isEnd) {
                ending();
            }

        }
    }

    public void updateTilesForPLayer() {
        currentTile = players.get(currentPlayer).getTile();
        nextTile = findUnOccupy();
    }

    // resets typeChecked variable
    public void resetTypeChecked() {
        typeClicked = "-1";
    }

    /**
     * ends current player turn and starts the next player turns
     */
    public void startNextPlayer() {


        currentPlayer = (currentPlayer + 1) % ZickeZacke.playerCount;
        updateTilesForPLayer();
        System.out.println("Next player! " + currentPlayer);
    }

    public void randomizeTiles() {

    }

    // start Ending sequence
    public void ending() {
        Gdx.app.log("End", "Winner " + currentPlayer);
        isEnd = false;
    }

    // move the Chicken
    public void moveChicken() {
        players.get(currentPlayer).setDesPosition(eggTilePosition[nextTile][0],
                eggTilePosition[nextTile][1],
                eggTilePosition[nextTile][2]);

        eggTiles.get(nextTile).setOccupy(true);

        eggTiles.get(currentTile).setOccupy(false);

        players.get(currentPlayer).setTile(nextTile);

        checkGainLoseTail();

        updateTilesForPLayer();
    }

    // finds the next unoccupied Tile
    public int findUnOccupy() {
        for (int j = 1; j < 23; j++) {
            if (!eggTiles.get((currentTile+j)%24).getOccupy()) {
                return (currentTile+j)%24;
            }
        }
        return -1;
    }

    // checks if ends
    public void checkEnd() {
        for (Chicken c: players) {
            Gdx.app.log("Tail" + c.getPlayerNum(), Integer.toString(c.getTail()));
            if (c.getTail() == ZickeZacke.playerCount) {
                isRunning = false;
                isEnd = true;
                break;
            }
        }
    }

    // check if gains or loses Tail
    public void checkGainLoseTail() {
        if ((currentTile+1)%24 != nextTile) {
            // start checking from the next Tile
            tileChecked = (currentTile + 1)%24;

            // till the destination Tile
            while (tileChecked != nextTile) {

                // Run through each players
                for (Chicken c : players) {
                    // check if player on the Tile
                    if (c.getTile() == tileChecked) {
                        // lose and gain Tail
                        players.get(currentPlayer).gainTail(c.getTail());
                        c.loseTail();

                        // set Tail to the new Chicken
                        for (Tail t : tails) {
                            if (t.getPlayerNum() == c.getPlayerNum()) {
                                t.setPlayerNum(currentPlayer);
                            }
                        }
                    }
                }
                tileChecked = (tileChecked + 1)%24;
            }

            checkEnd();
        }
    }
}
