package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class OctTiles extends GameObject3D {
    private static final int FLIP_FRAME = 60;

    private static final int PAUSE_FRAME = 60;

    private static boolean inAnimation = false;

    private boolean Trigger = false;
    private boolean Back = false;
    private boolean Timer = false;

    private int timer = 0;
    private int count = 0;
    private BoundingVisual boundingVisual = new BoundingVisual();
    public OctTiles(int id,int x, int y, int z){
        super(id,true);
        setPosition(x,y,z);
    }

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
        if (!inAnimation) {
            Trigger = !Trigger;
            inAnimation = true;
        }
    }

    @java.lang.Override
    public void objectUpdate() {
        super.objectUpdate();
        if(Trigger){
            model3D.setRotation(new Vector3(0,0,1), 180f/FLIP_FRAME);
            count++;
            position3D.y += 10/600f;
            if(count == FLIP_FRAME){
                Trigger = !Trigger;
                count = 0;
                Timer = true;
                position3D.y -= 1f;
            }
        }

        if(Timer){
            timer++;
            if(timer == PAUSE_FRAME){
                timer = 0;
                Timer = false;
                Back = true;
            }
        }

        if(Back){
            model3D.setRotation(new Vector3(0, 0, 1), -180f / FLIP_FRAME);
            count++;
            position3D.y += 10/600f;
            if (count == FLIP_FRAME) {
                Back = !Back;
                count = 0;
                position3D.y -= 1f;
                inAnimation = false;
            }
        }
    }
}
