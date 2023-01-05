package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class Ground extends GameObject3D {
    public Ground(int id){
        super(id, false);
    }

    @Override
    public void objectInit() {
        source3D = "./game_scene/main_ground_demo.g3db";
        position3D = new Vector3(0, 0, 0);
        scale3D = new Vector3(1, 1, 1);
    }

    @Override
    public void objectStart() {
        position3D.set(-offset3D.x, - offset3D.y+2f, -offset3D.z);
    }

    @Override
    public void MouseDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("really","yes");
    }
}
