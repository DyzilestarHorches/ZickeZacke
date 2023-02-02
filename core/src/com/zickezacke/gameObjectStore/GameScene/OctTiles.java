package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.scenes.GameScene;

/**
 * The OctTiles class is a Tile that faces its picture on the ground
 *  is used in the Main Scene
 *
 */
public class OctTiles extends GameObject3D {
    // frame that the OctTile flips per second
    private static final int FLIP_FRAME = 10;

    // frame the the OctTile pause per second
    private static final int PAUSE_FRAME = 10;

    // prevents player from flipping another tile while there is a tile is being flipped
    private static boolean inAnimation = false;

    // prevents player from clicking the tile before doing the Core Logic
    private static boolean isClickable = true;

    // determines to flip the tile
    private boolean Trigger = false;

    // determines to face down the tile back
    private boolean Back = false;

    // determines to pause the tile after flip the Tile up
    private boolean Timer = false;

    // is used to render the action flipping of the tile by frame per second
    private int count = 0;

    // file to render the Egg Tile
    private String octTileFile;

    // stores value to check in GameScene
    private GameScene thisGameScene;

    /**
     * Constructor for the OctTiles class
     * @param id - int - unique identifier for the object
     * @param octTileFile - the file used to render the Egg Tile
     * @param x - float - x-coordinate of the Egg Tile's position
     * @param y - float - y-coordinate of the Egg Tile's position
     * @param z - float - z-coordinate of the Egg Tile's position
     */
    public OctTiles(int id, int octTileFile,float x, float y, float z){
        super(id,true);
        setPosition(x,y,z);
        this.octTileFile = String.valueOf(octTileFile);
    }

    /**
     * sets the Position of the Oct Tile to an assigned coordinate
     *
     * @param x - float - x-coordinate of the Oct Tile's position
     * @param y - float - y-coordinate of the Oct Tile's position
     * @param z - float - z-coordinate of the Oct Tile's position
     */
    public void setPosition(float x, float y, float z){
        position3D = new Vector3(x,y,z);
    }

    /**
     * overrides the objectInit method in parent class GameObject3D, to make changes before create
     */
    @java.lang.Override
    public void objectInit() {
        source3D = "./Cards/" + octTileFile + "/oct_card.g3db";
        scale3D = new Vector3(1,1,1);
    }

    /**
     * overrides the objectStart method in parent class GameObject3D, for changes after object creation
     */
    @Override
    public void objectStart() {
        if (!ZickeZacke.DEVELOPER_MODE) {
            model3D.setRotation(new Vector3(0,0,1), 180f);
        }
    }

    @java.lang.Override
    public void MouseDown(int screenX, int screenY, int pointer, int button) {
        if (!inAnimation) {
            Trigger = !Trigger;
            inAnimation = true;
            thisGameScene.octTileFileClicked = this.octTileFile;
        }
    }

    /**
     * overrides the objectUpdate method in parent class GameObject3D, to make changes before render
     */
    @java.lang.Override
    public void objectUpdate() {
        super.objectUpdate();

        if (thisGameScene == null) {
            thisGameScene = (GameScene) ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld();
        }
        flip();
    }

    /**
     * flips and pauses the Oct Tile by frame per second
     */
    public void flip() {
        if(Trigger){
            model3D.setRotation(new Vector3(0,0,1), 180f/FLIP_FRAME);
            count++;
            position3D.y += 10/600f;
            if(count == FLIP_FRAME){
                Trigger = !Trigger;
                count = 0;
                Timer = true;

            }
        }

        if(Timer){
            count++;
            if(count == PAUSE_FRAME){
                count = 0;
                Timer = false;
                Back = true;
            }
        }

        if(Back){
            model3D.setRotation(new Vector3(0, 0, 1), -180f / FLIP_FRAME);
            count++;
            position3D.y -= 10/600f;
            if (count == FLIP_FRAME) {
                Back = !Back;
                count = 0;
                inAnimation = false;
            }
        }
    }
}
