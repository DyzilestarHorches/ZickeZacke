package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;

public class playBtn extends Btn{
    public playBtn(int id, String type){super(id, type);}

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2(5*cellWidth, 2*cellHeight);
        size2D = new Vector2(2*cellWidth,1*cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        boolean[] arr = {selectBtn0.getState(),
                selectBtn1.getState(),
                selectBtn2.getState(),
                selectBtn3.getState()};
        if(button == 0){
            for(boolean i : arr){Gdx.app.log("player join: ", String.valueOf(i));}
            ZickeZacke.getInstance().setScreen(0);
        }
    }
}
