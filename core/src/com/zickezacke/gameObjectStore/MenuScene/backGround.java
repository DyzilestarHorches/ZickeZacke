package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.screens.helpers.UIHelpers;

public class backGround extends GameObject {
    private UIHelpers uiHelpers = new UIHelpers();

    public backGround(int id, boolean isUi){
        super(id, isUi);
        components.add(uiHelpers);
    }

    @Override
    public void objectInit() {
        source2D = "./UI_demo/menu_layout.jpg";
        position2D = new Vector2(0,0);
        size2D = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
