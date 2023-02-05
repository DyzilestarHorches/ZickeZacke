package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject3D;

/**
 * Ground is used for implementing 3d play ground.
 */
public class Ground extends GameObject3D {
    /**
     * constructor for BackGround class
     *
     * @param id - int - unique identifier for object
     */
    public Ground(int id){
        super(id, false);
    }

    /**
     * initiates a main 3d ground.
     */
    @Override
    public void objectInit() {
        //initiates 3d ground base on file path.
        source3D = "./Environment/ground.g3db";
        //specifies (0,0,0) position in 3d coordination system.
        position3D = new Vector3(0, 0, 0);
        //scales object to 1:1 ratio.
        scale3D = new Vector3(1, 1, 1);
    }

    /**
     * adjusts the ground position.
     */
    @Override
    public void objectStart() {
        position3D.set(-offset3D.x, -0.2f, -offset3D.z);
    }
}
