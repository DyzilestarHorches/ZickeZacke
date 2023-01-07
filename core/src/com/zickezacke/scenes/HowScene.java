package com.zickezacke.scenes;


import com.zickezacke.gameObjectStore.MenuScene.Btn;
import com.zickezacke.gameObjectStore.MenuScene.backBtn;
import com.zickezacke.gameObjectStore.MenuScene.howBackGround;
import com.zickezacke.gameObjectStore.demo.textTest;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class HowScene extends GameWorld {
    public HowScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
        gameObjects.add(new howBackGround(201, true));
        gameObjects.add(new backBtn(201, "back_btn"));
        gameObjects.add(new textTest(202, true));
    }
}
