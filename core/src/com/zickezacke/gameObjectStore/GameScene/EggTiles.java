package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class EggTiles extends GameObject3D {
    private BoundingVisual boundingVisual = new BoundingVisual();
    public EggTiles(int id){super(id,true);}

    public void setPosition(int x, int y, int z){
        position3D = new Vector3(x,y,z);
    }

    @java.lang.Override
    public void objectInit() {
        source3D = "";
        scale3D = new Vector3(1,1,1);
        components.add(boundingVisual);
    }

}
