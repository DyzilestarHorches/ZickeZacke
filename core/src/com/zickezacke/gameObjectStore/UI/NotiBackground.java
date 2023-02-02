/**
 * NotiBackground is used to implement background in menu scene with can active or inactive
 */
package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class NotiBackground extends GameObject {
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
        setActive(false);
    }
    @Override
    /**
     * initiates a notification background which is fit screen size
     */
    public void objectInit() {
        source2D = "./UI/" + fileType + ".png";
        position2D = new Vector2(0,0);
        size2D = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setActive(false);
    }

}
