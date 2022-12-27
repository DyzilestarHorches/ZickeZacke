package com.zickezacke.gameObjectStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;


public class testObject extends GameObject3D {

    private boolean isMoving;

    public testObject(int id){
        super(id);
    }
    public void objectInit(){
        source3D = "demo.g3db";
        position3D = new Vector3(0f, 0f, -10f);
    }
    public void objectStart(){
        isMoving = false;
    }
    public void animation(){
        String Default = model3D.animations.get(0).id;
        String Ani1 = model3D.animations.get(1).id;
        if (!isMoving) animation3D.setAnimation(Default, 10);
        if (isMoving)
            animation3D.animate(Ani1, new Animation3D.AnimationListener(){
                @Override
                public void onEnd(Animation3D.AnimationDesc animation) {
                    isMoving = false;
                }

                @Override
                public void onLoop(Animation3D.AnimationDesc animation) {

                }
            }, 0f);
    }
    public void movement(){
        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            model3D.setRotation(new Vector3(0,0,1), 2f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z)){
            model3D.setScale(new Vector3(1.1f,1.1f,1.1f));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
            Gdx.app.log("Object","Move");
            position3D.x += 10/60f;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
            Gdx.app.log("Object","Move");
            position3D.x -= 10/60f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
            Gdx.app.log("Object","Move");
            position3D.z += 10/60f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
            Gdx.app.log("Object","Move");
            position3D.z -= 10/60f;
        }
    }
    public void openMenu(){
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            ZickeZacke.getInstance().setScreen(1);
        }

    }
    public void objectUpdate(){
        animation();
        movement();
        openMenu();
    }
}
