package com.zickezacke.gameObjectStore;

import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class backGround3D extends GameObject3D {
    public backGround3D(int id){
        super(id, false);
    }

    @Override
    public void objectInit() {
        source3D = "environment.g3db";
        position3D = new Vector3(0f,0f,7f);
    }
}
