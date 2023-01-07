package com.zickezacke.scenes;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.MenuScene.Btn;
import com.zickezacke.gameObjectStore.MenuScene.backBtn_0;
import com.zickezacke.gameObjectStore.MenuScene.backGround;
import com.zickezacke.gameObjectStore.MenuScene.howBtn;
import com.zickezacke.gameObjectStore.MenuScene.notiScene;
import com.zickezacke.gameObjectStore.MenuScene.playBtn;
import com.zickezacke.gameObjectStore.MenuScene.selectBtn;
import com.zickezacke.gameObjectStore.MenuScene.settingBtn;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class MenuScene extends GameWorld {
    public MenuScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    public void Begin(){
        //gameObjects.add(new backGround(102));
        gameObjects.add(new backGround(101));
        gameObjects.add(new playBtn(102,Btn.play_btn));
        gameObjects.add(new settingBtn(103,Btn.setting_btn));
        gameObjects.add(new howBtn(104,Btn.how_btn));
        gameObjects.add(new selectBtn(105,"0",5,3.5));
        gameObjects.add(new selectBtn(105,"1",5.5,3.5));
        gameObjects.add(new selectBtn(105,"2",6,3.5));
        gameObjects.add(new selectBtn(105,"3",6.5,3.5));
        gameObjects.add(new notiScene(109));
        gameObjects.add(new backBtn_0(110,Btn.back_btn));
        gameObjects.add(new Btn(111));
    }

    @Override
    public void worldUpdate() {
        //update number of player
        ZickeZacke.playerList = new boolean[] {((selectBtn)Btn.thisMenuScene.getGameObjects().get(4)).getState(),
                ((selectBtn)Btn.thisMenuScene.getGameObjects().get(5)).getState(),
                ((selectBtn)Btn.thisMenuScene.getGameObjects().get(6)).getState(),
                ((selectBtn)Btn.thisMenuScene.getGameObjects().get(7)).getState()};
    }
}
