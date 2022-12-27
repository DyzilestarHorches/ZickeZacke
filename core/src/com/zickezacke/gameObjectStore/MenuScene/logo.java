package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.gameObject.GameObject;

public class logo extends GameObject {
    public logo(int id, boolean isUi){
        super(id, isUi);
    }

    @Override
    public void objectInit() {
        source2D = "./UI_demo/logo@4x.png";
        position2D = new Vector2(1280/12*5, 720/10*6);
        size2D = new Vector2(1280/12*2, 720/10*3);
    }

    @Override
    public void MouseDown(int x, int y, int cursor, int button) {
        if (button == 0)
            ZickeZacke.getInstance().setScreen(0);
    }
}
