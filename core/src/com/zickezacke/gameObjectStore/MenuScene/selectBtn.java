package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;

public class selectBtn extends Btn{
    private static boolean isChoose;
    public selectBtn(int id, String type, float x, float y, float z){
        super(id, type);
        setPosition(x,y,z);
    }

    public void setPosition(float x, float y, float z){
        position2D = new Vector2(x*cellWidth,y*cellHeight);
    }
    @Override
    public void objectInit() {
        super.objectInit();
        size2D = new Vector2((float)0.5 * cellHeight, (float)0.5 * cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        isChoose = !isChoose;
        if(isChoose){
            setTexture(Btn.selected_btn_0);

        }else{
            setTexture(Btn.select_btn_0);
        }
    }
    public static boolean getState(){return isChoose;}
}
