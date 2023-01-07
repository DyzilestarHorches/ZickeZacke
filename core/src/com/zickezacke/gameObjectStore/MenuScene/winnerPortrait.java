package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

public class winnerPortrait extends GameObject {
    private int playerFile;
    public int cellWidth =  1280/12;
    public int cellHeight = 720/10;
    public winnerPortrait(int id, int playerFile){
        super(id,true);
        this.playerFile = playerFile;
    }

    @Override
    public void objectInit() {
        source2D = "./UI_demo/winner_" + String.valueOf(playerFile) + ".png";
        position2D = new Vector2(5*cellWidth,6*cellHeight);
        size2D = new Vector2(2*cellWidth,3*cellHeight);
    }

}
