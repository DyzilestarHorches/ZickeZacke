package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;

/**
 * The EggTiles class is a Tile that the Chicken stands on
 *  is used in the Main Scene
 *
 */
public class EggTiles extends GameObject3D {
    // determines if the tile has the chicken standing on it
    private boolean occupy = false;

    // rotates the egg tile based on the camera
    private float angle;

    // file to render the Egg Tile
    private final String eggTileFile;

    /**
     * Constructor for the EggTiles class
     *
     * @param id - int - unique identifier for the object
     * @param eggTileFile - the file used to render the Egg Tile
     * @param x - float - x-coordinate of the Egg Tile's position
     * @param y - float - y-coordinate of the Egg Tile's position
     * @param z - float - z-coordinate of the Egg Tile's position
     * @param angle - the angle of the Egg Tile
     */
    public EggTiles(int id, int eggTileFile,float x, float y, float z, float angle){
        super(id,true);
        setPosition(x,y,z);
        this.angle = angle;
        this.eggTileFile = String.valueOf(eggTileFile);
    }

    /**
     * sets the Position of the Egg Tile to an assigned coordinate
     *
     * @param x - float - x-coordinate of the Egg Tile's position
     * @param y - float - y-coordinate of the Egg Tile's position
     * @param z - float - z-coordinate of the Egg Tile's position
     */
    public void setPosition(float x, float y, float z){position3D = new Vector3(x,y,z);}

    /**
     * overrides the objectInit method in parent class GameObject3D, to make changes before create
     *
     */
    @java.lang.Override
    public void objectInit() {
        source3D = "./Cards/" + eggTileFile + "/egg_card.g3db";
        scale3D = new Vector3(1,1,1);
    }

    /**
     * overrides the objectStart method in parent class GameObject3D, for changes after object creation
     *
     */
    @Override
    public void objectStart() {
        model3D.setRotation(new Vector3(0, 0, 1), 180f);
        //model3D.setRotation(new Vector3(0, 1, 0), angle);
    }

    /**
     * overrides the objectUpdate method in parent class GameObject3D, to make changes before render
     */
    @Override
    public void objectUpdate() {}

    /**
     * gets file's name of the Egg Tile
     *
     * @return - String - file's name of the Egg Tile
     */
    public String getType() {
        return this.eggTileFile;
    }

    /**
     * sets the state for the Egg Tile if it has chicken standing on it
     *
     * @param state - boolean - the state of the Egg Tile
     */
    public void setOccupy(boolean state) {this.occupy = state;}

    /**
     * gets the state of the Egg Tile
     *
     * @return - boolean - the state of the Egg Tile
     */
    public boolean getOccupy() {return  this.occupy;}

    @Override
    public void MouseDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("yes");
    }
}
