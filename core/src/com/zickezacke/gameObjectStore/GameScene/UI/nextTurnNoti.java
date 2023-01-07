package com.zickezacke.gameObjectStore.GameScene.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class nextTurnNoti extends GameObject {
    private int nextPlayer;
    private final int SHOW_FRAME = 30;
    private int count = 0;
    public int cellWidth =  1280/12;
    public int cellHeight = 720/10;
    public nextTurnNoti (int id, int nextPlayer){
        super(id,true);
        this.nextPlayer = nextPlayer;
        setActive(false);
    }
    @Override
    public void objectInit() {
        source2D = "./UI_demo/next_noti_" + String.valueOf(nextPlayer) + ".png";
        position2D = new Vector2(0*cellWidth,4*cellHeight);
        size2D = new Vector2(12*cellWidth,2*cellHeight);
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
