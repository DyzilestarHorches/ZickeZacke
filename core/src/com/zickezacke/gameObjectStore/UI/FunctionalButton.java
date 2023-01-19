package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject;

public class FunctionalButton extends Button{

    private int sceneId;
    private GameObject gameObj;
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(cellHeight,cellHeight);
        this.sceneId = sceneId;
    }
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize,int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = null;
        this.sceneId = sceneId;
    }

    public FunctionalButton(int id, String typeFile, double xPos, double yPos, GameObject gameObject){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(cellHeight,cellHeight);
        this.gameObj = gameObject;
        this.sceneId = -1;
    }
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = null;
        this.sceneId = -1;
    }
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize,GameObject gameObject){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = gameObject;
        this.sceneId = -1;
    }

    public void setPosition(double x, double y){
        position2D = new Vector2((float) x*cellWidth,(float) y*cellHeight);
    }
    public void setSize(double x, double y){
            size2D = new Vector2((float)x*cellWidth,(float) y*cellHeight);
    }

    @java.lang.Override
    public void objectInit() {
        super.objectInit();
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(this.sceneId == -1 && gameObj == null) {
            Gdx.app.exit();
        }
        if(this.sceneId != -1){
            if(button == 0){
                ZickeZacke.getInstance().setScreen(sceneId);
            }
        }else {
            this.gameObj.setActive(!gameObj.isActive());
        }
    }

}
