package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject3D;

/**
 * The Tail class is a Tail for the Chicken
 *  is used to represent points in the main GameScene
 */
public class Tail extends GameObject3D {
    // manages the GameScene associated to the Tails
    private GameWorld thisGameScene;

    // constant used to control the number of Frames needed to perform a movement
    private static final  int ROTATE_FRAME = 30;

    // The player has the Tail
    private int playerNum;

    // get File to render the Tail
    private final int tailFile;

    // manages the Tile the Tail is on and the number of Tile shifted to get to the new destition
    private int tile;
    private int tileShift;

    // manages the number of Frames needed for a movement
    private int count = 0;

    // manages the state the Tail is on
    private boolean trigger = false;

    // manages the Chicken associated to
    private Chicken myChicken;

    /**
     * Constructor for the Tail class
     *
     * @param id - int - unique identifier for objects
     * @param tailFile - int - the file used to render
     * @param playerNum - int  - the PlayerNumber of the Chicken that has the Tail
     * @param tile - int - the index of the Tile that the Tail is on
     */
    public Tail(int id,int tailFile, int playerNum, int tile){
        super(id, false);
        this.tailFile = tailFile;
        this.playerNum = playerNum;
        this.tile = tile;
    }

    /**
     * sets the PlayerNumber to a new Chicken that will has the Tail
     *
     * @param playerNum - the PLayerNumber of the new Chicken
     */
    public void setPlayerNum(int playerNum) {this.playerNum = playerNum;}

    /**
     * gets the PlayerNumber of the Chicken that currently has the Tail
     *
     * @return - int - the PlayerNumber of the Chicken that has the Tail
     */
    public int getPlayerNum () {return this.playerNum;}

    @Override
    /**
     * overrides the objectStart method in parent class GameObject3D, for changes after object creation
     *  to rotate the Tail accordingly
     */
    public void objectStart() {
        model3D.setRotation(new Vector3(0,1,0),170f-(360f/24f)*tile);
    }

    @java.lang.Override
    /**
     * overrides the objectInit method in parent class GameObject3D, to make changes before create
     *  to change the file used to render and get some additional value
     */
    public void objectInit() {
        thisGameScene = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld();
        source3D = "./Chickens/tail_" + String.valueOf(tailFile) + ".g3db";
        // chicken 0 -> get(37)
        //tail 0 added
        // chicken 1 -> get(39)
        myChicken = (Chicken)(thisGameScene.getGameObjects3D().get(37+playerNum*2));
        if (myChicken != null){
            position3D = myChicken.getPosition3D();
        }
        scale3D = new Vector3(1,1,1);
    }

    @Override
    /**
     * overrides the objectUpdate of the parent class GameObject3D, to make changes to the Tail before rendering
     *  to update the Chicken and Rotation
     */
    public void objectUpdate() {
        // updates the current Chicken
        myChicken = (Chicken) (thisGameScene.getGameObjects3D().get(37+playerNum*2));
        if (myChicken != null){
            position3D = myChicken.getPosition3D();
        }

        // rotates the Tail according to the new Chicken
        rotateTail();

    }

    /**
     * rotates the Tail according to the number of Tile shifted
     */
    public void rotateTail(){
        if(this.trigger){
            count++;
            for(int i = 0; i < tileShift; i++){
                model3D.setRotation(new Vector3(0,1,0),-(360f/24f)/ROTATE_FRAME);
            }
            if(count==ROTATE_FRAME){
                count = 0;
                this.trigger = false;}
        }
    }

    /**
     * sets the Tile for the Tail is on,
     * calculates the number of Tile shifted to make Rotation accordingly
     *
     * @param nextTile - int - the Tile the Tail will be on
     */
    public void setTile(int nextTile){
        this.tileShift = (Math.abs(this.tile - nextTile)<24/2) ? Math.abs(this.tile - nextTile) : (24-Math.abs(this.tile - nextTile));
        this.tile = nextTile;
        this.trigger = true;
    }
}
