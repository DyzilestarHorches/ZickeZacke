package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;


public class BackGround extends GameObject {
    private String typeFile;
    public BackGround(int id, String typeFile){
        super(id);
        this.typeFile = typeFile;
    }

    @Override
    public void objectInit() {
        source2D = "./UI/" + typeFile + ".jpg";
        position2D = new Vector2(0,0);
        size2D = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
