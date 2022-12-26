package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class logo extends GameObject {
    public logo(int id){
        super(id);
    }

    @Override
    public void objectInit() {
        source2D = "./UI_demo/logo@4x.png";
        position2D = new Vector2(1280/12*5, 720/10*6);
        size2D = new Vector2(1280/12*2, 720/10*3);
    }
}
