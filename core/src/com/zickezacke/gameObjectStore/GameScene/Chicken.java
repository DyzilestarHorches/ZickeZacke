package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;
import com.zickezacke.scenes.GameScene;

import java.util.ArrayList;
import java.util.List;

public class Chicken extends GameObject3D {
    private boolean isMoving;

    private boolean isJumping = false;

    private static final  int JUMP_FRAME = 30;

    private static final float HEIGHT_SCALE = 0.1f;

    private int count = 0;

    private int tail = 0;

    private boolean Trigger = false;

    private int tile;
    private int tileShift;
    private float[] currentPos = new float[3];
    private float[] desPos = new float[3];
    private final int playerNum;
    private final int playerFile;
    private Color color;
    private BoundingVisual boundingVisual = new BoundingVisual();
    public Chicken(int id, float x, float y, float z,
                   Color color, int tile, int playerNum, int playerFile, int tail){
        super(id, true);
        setPosition(x,y,z);
        this.color = color;
        this.tile = tile;
        this.playerNum = playerNum;
        this.playerFile = playerFile;
        this.tail = tail;

    }

    public void setPosition(float x, float y, float z){
        position3D = new Vector3(x,y,z);
    }

    @java.lang.Override
    public void objectInit() {
        Gdx.app.log("playernum", String.valueOf(playerNum));
        source3D = "./Chickens/chicken_" + String.valueOf(playerFile) + ".g3db";
        scale3D = new Vector3(1,1,1);
        components.add(boundingVisual);
    }

    public void objectStart(){
        //isMoving = false;
        //rotate object
        model3D.setRotation(new Vector3(0,1,0), 170f-(360f/24f)*tile);
    }
    public void animation(){
        String Default = model3D.animations.get(0).id;
        animation3D.setAnimation(Default, 10);
    }

    public void setCurrentPos() {
        currentPos[0] = position3D.x;
        currentPos[1] = position3D.y;
        currentPos[2] = position3D.z;
    }

    public void setDesPosition(float x, float y, float z){
        setCurrentPos();

        desPos[0] = x;
        desPos[1] = y;
        desPos[2] = z;

        Trigger = true;
        isJumping = true;
    }

    public void move() {
        if (Trigger) {
            count++;
            position3D.x += (desPos[0]-currentPos[0])/JUMP_FRAME;
            position3D.z += (desPos[2]-currentPos[2])/JUMP_FRAME;

            position3D.y = getNewY();

            rotateChicken();

            if (count == JUMP_FRAME) {
                Trigger = false;
                isJumping = false;
                count = 0;

            }
        }
    }
    public void rotateChicken(){
        for(int i = 0; i < this.tileShift; i++){
            model3D.setRotation(new Vector3(0,1,0), -(360f/24f)/JUMP_FRAME);
        }
    }
    @java.lang.Override
    public void objectUpdate() {
        boundingVisual.drawBox(dimensions,bounds, color);
        animation();

        if (isJumping) {
            move();
        }

    }
    public void setTile(int tile) {
        this.tileShift = (Math.abs(this.tile - tile)<24/2) ? Math.abs(this.tile - tile) : (24-Math.abs(this.tile - tile));
        this.tile = tile;
    }
    public int getTile() { return this.tile;}

    private float getNewY() {
        float xStar = (float) Math.sqrt(Math.pow(desPos[0]-currentPos[0],2)+Math.pow(desPos[2]-currentPos[2],2));

        float xNew = -xStar + count*xStar/JUMP_FRAME;

        return (float) (HEIGHT_SCALE * ( -Math.pow(xNew, 2) - xStar * xNew));
    }

    public int getTail() {return this.tail;}

    public void loseTail() {this.tail = 0;}

    public void gainTail(int tailNumber) {this.tail += tailNumber;}

    public int getPlayerNum() {return this.playerNum;}

    public int getPlayerFile() {return this.playerFile;}
}
