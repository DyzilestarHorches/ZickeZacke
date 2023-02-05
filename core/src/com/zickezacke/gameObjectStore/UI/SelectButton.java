package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.math.Vector2;
/**
 * SelectButton is used for implement button which can be selected or deselected
 */
public class SelectButton extends Button {
    //variable is used for check or uncheck status.
    private boolean isChoose;

    //type of color for each select button's texture path.
    private String type;

    /**
     * constructor for SelectButton
     * @param id - int - unique identifier for object
     * @param type - String -  image file of button
     * @param x - double - x position
     * @param y - double - y position
     *
     * @return SelectButton
     */
    public SelectButton(int id, String type, double x, double y){
        super(id, type);
        setPosition(x,y);
        this.type = type;
    }

    /**
     * sets selected button position
     *
     * @param x - double - x position
     * @param y - double - y position
     */
    public void setPosition(double x, double y){
        position2D = new Vector2((float) x* CELL_WIDTH,(float) y* CELL_HEIGHT);
    }

    /**
     * initiates selected button
     */
    @Override
    public void objectInit() {
        super.objectInit();
        //initiates texture for button based on type path.
        source2D = "./UI/select_btn_" + type + ".png";
        //initiates size for button.
        size2D = new Vector2((float)0.5 * CELL_HEIGHT, (float)0.5 * CELL_HEIGHT);
    }

    /**
     * clicks for choosing number and color of players.
     */
    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        super.MouseDown(x, y, pointer, button); //add this down here
        //switches isChoose status
        isChoose = !isChoose;
        //changes texture based on isChoose status.
        if(isChoose){
            setTexture("./UI/selected_btn_" + type + ".png");
        }else{
            setTexture(source2D);
        }
    }

    /**
     * gets selected button state
     * @return isChoose - boolean - value specifies that button is selected or not
     */
    public boolean getState(){return isChoose;}
}
