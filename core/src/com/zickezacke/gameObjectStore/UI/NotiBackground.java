package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

/**
 * NotiBackground is used to implement background in menu scene with can active or inactive
 */
public class NotiBackground extends GameObject {
    // the file used to render the NotiBackGround
    private String fileType;

    /**
     * constructor for BackGround class
     *
     * @param id - int - unique identifier for object
     * @param fileType - String -  image file of button
     *
     * @return NotiBackGround - a background notification
     */
    public NotiBackground(int id,String fileType){
        super(id,true);
        this.fileType = fileType;
        //set inactive as default
        setActive(false);
    }

    /**
     * initiates a notification background which is fit screen size
     */
    @Override
    public void objectInit() {
        //initiates texture for notification based on file path.
        source2D = "./UI/" + fileType + ".png";
        //initiates position fixed bottom-left of screen.
        position2D = new Vector2(0,0);
        //initiates fixed screen size.
        size2D = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setActive(false);
    }

}
