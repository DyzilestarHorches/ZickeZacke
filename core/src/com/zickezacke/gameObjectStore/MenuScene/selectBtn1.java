package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;

public class selectBtn1 extends Btn{
    private static boolean isChoose;
    public selectBtn1(int id, String type){super(id,type);}

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2((float) 5.5*cellWidth, (float)3.5 * cellHeight);
        size2D = new Vector2((float)0.5 * cellHeight, (float)0.5 * cellHeight);
    }
    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        isChoose = !isChoose;
        if(isChoose){
            setTexture(Btn.selected_btn_1);
        }else{
            setTexture(Btn.select_btn_1);
        }
    }
    static public boolean getState(){return isChoose;}

}