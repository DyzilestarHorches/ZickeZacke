package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.scenes.GameScene;
/**
 * Functional button is used to implement various UI button such as exit, back, play, pause,...
 */
public class FunctionalButton extends Button{
    // screen it for button to direct
    private final int sceneId;

    // index for switching camera modes.
    private int  i = 0;

    // 3d position of camera in mode 2.
    private Vector3 vector3;

    // object which allows button to modify.
    private GameObject gameObj;

    // camera tag for camera button.
    private boolean isCameraAdj = false;

    /**
     * constructor for FunctionalButton class - button can direct user to another screen
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param sceneId - int - screen for button to direct
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(CELL_HEIGHT, CELL_HEIGHT);
        this.sceneId = sceneId;
    }

    /**
     * constructor for FunctionalButton class - button can modify camera in different modes.
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param sceneId - int - screen for button to direct
     * @param isCameraAdj - boolean - true if this button modifies camera view
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, int sceneId,boolean isCameraAdj){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(CELL_HEIGHT, CELL_HEIGHT);
        this.isCameraAdj = isCameraAdj;
        this.sceneId = sceneId;
    }

    /**
     * constructor for FunctionalButton class - button can direct user to another screen
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param xSize - double - height
     * @param ySize - double - width
     * @param sceneId - int - screen for button to direct
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize,int sceneId){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = null;
        this.sceneId = sceneId;
    }

    /**
     * constructor for FunctionalButton class - button can modify another gameObject
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param gameObject - GameObject - object which this button can modify
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, GameObject gameObject){
        super(id, typeFile);
        setPosition(xPos,yPos);
        size2D = new Vector2(CELL_HEIGHT, CELL_HEIGHT);
        this.gameObj = gameObject;
        this.sceneId = -1;
    }

    /**
     * constructor for FunctionalButton class - button to exit program
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param xSize - double - height
     * @param ySize - double - width
     */
    public FunctionalButton(int id, String typeFile, double xPos, double yPos, double xSize, double ySize){
        super(id, typeFile);
        setPosition(xPos,yPos);
        setSize(xSize,ySize);
        this.gameObj = null;
        this.sceneId = -1;
    }

    /**
     * constructor for FunctionalButton class - button can modify another gameObject
     *
     * @param id - int - unique identifier for object
     * @param typeFile - String -  image file of button
     * @param xPos - double - x position
     * @param yPos - double - y position
     * @param xSize - double - height
     * @param ySize - double - width
     * @param gameObject - GameObject - object which this button can modify
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
        position2D = new Vector2((float) x* CELL_WIDTH,(float) y* CELL_HEIGHT);
    }

    /**
     *sets size for button in scene
     * @param x  - double - x position
     * @param y  - double - y position
     */
    public void setSize(double x, double y){
            size2D = new Vector2((float)x* CELL_WIDTH,(float) y* CELL_HEIGHT);
    }

    /**
     * sets 3d position for camera to zoom in specific tile
     * @param tmp - float[3] - 3d position of camera
     */
    public void setVector3(float[] tmp){vector3 = new Vector3(tmp[0],tmp[1]+10,tmp[2]);}

    /**
     * initiates button object
     */
    @Override
    public void objectInit() {
        super.objectInit();
    }

    /**
     * starts mouse action corresponding functional button
     */
    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        //calls parent's mouse click
        super.MouseDown(x,y,pointer,button);
        //cases where functional button has no object as reference or screen id
        if(this.sceneId == -1 && gameObj == null) {
            //camera button is true
            if(isCameraAdj){
                i++;
                if(i%3==0){
                    //mode 1
                    ((GameScene)ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld()).setDefaultCamera();
                }else if(i%3==1){
                    //mode 2
                    ((GameScene)ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld()).setTileCamera(vector3);
                }else if(i%3==2){
                    //mode 3
                    ((GameScene)ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld()).setTopDownCamera();
                }
            } else {
                //exit button
                Gdx.app.exit();
            }
        }else{
            //functional button has screen id.
            if(this.sceneId != -1){
                if(button == 0){
                    //direct to specific screen.
                    ZickeZacke.getInstance().setScreen(sceneId);
                }
            //functional button has an object as reference.
            }else {
                this.gameObj.setActive(!gameObj.isActive());
            }
        }
    }

}
