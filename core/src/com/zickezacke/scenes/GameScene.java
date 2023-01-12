
package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.EggTiles;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.GroundCloud;
import com.zickezacke.gameObjectStore.GameScene.OctTiles;
import com.zickezacke.gameObjectStore.GameScene.Tail;
import com.zickezacke.gameObjectStore.UI.nextTurnNoti;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.gameObjectStore.UI.NotiBackground;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.gameObject.GameObject3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class GameScene extends GameWorld {
    private static final int WAIT_FRAME = 300;

    // determines if the game is in player's turn
    private boolean isRunning;

    private int currentPlayer;

    // manages current Tile and the next Tile to jump to
    private int nextTile;
    private int currentTile;

    public String octTileFileClicked = "-1";

    // signals ending
    private boolean isEnd = false;

    // list to manage player
    private final List<Chicken> players = new ArrayList<>();

    // list to manage tails
    private final List<Tail> tails = new ArrayList<>();

    // list to manage OctTile
    private final List<OctTiles> octTiles = new ArrayList<>();

    // list to manage EggTile
    private final List<EggTiles> eggTiles = new ArrayList<>();

    //list noti
    private  List<nextTurnNoti> nextTurnNotis = new ArrayList<>();

    //list to manage uiButton
    private List<FunctionalButton> buttons = new ArrayList<>();
    private NotiBackground menuInGame;

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

    //check if the scene is built or not
    public static boolean isBuilt = true;

    public GameScene(boolean has3DCamera, boolean has2DCamera)
    {
        super(has3DCamera, has2DCamera);
        isRunning = true;
        currentPlayer = 0;
    }

    public void Begin() {
//        ZickeZacke.getSoundSystem().playBackgroundMusicOnLoop();

        gameObjects3D.add(new Ground(101));

        //randomize the position of the eggTiles
        List<Integer> randomEggTileFile = new ArrayList<>();

        for(int i = 0; i < 24; i++) {
            randomEggTileFile.add(i);
        }

        Collections.shuffle(randomEggTileFile);

        //creates 24 eggTiles
        for (int i = 0; i < 24; i++) {
            EggTiles eggTile = new EggTiles(i+3000, randomEggTileFile.get(i)/2, eggTilePosition[i][0],
                                                                    eggTilePosition[i][1],
                                                                    eggTilePosition[i][2], 270f+(360f/24f)*i);

            gameObjects3D.add(eggTile);
            eggTiles.add(eggTile);
        }

        //randomize the position of the eggTiles
        List<Integer> randomOctTilePosition = new ArrayList<>();

        for(int i = 0; i < 12; i++) {
            randomOctTilePosition.add(i);
        }

        Collections.shuffle(randomOctTilePosition);

        //creates 12 octTiles
        for (int i = 0; i < 12; i++) {
            OctTiles octTile = new OctTiles(i+2000, i, octTilePosition[randomOctTilePosition.get(i)][0],
                                                        octTilePosition[randomOctTilePosition.get(i)][1],
                                                        octTilePosition[randomOctTilePosition.get(i)][2]);

            gameObjects3D.add(octTile);
            octTiles.add(octTile);
        }

        //Gdx.app.log("Number of GO3D", Integer.toString(gameObjects3D.size()));
        //gameObjects.add(new backGround(102));
        //gameObjects.add(new backGround3D(103));
        createUI();
    }

    public void Show() {
        if (isBuilt) return;
        createChickenTailUI();

        updateTilesForPLayer();
        isBuilt = true;
    }

    // creates Chickens and Tails based on number of player
    public void createChickenTailUI() {
        //mapping playerList
        int[] chickenList = new int[ZickeZacke.playerCount];
        int j = 0;
        for (int i = 0; i < 4; i++) {
            if (ZickeZacke.playerList[i]) {
                chickenList[j] = i;
                j++;
            }
        }

        //calculates the spacing between chickens
        int distancing = (24 - ZickeZacke.playerCount) / ZickeZacke.playerCount + 1;

        //creates the chicken, tails, and UI
        for (int i = 1000; i < 1000 + ZickeZacke.playerCount; i++) {
            Chicken chicken = new Chicken(i, eggTilePosition[(i - 1000) * distancing][0],
                    eggTilePosition[(i - 1000) * distancing][1],
                    eggTilePosition[(i - 1000) * distancing][2],
                    (i - 1000) * distancing, i - 1000, chickenList[i - 1000], 1);

            //add chickens
            players.add(chicken);
            gameObjects3D.add(chicken);
            chicken.Start();

            //set Tile to occupy
            eggTiles.get((i - 1000) * distancing).setOccupy(true);

            //add tails
            Tail tmpTail = new Tail(i + 10, chickenList[i - 1000], i - 1000, (i - 1000) * distancing);
            tails.add(tmpTail);
            gameObjects3D.add(tmpTail);
            tmpTail.Start();

            nextTurnNoti noti = new nextTurnNoti(8000 + i, chickenList[i - 1000]);
            gameObjects.add(noti);
            nextTurnNotis.add(noti);
            noti.Start();
        }
    }
    public void createUI(){
        menuInGame =new NotiBackground(9006,"menu_background");
        Gdx.app.log("ddada",String.valueOf(menuInGame.isActive()));
        gameObjects.add(new FunctionalButton(9005,"menu_btn",11,8,menuInGame));
        gameObjects.add(new FunctionalButton(9005,"how_btn",11,6.5,2));
        gameObjects.add(new FunctionalButton(9005,"default_view_btn",11,5,-1));

        gameObjects.add(menuInGame);
        buttons.add(new FunctionalButton(9006,"resume_btn",5,4.5,2,1,menuInGame));
        buttons.add(new FunctionalButton(9006,"setting0_btn",5,3,2,1,3));
        buttons.add(new FunctionalButton(9006,"exit_btn",5,1.5,2,1));
        for(FunctionalButton i : buttons){
            gameObjects.add(i);
            i.setActive(menuInGame.isActive());
        }
    }

    @Override
    public void worldUpdate() {
        for(FunctionalButton i : buttons){i.setActive(menuInGame.isActive());}

        if (isRunning) {
            if (eggTiles.get(nextTile).getType().equals(octTileFileClicked)) {
                checkGainLoseTail();
                moveChicken();
                updateTilesForPLayer();

                resetTypeChecked();

                Gdx.app.log("Player tile" + currentPlayer, Integer.toString(players.get(currentPlayer).getTile()));

            } else if (!eggTiles.get(nextTile).getType().equals(octTileFileClicked) && !Objects.equals(octTileFileClicked, "-1")) {
                startNextPlayer();

                resetTypeChecked();
            }

        } else {
            if (isEnd && ZickeZacke.waitFrame(WAIT_FRAME)) {
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
        octTileFileClicked = "-1";
    }

    /**
     * ends current player turn and starts the next player turns
     */
    public void startNextPlayer() {
        currentPlayer = (currentPlayer + 1) % ZickeZacke.playerCount;
        nextTurnNotis.get(currentPlayer).setActive(true);
        updateTilesForPLayer();
        System.out.println("Next player! " + currentPlayer);
    }

    // start Ending sequence
    public void ending() {
        Gdx.app.log("End", "Winner " + currentPlayer);
        ZickeZacke.winner = players.get(currentPlayer).getPlayerFile();
        ZickeZacke.getInstance().setScreen(4);
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

        rotateTails();
    }

    //rotate the tails
    public void rotateTails(){
        for(Tail t : tails){
            if(currentPlayer == t.getPlayerNum()){t.setTile(nextTile);}
        }
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
            int tileChecked = (currentTile + 1) % 24;

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
