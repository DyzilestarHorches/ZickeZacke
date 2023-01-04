package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class OctTiles extends GameObject3D {

    private boolean occupy = false;

    private boolean Default = true;
    private boolean Trigger = true;
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
        Trigger = !Trigger;
    }

    @java.lang.Override
    public void objectUpdate() {
        super.objectUpdate();
        if(Default != Trigger){
            model3D.setRotation(new Vector3(0,0,1), 3f);
            count++;
            if(count == 60){
                Trigger = !Trigger;
                count = 0;
            }
        }
    }

    public void setOccupy(boolean state) {this.occupy = state;}

    public boolean getOccupy() {return  this.occupy;}
}
