package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;

public class DirectButton extends Button{

    private int sceneId;

    public DirectButton(int id, String typeFile, double xPos, double yPos, int sceneId){
        super(id, typeFile);
        position2D = new Vector2((float) xPos*cellWidth,(float) yPos*cellHeight);
        this.sceneId = sceneId;
    }

    @java.lang.Override
    public void objectInit() {
        super.objectInit();
        size2D = new Vector2(cellHeight,cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(button == 0){
            ZickeZacke.getInstance().setScreen(sceneId);
        }
    }

}
