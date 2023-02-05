package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.zickezacke.nclib.gameObject.GameObject;
/**
 * Button is a component to implement ui
 */
public class Button extends GameObject {
    //value of grid layout 12 columns and 10 rows
    protected final int CELL_WIDTH =  Gdx.graphics.getWidth()/12;
    protected final int CELL_HEIGHT = Gdx.graphics.getHeight()/10;

    //
    private String typeFile;

    /**
     * constructor for button class
     *
     * @param id - int - unique identifier for object
     */
    public Button(int id){
        super(id, true);
    }

    /**
     * constructor for button class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     */
    public Button(int id, String typeFile){
        super(id,true);
        this.typeFile = typeFile;
    }

    /**
     * initiates a button object
     */
    @Override
    public void objectInit() {
        if (typeFile != null) {
            source2D = "./UI/" + typeFile + ".png";
        }
    }

    /**
     * starts a button object
     */
    @Override
    public void objectStart() {
        super.objectStart();
    }
}
