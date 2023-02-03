
package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.gameObjectStore.GameScene.EggTiles;
import com.zickezacke.gameObjectStore.GameScene.Ground;
import com.zickezacke.gameObjectStore.GameScene.OctTiles;
import com.zickezacke.gameObjectStore.GameScene.Tail;
import com.zickezacke.gameObjectStore.UI.nextTurnNoti;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.gameObjectStore.UI.NotiBackground;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The GameScene class is the main game scene for the game, where all core logic and game mechanics are implemented
 */
public class GameScene extends GameWorld {
    // static final variables in the GameScene to control some animations
    private static final int WAIT_FRAME = 300;
    private static final int WAIT_NEXT_PLAYER_FRAME = 150;

    // determines if the game is in player's turn
    private boolean isRunning;

    // saves the current Player
    private int currentPlayer;

    // manages current Tile and the next Tile to jump to
    private int nextTile;
    private int currentTile;

    // manages the Oct Tile just clicked, reset upon clicking thus no looping
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

    //list to manage uiButton.
    //list of buttons in submenu.
    private List<FunctionalButton> buttons = new ArrayList<>();
    //camera button for switching camera modes.
    private FunctionalButton cameraButton;
    //background of submenu.
    private NotiBackground menuInGame;

    private final float eggOffset = 3f;
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

    private final float octOffset = 2.6f;
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

    /**
     * Constructor for the GameScene class
     *
     * @param has3DCamera - boolean - if the GameScene has a 3D camera or not
     * @param has2DCamera - boolean - if the GameScene has a 2D camera or not
     */
    public GameScene(boolean has3DCamera, boolean has2DCamera) {
        super(has3DCamera, has2DCamera);
        isRunning = true;
        currentPlayer = 0;
    }

    /**
     * overrides the Begin method in parent class GameWorld, to add objects
     */
    public void Begin() {
        // add the Ground to GameScene
        gameObjects3D.add(new Ground(101));

        //randomize the files of the eggTiles
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

        //randomize the position of the octTiles
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

        //create buttons for UI
        createUI();
    }

    /**
     * overrides the Show method in parent class GameScreen, to render the class before any scene
     *  to create more objects (when first start the game)
     */
    public void Show() {
        if (isBuilt) return;
        createChickenTailNoti();

        updateTilesForPLayer();
        isBuilt = true;
    }

    /**
     * creates Chickens and Tails based on number of player
     */
    public void createChickenTailNoti() {
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

    /**
     * creates UI for the GameScene
     */
    public void createUI(){
        //creates a submenu in game is inactive as default.
        menuInGame =new NotiBackground(9006,"menu_background");
        //adds menu button that activates submenu.
        gameObjects.add(new FunctionalButton(9005,"menu_btn",11,8,menuInGame));
        //adds how button that directs to game instruction scene.
        gameObjects.add(new FunctionalButton(9005,"how_btn",11,6.5,2));
        //creates camera button that allows user to switch different camera modes.
        cameraButton = new FunctionalButton(9005,"default_view_btn",11,5,-1,true);
        //adds camera.
        gameObjects.add(cameraButton);
        //adds submenu.
        gameObjects.add(menuInGame);

        //adds functional buttons into submenu button list.
        //adds resume button that inactivates submenu, back to game scene.
        buttons.add(new FunctionalButton(9006,"resume_btn",5,4.5,2,1,menuInGame));
        //adds setting button that directs to setting scene.
        buttons.add(new FunctionalButton(9006,"setting0_btn",5,3,2,1,3));
        //adds exit button that allows user to exit game.
        buttons.add(new FunctionalButton(9006,"exit_btn",5,1.5,2,1));
        //set active status of buttons in submenu base on submenu status.
        for(FunctionalButton i : buttons){
            //adds list of button into scene.
            gameObjects.add(i);
            i.setActive(menuInGame.isActive());
        }
    }

    @Override
    /**
     * overrides the worldUpdate method in parent class GameWorld, to implement the core logic of the game
     */
    public void worldUpdate() {
        //updates active status of buttons in submenu base on submenu status.
        for(FunctionalButton i : buttons){i.setActive(menuInGame.isActive());}
        //updates camera view for 'tile' mode in camera button.
        cameraButton.setVector3(eggTilePosition[nextTile]);
        // the core Game Mechanic
        // State 1: The Game is still running
        if (isRunning) {
            // State 1.1: The Player's turn
            if (eggTiles.get(nextTile).getType().equals(octTileFileClicked)) {
                // Checks to see if the Player gains new Tail, then check if the game ends
                checkGainLoseTail();
                ZickeZacke.getSoundSystem().cucTaCucTac();
                moveChicken();
                updateTilesForPLayer();

                resetTypeChecked();
                OctTiles.setIsClickable(true);

                Gdx.app.log("Player tile" + currentPlayer, Integer.toString(players.get(currentPlayer).getTile()));

            }
            // State 1.2: Handing the turn to the next Player
            else if (!eggTiles.get(nextTile).getType().equals(octTileFileClicked) && !Objects.equals(octTileFileClicked, "-1") && ZickeZacke.waitFrame(WAIT_NEXT_PLAYER_FRAME))
            {
                ZickeZacke.getSoundSystem().nextTurn();
                startNextPlayer();

                OctTiles.setIsClickable(true);
                resetTypeChecked();
            }

        }
        // State 2: The game is ending
        else {
            if (isEnd && ZickeZacke.waitFrame(WAIT_FRAME)) {
                ZickeZacke.getSoundSystem().applause();
                ending();
            }
        }
    }

    /**
     * updates the current Tile and next Tile for PLayer, to use in core logic
     */
    public void updateTilesForPLayer() {
        currentTile = players.get(currentPlayer).getTile();
        nextTile = findUnOccupy();
    }

    /**
     * resets typeChecked variable of the oct Tile clicked, prevent looping in Core Logic
     */
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

    /**
     * ends the game
     */
    public void ending() {
        Gdx.app.log("End", "Winner " + currentPlayer);
        ZickeZacke.winner = players.get(currentPlayer).getPlayerFile();
        ZickeZacke.getInstance().setScreen(4);
        isEnd = false;
    }

    /**
     * moves the Chicken
     */
    public void moveChicken() {
        players.get(currentPlayer).setDesPosition(eggTilePosition[nextTile][0],
                eggTilePosition[nextTile][1],
                eggTilePosition[nextTile][2]);

        eggTiles.get(nextTile).setOccupy(true);

        eggTiles.get(currentTile).setOccupy(false);

        players.get(currentPlayer).setTile(nextTile);

        rotateTails();
    }

    /**
     * rotates the tails
     */
    public void rotateTails(){
        for(Tail t : tails){
            if(currentPlayer == t.getPlayerNum()){t.setTile(nextTile);}
        }
    }

    /**
     * finds the next unoccupied Tile
     *
     * @return - int - the next unoccupied Tile
     */
    public int findUnOccupy() {
        for (int j = 1; j < 23; j++) {
            if (!eggTiles.get((currentTile+j)%24).getOccupy()) {
                return (currentTile+j)%24;
            }
        }
        return -1;
    }

    /**
     * checks if the game ends
     */
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

    /**
     * checks if the current PLayer gains or loses Tail
     */
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
