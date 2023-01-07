package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class Tail extends GameObject3D {

    private GameWorld thisGameScene;

    // The player has the Tail
    private int playerFile;

    // get File to render the Tail
    private final int tailFile;

    private int tile;
    private int tileShift;
    private static final  int ROTATE_FRAME = 30;
    private int count = 0;
    private boolean trigger = false;
    private Chicken myChicken;
    //private BoundingVisual boundingVisual = new BoundingVisual();
    public Tail(int id,int tailFile, int playerFile, int tile){
        super(id, false);
        this.tailFile = tailFile;
        this.playerFile = playerFile;
        this.tile = tile;
    }

    public void setPlayerFile(int playerFile) {this.playerFile = playerFile;}
    public int getPlayerFile() {return this.playerFile;}

    @Override
    public void objectStart() {
        model3D.setRotation(new Vector3(0,1,0),170f-(360f/24f)*tile);
    }

    @java.lang.Override
    public void objectInit() {
        thisGameScene = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld();
        source3D = "./Chickens/tail_" + String.valueOf(tailFile) + ".g3db";
        // chicken 0 -> get(37)
        //tail 0 added
        // chicken 1 -> get(39)
        myChicken = (Chicken)(thisGameScene.getGameObjects3D().get(37+playerFile*2));
        if (myChicken != null){
            position3D = myChicken.getPosition3D();
        }
        scale3D = new Vector3(1,1,1);
    }

    @Override
    public void objectUpdate() {
        myChicken = (Chicken) (thisGameScene.getGameObjects3D().get(37+playerFile*2));
        if (myChicken != null){
            position3D = myChicken.getPosition3D();
        }

        rotateTail();

    }
    public void rotateTail(){
        if(this.trigger){
            count++;
            for(int i = 0; i < tileShift; i++){
                model3D.setRotation(new Vector3(0,1,0),-(360f/24f)/ROTATE_FRAME);
            }
            if(count==ROTATE_FRAME){
                count = 0;
                this.trigger = false;}
        }
    }
    public void setTile(int nextTile){
        this.tileShift = (Math.abs(this.tile - nextTile)<24/2) ? Math.abs(this.tile - nextTile) : (24-Math.abs(this.tile - nextTile));
        this.tile = nextTile;
        this.trigger = true;
    }
}
