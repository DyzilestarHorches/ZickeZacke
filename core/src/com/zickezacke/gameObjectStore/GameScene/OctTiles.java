package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class OctTiles extends GameObject3D {
    private BoundingVisual boundingVisual = new BoundingVisual();
    public OctTiles(int id){super (id,true);}

    public void setPosition(int x, int y, int z){
        position3D = new Vector3(x,y,z);
    }

    @java.lang.Override
    public void objectInit() {
        source3D = "card_pick_0.g3db";
        scale3D = new Vector3(1,1,1);
        components.add(boundingVisual);
    }

    @java.lang.Override
    public void MouseUp(int screenX, int screenY, int pointer, int button) {
        model3D.setRotation(new Vector3(0,0,1),180f);
    }


}
