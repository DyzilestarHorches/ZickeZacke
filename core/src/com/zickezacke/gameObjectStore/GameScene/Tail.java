package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class Tail extends GameObject3D {

    private GameWorld thisGameScene;

    // The player has the Tail
    private int playerNum;

    // get File to render the Tail
    private final int playerFile;

    private Chicken myChicken;
    //private BoundingVisual boundingVisual = new BoundingVisual();
    public Tail(int id,int playerFile, int playerNum){
        super(id, false);
        this.playerFile = playerFile;
        this.playerNum = playerNum;
    }

    public void setPlayerNum(int playerNum) {this.playerNum = playerNum;}
    public int getPlayerNum() {return this.playerNum;}


    @java.lang.Override
    public void objectInit() {
        thisGameScene = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld();
        source3D = "./Chickens/tail_" + String.valueOf(playerFile) + ".g3db";
        // chicken 0 -> get(37)
        //tail 0 added
        // chicken 1 -> get(39)
        myChicken = (Chicken)(thisGameScene.getGameObjects3D().get(37+playerNum*2));
        if (myChicken != null){
            position3D = myChicken.getPosition3D();
        }
        scale3D = new Vector3(1,1,1);
    }

    @Override
    public void objectUpdate() {
        myChicken = (Chicken) (thisGameScene.getGameObjects3D().get(37+playerNum*2));
        if (myChicken != null){
            position3D = myChicken.getPosition3D();
        }
    }
}
