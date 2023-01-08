package com.zickezacke.scenes;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.Button;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.gameObjectStore.UI.PlayButton;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.gameObjectStore.UI.NotiBackground;
import com.zickezacke.gameObjectStore.UI.SelectButton;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject;
import java.util.ArrayList;
import java.util.List;

public class MenuScene extends GameWorld {
    public MenuScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    private NotiBackground notiPlayer = new NotiBackground(109,"noti_scene");
    private FunctionalButton backBtn = new FunctionalButton(110,"back_btn",5.76,2.5,notiPlayer);
    private List<SelectButton> selectButtonList = new ArrayList<>();

    public void Begin(){
        //gameObjects.add(new backGround(102));
        gameObjects.add(new BackGround(101,"main_menu_background"));
        gameObjects.add(new PlayButton(102,"play_btn"));
        gameObjects.add(new FunctionalButton(103,"setting_btn",10,1,3));
        gameObjects.add(new FunctionalButton(104,"how_btn",11,1,2));
        selectButtonList.add(new SelectButton(105,"0",5,3.5));
        selectButtonList.add(new SelectButton(105,"1",5.5,3.5));
        selectButtonList.add(new SelectButton(105,"2",6,3.5));
        selectButtonList.add(new SelectButton(105,"3",6.5,3.5));
        for(SelectButton i : selectButtonList){gameObjects.add(i);}
        gameObjects.add(notiPlayer);
        gameObjects.add(backBtn);
        gameObjects.add(new Button(111));
    }

    @Override
    public void worldUpdate() {
        backBtn.setActive(notiPlayer.isActive());
        //update number of player
        ZickeZacke.playerList = new boolean[] {selectButtonList.get(0).getState(),
                                                selectButtonList.get(1).getState(),
                                                selectButtonList.get(2).getState(),
                                                selectButtonList.get(3).getState()};
    }
}
