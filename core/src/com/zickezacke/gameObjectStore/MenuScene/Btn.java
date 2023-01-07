package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject;

public class Btn extends GameObject {
    public int cellWidth =  1280/12;
    public int cellHeight = 720/10;

    private String typeFile;

    public static GameWorld thisMenuScene;


    public Btn(int id){
        super(id, true);
    }
    public Btn(int id,String typeFile){
        super(id,true);
        this.typeFile = typeFile;
    }

    @Override
    public void objectInit() {
        if (typeFile != null) {
            source2D = "./UI_demo/" + typeFile + ".png";
        }
        if (ZickeZacke.getInstance().getGameScreens().size() >1)
            thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
    }

    @Override
    public void objectStart() {
        super.objectStart();
    }

    @Override
    public void objectUpdate() {
        thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
    }
}
