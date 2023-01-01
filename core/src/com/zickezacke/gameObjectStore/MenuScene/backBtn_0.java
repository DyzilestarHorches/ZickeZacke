package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;

public class backBtn_0 extends Btn {
    public backBtn_0(int id, String type){super(id,type);}

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2((float) 5.67*cellWidth,(float) 2.5*cellHeight);
        size2D = new Vector2(1*cellHeight,1*cellHeight);
        setActive(false);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(button == 0){
            ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld().getGameObjects().get(8).setActive(false);
            setActive(false);
        }
    }
}
