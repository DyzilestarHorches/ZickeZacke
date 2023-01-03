package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.gameObjectStore.MenuScene.selectBtn1;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;

public class Chicken1 extends GameObject3D {
    private boolean isMoving;
    private BoundingVisual boundingVisual = new BoundingVisual();
    public Chicken1(int id){super(id, true);}

    @java.lang.Override
    public void objectInit() {
        source3D = "demo.g3db";
        position3D = new Vector3(0,0,1);
        scale3D = new Vector3(1,1,1);
        components.add(boundingVisual);
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

    @java.lang.Override
    public void objectUpdate() {
        isActive = selectBtn1.getState();
        boundingVisual.drawBox(dimensions,bounds, Color.RED);
        animation();
    }

}
