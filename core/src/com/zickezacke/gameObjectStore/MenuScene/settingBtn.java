package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;

public class settingBtn extends Btn{
    public  settingBtn(int id, String type){super(id,type);}

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2(10*cellWidth,1*cellHeight);
        size2D = new Vector2(cellHeight,cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        ZickeZacke.getInstance().setScreen(3);
    }
}
