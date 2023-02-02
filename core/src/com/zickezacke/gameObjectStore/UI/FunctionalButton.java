/**
 * Functional button is used to implement various UI button such as exit, back, play, pause,...
 */
package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject;

public class FunctionalButton extends Button{

    private final int sceneId;
    private int  i = 0;
    private Vector3 vector3;
    private GameObject gameObj;
    private boolean isCameraAdj = false;

    /**
     *constructor for FunctionalButton class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param sceneId - int - screen for button to direct
     *
     * @return FunctionalButton - button can direct user to another screen
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(cellHeight,cellHeight);
        this.sceneId = sceneId;
    }

    /**
     * constructor for FunctionalButton class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param sceneId - int - screen for button to direct
     * @param isCameraAdj - boolean - true if this button modifies camera view
     *
     * @return FunctionalButton - button can modify camera in different modes.
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, int sceneId,boolean isCameraAdj){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(cellHeight,cellHeight);
        this.isCameraAdj = isCameraAdj;
        this.sceneId = sceneId;
    }

    /**
     * constructor for FunctionalButton class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param xSize - double - height
     * @param ySize - double - width
     * @param sceneId - int - screen for button to direct
     *
     * @return FunctionalButton - button can direct user to another screen
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize,int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = null;
        this.sceneId = sceneId;
    }

    /**
     * constructor for FunctionalButton class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param gameObject - GameObject - object which this button can modify
     *
     *@return FunctionalButton - button can modify another gameObject
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, GameObject gameObject){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(cellHeight,cellHeight);
        this.gameObj = gameObject;
        this.sceneId = -1;
    }

    /**
     * constructor for FunctionalButton class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param xSize - double - height
     * @param ySize - double - width
     *
     * @return FunctionalButton - button to exit program
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = null;
        this.sceneId = -1;
    }

    /**
     * constructor for FunctionalButton class
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param xSize - double - height
     * @param ySize - double - width
     * @param gameObject - GameObject - object which this button can modify
     *
     *@return FunctionalButton - button can modify another gameObject
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize,GameObject gameObject){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = gameObject;
        this.sceneId = -1;
    }

    /**
     *sets position for button in scene
     * @param x  - double - x position
     * @param y  - double - y position
     */
    public void setPosition(double x, double y){
        position2D = new Vector2((float) x*cellWidth,(float) y*cellHeight);
    }
    /**
     *sets size for button in scene
     * @param x  - double - x position
     * @param y  - double - y position
     */
    public void setSize(double x, double y){
            size2D = new Vector2((float)x*cellWidth,(float) y*cellHeight);
    }

    /**
     * sets 3d position for camera to zoom in specific tile
     * @param tmp - float[3] - 3d position of camera
     */
    public void setVector3(float[] tmp){vector3 = new Vector3(tmp[0],tmp[1]+10,tmp[2]);}
    @java.lang.Override
    public void objectInit() {
        super.objectInit();
    }
    /**
     * starts mouse action corresponding fictional button
     */
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
