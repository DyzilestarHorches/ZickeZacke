package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;

public class chickenObject extends GameObject3D {
    private boolean isMoving;
    public chickenObject(int id) {super(id,true);}

    @Override
    public void objectInit() {
        source3D = "chicken.g3db";
        position3D = new Vector3(0f,0f,0f);
    }

    @Override
    public void objectStart() {
        this.isMoving = false;
    }

    public void animation(){
        String Default = model3D.animations.get(0).id;
        String Jump = model3D.animations.get(1).id;
        if (!isMoving) animation3D.setAnimation(Default, 10);
        if (isMoving)
            animation3D.animate(Jump, new Animation3D.AnimationListener(){
                @Override
                public void onEnd(Animation3D.AnimationDesc animation) {
                    isMoving = false;
                }

                @Override
                public void onLoop(Animation3D.AnimationDesc animation) {

                }
            }, 0f);
    }

    @Override
    public void objectUpdate() {
        animation();
            }
}
