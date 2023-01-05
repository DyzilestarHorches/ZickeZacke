package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.scenes.GameScene;

public class OctTiles extends GameObject3D {
    private static final int FLIP_FRAME = 10;

    private static final int PAUSE_FRAME = 10;

    private static boolean inAnimation = false;

    private boolean Trigger = false;
    private boolean Back = false;
    private boolean Timer = false;

    private int timer = 0;
    private int count = 0;
    private BoundingVisual boundingVisual = new BoundingVisual();

    private String type;

    public OctTiles(int id, int type,float x, float y, float z){
        super(id,true);
        setPosition(x,y,z);
        this.type = String.valueOf(type);
    }

    public void setPosition(float x, float y, float z){
        position3D = new Vector3(x,y,z);
    }


    @java.lang.Override
    public void objectInit() {
        source3D = "./Cards/" + type + "/oct_card.g3db";
        scale3D = new Vector3(1,1,1);
        components.add(boundingVisual);
    }

    @java.lang.Override
    public void MouseDown(int screenX, int screenY, int pointer, int button) {
        if (!inAnimation) {
            Trigger = !Trigger;
            inAnimation = true;
            GameScene gameScene = (GameScene) ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld();
            gameScene.typeClicked = this.type;
        }
    }

    @java.lang.Override
    public void objectUpdate() {
        super.objectUpdate();

        flip();
    }

    public void flip() {
        if(Trigger){
            model3D.setRotation(new Vector3(0,0,1), 180f/FLIP_FRAME);
            count++;
            position3D.y += 10/600f;
            if(count == FLIP_FRAME){
                Trigger = !Trigger;
                count = 0;
                Timer = true;

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
            position3D.y -= 10/600f;
            if (count == FLIP_FRAME) {
                Back = !Back;
                count = 0;
                inAnimation = false;
            }
        }
    }
}
