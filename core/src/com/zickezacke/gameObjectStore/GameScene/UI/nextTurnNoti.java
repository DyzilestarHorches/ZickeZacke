package com.zickezacke.gameObjectStore.GameScene.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class nextTurnNoti extends GameObject {
    private final int SHOW_FRAME = 30;
    private final float CELL_WIDTH =  1280/12f;
    private final float CELL_HEIGHT = 720/10f;

    private int nextPlayer;
    private int count = 0;

    public nextTurnNoti (int id, int nextPlayer){
        super(id,true);
        this.nextPlayer = nextPlayer;
        setActive(false);
    }
    @Override
    public void objectInit() {
        source2D = "./UI_demo/next_noti_" + String.valueOf(nextPlayer) + ".png";
        position2D = new Vector2(0*CELL_WIDTH,4*CELL_HEIGHT);
        size2D = new Vector2(12*CELL_WIDTH,2*CELL_HEIGHT);
    }

    @Override
    public void objectUpdate() {
        showNoti();
    }
    public  void showNoti(){
        if(this.isActive){
            count++;
            if(count == SHOW_FRAME){
                count = 0;
                this.setActive(false);
            }
        }
    }
}
