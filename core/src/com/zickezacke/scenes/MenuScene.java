package com.zickezacke.scenes;

import com.zickezacke.gameObjectStore.MenuScene.Btn;
import com.zickezacke.gameObjectStore.MenuScene.backGround;
import com.zickezacke.gameObjectStore.MenuScene.howBtn;
import com.zickezacke.gameObjectStore.MenuScene.playBtn;
import com.zickezacke.gameObjectStore.MenuScene.selectBtn0;
import com.zickezacke.gameObjectStore.MenuScene.selectBtn1;
import com.zickezacke.gameObjectStore.MenuScene.selectBtn2;
import com.zickezacke.gameObjectStore.MenuScene.selectBtn3;
import com.zickezacke.gameObjectStore.MenuScene.settingBtn;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class MenuScene extends GameWorld {
    public MenuScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    public void Begin(){
        //gameObjects.add(new backGround(102));
        gameObjects.add(new backGround(101, true));
        gameObjects.add(new playBtn(102,Btn.play_btn));
        gameObjects.add(new settingBtn(103,Btn.setting_btn));
        gameObjects.add(new howBtn(104,Btn.how_btn));
        gameObjects.add(new selectBtn0(105,Btn.select_btn_0));
        gameObjects.add(new selectBtn1(106,Btn.select_btn_1));
        gameObjects.add(new selectBtn2(107,Btn.select_btn_2));
        gameObjects.add(new selectBtn3(108,Btn.select_btn_3));


    }
}
