/**
 * SelectButton is used for implement button which can be selected or deselected
 */
package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.math.Vector2;

public class SelectButton extends Button {
    private boolean isChoose;
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
        position2D = new Vector2((float) x*cellWidth,(float) y*cellHeight);
    }
    @Override
    /**
     * initiates selected button
     */
    public void objectInit() {
        super.objectInit();
        source2D = "./UI/select_btn_" + type + ".png";
        size2D = new Vector2((float)0.5 * cellHeight, (float)0.5 * cellHeight);
    }

    @Override
    /**
     * changes texture for clicked button
     */
    public void MouseDown(int x, int y, int pointer, int button) {
        isChoose = !isChoose;
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
