package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;


public class howBtn extends Btn {
    public howBtn(int id,String type){super(id,type);}

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2(11*cellWidth,1*cellHeight);
        size2D = new Vector2(cellHeight,cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(button == 0){
            ZickeZacke.getInstance().setScreen(2);
        }
    }
}
