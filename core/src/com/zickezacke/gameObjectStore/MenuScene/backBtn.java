package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject;

public class backBtn extends Btn {
    public backBtn(int id, String type){super(id,type);}

    @Override
    public void objectInit() {
        super.objectInit();

        position2D = new Vector2(1*cellWidth,1*cellHeight);
        size2D = new Vector2(1*cellHeight,1*cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(button == 0){
            ZickeZacke.getInstance().setScreen(1);
        }
    }
}
