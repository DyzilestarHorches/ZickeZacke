package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;

public class selectBtn2 extends Btn{
    private static boolean isChoose;
    public selectBtn2(int id, String type){super(id,type);}

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2((float)6*cellWidth, (float)3.5 * cellHeight);
        size2D = new Vector2((float)0.5 * cellHeight, (float)0.5 * cellHeight);
    }
    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        isChoose = !isChoose;
        if(isChoose){
            setTexture(Btn.selected_btn_2);
        }else{
            setTexture(Btn.select_btn_2);
        }
    }
    static public boolean getState(){return isChoose;}

}
