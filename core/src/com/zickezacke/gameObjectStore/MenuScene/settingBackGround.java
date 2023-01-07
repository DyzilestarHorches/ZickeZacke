package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class settingBackGround extends GameObject {
    public settingBackGround(int id, boolean isID){super(id,isID);}

    @Override
    public void objectInit() {
        source2D = "./UI/setting_background.jpg";
        position2D = new Vector2(0,0);
        size2D = new Vector2(1280,720);
    }
}
