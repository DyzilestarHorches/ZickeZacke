package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject3D;
public class Tail extends GameObject3D {
    private boolean isMoving;
    private GameWorld thisGameScene;
    private int playerNum;
    private Chicken myChicken;
    //private BoundingVisual boundingVisual = new BoundingVisual();
    public Tail(int id, int playerNum){
        super(id, false);
        this.playerNum = playerNum;
    }
    public void setPosition(float x, float y, float z){
        position3D = new Vector3(x,y,z);
    }


    @java.lang.Override
    public void objectInit() {
        thisGameScene = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld();
        source3D = "./Chickens/tail_" + String.valueOf(playerNum) + ".g3db";
        // chicken 0 -> get(37)
        //tail 0 added
        // chicken 1 -> get(39)
        myChicken = (Chicken)(thisGameScene.getGameObjects3D().get(37+playerNum*2));
        if (myChicken != null){
            position3D = myChicken.getCNposition();
        }
        scale3D = new Vector3(1,1,1);
    }

    @java.lang.Override
    public void objectUpdate() {
        thisGameScene = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld();
    }
}
