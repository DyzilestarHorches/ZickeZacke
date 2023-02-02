/**
 * Button is a component to implement ui
 */
package com.zickezacke.gameObjectStore.UI;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject;

public class Button extends GameObject {
    public int cellWidth =  1280/12;
    public int cellHeight = 720/10;
    private String typeFile;

    /**
     * constructor for button class
     *
     * @param id - int - unique identifier for object
     *
     * @return
     */
    public Button(int id){
        super(id, true);
    }

    /**
     * constructor for button class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     *
     * @return
     */
    public Button(int id, String typeFile){
        super(id,true);
        this.typeFile = typeFile;
    }

    @Override
    /**
     * initiates a button object
     */
    public void objectInit() {
        if (typeFile != null) {
            source2D = "./UI/" + typeFile + ".png";
        }
    }

    @Override
    /**
     * starts a button object
     */
    public void objectStart() {
        super.objectStart();
    }

}
