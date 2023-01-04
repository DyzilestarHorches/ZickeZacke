package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class notiScene extends GameObject {
    public notiScene(int id){super(id,true);}

    @Override
    public void objectInit() {
        source2D = "./UI_demo/noti_scene.png";
        position2D = new Vector2(0,0);
        size2D = new Vector2(1280,720);
        setActive(false);
    }
}
