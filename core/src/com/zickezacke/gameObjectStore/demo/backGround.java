package com.zickezacke.gameObjectStore.demo;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.component.UIHelpers;
import com.zickezacke.nclib.gameObject.GameObject;


public class backGround extends GameObject {
    UIHelpers uiHelpers;
    public backGround(int id){
        super(id);
        uiHelpers = new UIHelpers();
        components.add(uiHelpers);
    }
    public void objectInit(){
        source2D = "POST.jpg";
        position2D = new Vector2(1280/2 - 100, 720/2 - 100);
        size2D = new Vector2(200, 200);
    }
    public void objectUpdate(){
        uiHelpers.drawGrid(4,4);
    }
}