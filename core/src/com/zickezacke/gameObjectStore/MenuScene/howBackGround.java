package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class howBackGround extends GameObject {
    public howBackGround(int id, boolean isUI){super(id,isUI);}

    @Override
    public void objectInit() {
        source2D = "./UI_demo/how_background.jpg";
        position2D = new Vector2(0,0);
        size2D = new Vector2(1280,720);
    }
}
