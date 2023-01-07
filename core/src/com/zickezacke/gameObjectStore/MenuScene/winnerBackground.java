package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class winnerBackground extends GameObject {
    public winnerBackground(int id){super(id,true);}

    @Override
    public void objectInit() {
        source2D = "./UI_demo/winner_background.jpg";
        position2D = new Vector2(0,0);
        size2D =new Vector2(1280,720);
    }
}
