package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;

public class selectBtn extends Btn{
    private static boolean isChoose = false;

    private String type;

    public selectBtn(int id, String type, double x, double y){
        super(id, type);
        setPosition(x,y);
        this.type = String.valueOf(type);
    }

    public void setPosition(double x, double y){
        position2D = new Vector2((float) x*cellWidth,(float) y*cellHeight);
    }
    @Override
    public void objectInit() {
        super.objectInit();
        source2D = "./UI_demo/select_btn_" + type + ".png";
        size2D = new Vector2((float)0.5 * cellHeight, (float)0.5 * cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        isChoose = !isChoose;
        if(isChoose){
            setTexture("./UI_demo/selected_btn_" + type + ".png");
        }else{
            setTexture(source2D);
        }
    }

    public boolean getState(){return isChoose;}
}
