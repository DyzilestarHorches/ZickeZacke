package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class EggTiles extends GameObject3D {
    private final BoundingVisual boundingVisual = new BoundingVisual();
    private boolean occupy = false;
    private float angle;
    private final String eggTileFile;

    public EggTiles(int id, int eggTileFile,float x, float y, float z, float angle){
        super(id,true);
        setPosition(x,y,z);
        this.angle = angle;
        this.eggTileFile = String.valueOf(eggTileFile);
    }

    public void setPosition(float x, float y, float z){position3D = new Vector3(x,y,z);}

    @java.lang.Override
    public void objectInit() {
        source3D = "./Cards/" + eggTileFile + "/egg_card.g3db";
        scale3D = new Vector3(1,1,1);
        components.add(boundingVisual);
    }

    @Override
    public void objectStart() {
        model3D.setRotation(new Vector3(0, 0, 1), 180f);
        model3D.setRotation(new Vector3(0, 1, 0), angle);
        boundingVisual.set(dimensions, bounds, new Color(0.7f, 0.7f, 0.7f, 0.2f));
    }

    @Override
    public void objectUpdate() {
        if (occupy){
            boundingVisual.setActive(true);
        }
    }

    public void setOccupy(boolean state) {this.occupy = state;}

    public String getType() {
        return this.eggTileFile;
    }

    public boolean getOccupy() {return  this.occupy;}

    @Override
    public void MouseDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("yes");
    }
}
