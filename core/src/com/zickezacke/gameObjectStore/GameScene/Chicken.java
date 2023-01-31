package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;

/**
 * The Chicken class is a Chicken for players
 *  is used in the main GameScene
 */
public class Chicken extends GameObject3D {
    // determines if the Chicken is jumping
    private boolean isJumping = false;

    // constants used to control the number of Frames needed to perform a movement
    private static final int JUMP_FRAME = 30;
    private static final float HEIGHT_SCALE = 0.1f;

    // variable to control the number of Frames needed to perform a movement
    private int count = 0;

    // the total number of Tail the Chicken currently has
    private int tail;

    // the Trigger state to initiate the movement of the Chicken
    private boolean Trigger = false;

    // the current Tile the Chicken is on
    private int tile;

    // the amount of Tile shifted, used to rotate the Chicken
    private int tileShift;

    // manages the Position of the Chicken, including the current Position and the destination Position
    private final float[] currentPos = new float[3];
    private final float[] desPos = new float[3];

    //the Chicken player number
    private final int playerNum;

    //file to render the chicken
    private final int playerFile;

    /**
     * Constructor for the Chicken class
     *
     * @param id - int - unique identifier for the object
     * @param x - float - x-coordinate of the Chicken's position
     * @param y - float - y-coordinate of the Chicken's position
     * @param z - float - z-coordinate of the Chicken's position
     * @param tile - int - the current Tile the Chicken is on
     * @param playerNum - the PlayerNumber of the Chicken
     * @param playerFile - the file used to render the Chicken
     * @param tail - the number of Tails the Chicken has
     */
    public Chicken(int id, float x, float y, float z
                   , int tile, int playerNum, int playerFile, int tail){

        super(id, true);
        setPosition(x,y,z);
        this.tile = tile;
        this.playerNum = playerNum;
        this.playerFile = playerFile;
        this.tail = tail;
    }

    /**
     * sets the Position of the Chicken to an assigned coordinate
     *
     * @param x - float - the assigned x-coordinate
     * @param y - float - the assigned y-coordinate
     * @param z - float - the assigned z-coordinate
     */
    public void setPosition(float x, float y, float z){
        position3D = new Vector3(x,y,z);
    }

    @java.lang.Override
    /**
     * overrides the objectInit method in parent class GameObject3D, to make changes before create
     */
    public void objectInit() {
        source3D = "./Chickens/chicken_" + String.valueOf(playerFile) + ".g3db";
        scale3D = new Vector3(1,1,1);
    }

    @java.lang.Override
    /**
     * overrides the objectStart method in parent class GameObject3D, for changes after object creation
     */
    public void objectStart(){
        //rotates object
        model3D.setRotation(new Vector3(0,1,0), 170f-(360f/24f)*tile);
    }

    /**
     * animates the default animation of the Chicken
     */
    public void animation(){
        String Default = model3D.animations.get(0).id;
        animation3D.setAnimation(Default, 10);
    }

    /**
     * gets the current Position of the Chicken
     */
    public void setCurrentPos() {
        currentPos[0] = position3D.x;
        currentPos[1] = position3D.y;
        currentPos[2] = position3D.z;
    }

    /**
     * sets the destination Position to move, turn the Chicken state into Triggered mode
     *
     * @param x - float - the new x-coordinate to move to
     * @param y - float - the new y-coordinate to move to
     * @param z - float - the new z-coordinate to move to
     */
    public void setDesPosition(float x, float y, float z){
        setCurrentPos();

        desPos[0] = x;
        desPos[1] = y;
        desPos[2] = z;

        Trigger = true;
        isJumping = true;
    }

    /**
     * moves the Chicken to the destination Position, start when the Chicken state is in Triggered mode
     */
    public void move() {
        // moves the Chicken when in Triggered state
        if (Trigger) {
            // counts the number of Frames passed to check the end of movement
            count++;

            // gets the new Position of the Chicken
            position3D.x += (desPos[0]-currentPos[0])/JUMP_FRAME;
            position3D.z += (desPos[2]-currentPos[2])/JUMP_FRAME;
            // calculates the new y-coordinate in the parabola shape
            position3D.y = getNewY();

            //rotates the Chicken accordingly
            rotateChicken();

            // deactivate the state to end to movement
            if (count == JUMP_FRAME) {
                Trigger = false;
                isJumping = false;
                count = 0;

            }
        }
    }

    /**
     * rotates the Chicken according to the number of Tile shifted
     */
    public void rotateChicken(){
        for(int i = 0; i < this.tileShift; i++){
            model3D.setRotation(new Vector3(0,1,0), -(360f/24f)/JUMP_FRAME);
        }
    }

    @java.lang.Override
    /**
     * overrides the objectUpdate method in parent class GameObject3D, to make changes before render
     */
    public void objectUpdate() {
        animation();

        if (isJumping) {
            move();
        }
    }

    /**
     * sets the new Tile the Chicken is on
     * calculates the according tileShift to rotate the Chicken
     *
     * @param tile - int - the new Tile the Chicken is on
     */
    public void setTile(int tile) {
        this.tileShift = (Math.abs(this.tile - tile)<24/2) ? Math.abs(this.tile - tile) : (24-Math.abs(this.tile - tile));
        this.tile = tile;
    }

    /**
     * gets the Tile the Chicken is currently on
     *
     * @return - int - the current Tile
     */
    public int getTile() { return this.tile;}

    /**
     * gets new y-position for the Chicken, to use in moving the Chicken
     *
     * @return - float- the new y-coordinate
     */
    private float getNewY() {
        float xStar = (float) Math.sqrt(Math.pow(desPos[0]-currentPos[0],2)+Math.pow(desPos[2]-currentPos[2],2));

        float xNew = -xStar + count*xStar/JUMP_FRAME;

        return (float) (HEIGHT_SCALE * ( -Math.pow(xNew, 2) - xStar * xNew));
    }

    /**
     * gets the number of Tails the Chicken has
     *
     * @return - int - the number of Tails
     */
    public int getTail() {return this.tail;}

    /**
     * loses all the Tails on the Chicken
     */
    public void loseTail() {this.tail = 0;}

    /**
     * gains a number of Tails to the number of Tails the Chicken currently has
     *
     * @param tailNumber - int - the number of Tails gained
     */
    public void gainTail(int tailNumber) {this.tail += tailNumber;}

    /**
     * gets the PlayerNumber of the Chicken, to use in Core Logic
     *
     * @return - int - the PlayerNumber of the Chicken
     */
    public int getPlayerNum() {return this.playerNum;}

    /**
     * gets the PlayerFile of the Chicken, to determine which file is used to render the Chicken
     *
     * @return - int - the index of the file used to render
     */
    public int getPlayerFile() {return this.playerFile;}
}
