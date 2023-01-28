package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject;

public class FunctionalButton extends Button{

    private int sceneId;
    private int  i = 0;
    private Vector3 vector3;
    private GameObject gameObj;
    private boolean isCameraAdj = false;
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(cellHeight,cellHeight);
        this.sceneId = sceneId;
    }
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, int sceneId,boolean isCameraAdj){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(cellHeight,cellHeight);
        this.isCameraAdj = isCameraAdj;
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
    public void setVector3(float[] tmp){vector3 = new Vector3(tmp[0],tmp[1]+10,tmp[2]);}
    @java.lang.Override
    public void objectInit() {
        super.objectInit();
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(this.sceneId == -1 && gameObj == null) {
            if(isCameraAdj){
                i++;
                if(i%3==0){
                    ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().setDefaultCamera();
                }else if(i%3==1){
                    ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().setTileCamera(vector3);
                }else if(i%3==2){
                    ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().setTopDownCamera();
                }
            } else {
                Gdx.app.exit();
            }
        }else{
            if(this.sceneId != -1){
                if(button == 0){
                    ZickeZacke.getInstance().setScreen(sceneId);
                }
            }else {
                this.gameObj.setActive(!gameObj.isActive());
            }
        }
    }

}
