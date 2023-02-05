/**
 * Background is used to implement background in menu scene
 */
package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;
public class BackGround extends GameObject {
    private String typeFile;

    /**
     * constructor for BackGround class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     *
     * @return BackGround - a background
     */
    public BackGround(int id, String typeFile){
        super(id);
        this.typeFile = typeFile;
    }

    @Override
    /**
     * initiates a background which is fit screen size
     */
    public void objectInit() {
        //initiates image based on file path
        source2D = "./UI/" + typeFile + ".jpg";
        //pivot of image object is fixed to bottom-left of the screen.
        position2D = new Vector2(0,0);
        //fits size of image responsive to window size.
        size2D = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
