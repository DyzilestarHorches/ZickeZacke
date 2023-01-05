package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class Obj extends GameObject3D {
    private float x,y;
    public Obj(int id, float x, float y){
        super(id,true);
        this.x = x;
        this.y = y;
    }

    @Override
    public void objectInit() {
        source3D = "demo.g3db";
        position3D = new Vector3(x,1f,y);
    }
}
