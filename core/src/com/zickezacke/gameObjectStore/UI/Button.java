package com.zickezacke.gameObjectStore.UI;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject;

public class Button extends GameObject {
    public int cellWidth =  1280/12;
    public int cellHeight = 720/10;

    private String typeFile;

    public Button(int id){
        super(id, true);
    }
    public Button(int id, String typeFile){
        super(id,true);
        this.typeFile = typeFile;
    }

    @Override
    public void objectInit() {
        if (typeFile != null) {
            source2D = "./UI/" + typeFile + ".png";
        }
    }

    @Override
    public void objectStart() {
        super.objectStart();
    }

}
