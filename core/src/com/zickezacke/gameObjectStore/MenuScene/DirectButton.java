package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;

public class DirectButton extends Button{

    private int sceneId;

    public DirectButton(int id, String typeFile, double xPos, double yPos, int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        this.sceneId = sceneId;
    }

    public void setPosition(double x, double y){
        position2D = new Vector2((float) x*cellWidth,(float) y*cellHeight);
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
