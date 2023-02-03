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

/**
 * MenuScene class is used for creating main menu
 */
public class MenuScene extends GameWorld {
    /**
     * Constructor for MenuScene class
     *
     * @param has3DCamera - boolean - the scene has 3d camera
     * @param has2DCamera - boolean - the scene has 2d camera
     *
     * @return MenuScene - a scene for game menu
     */
    public MenuScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }
    //creates a notification background.
    private NotiBackground notiPlayer = new NotiBackground(109,"noti_scene");
    //create back button the notification background.
    private FunctionalButton backBtn = new FunctionalButton(110,"back_btn",5.76,2.5,notiPlayer);
    private List<SelectButton> selectButtonList = new ArrayList<>();
    /**
     * adds objects into scene
     */
    public void Begin(){
        //adds background
        gameObjects.add(new BackGround(101,"main_menu_background"));
        //adds play button
        gameObjects.add(new PlayButton(102,"play_btn"));
        //adds setting button
        gameObjects.add(new FunctionalButton(103,"setting_btn",10,1,3));
        //adds how button
        gameObjects.add(new FunctionalButton(104,"how_btn",11,1,2));
        //adds list of select buttons
        selectButtonList.add(new SelectButton(105,"0",5,3.5));
        selectButtonList.add(new SelectButton(105,"1",5.5,3.5));
        selectButtonList.add(new SelectButton(105,"2",6,3.5));
        selectButtonList.add(new SelectButton(105,"3",6.5,3.5));
        for(SelectButton i : selectButtonList){gameObjects.add(i);}
        //adds notification for number of players.
        gameObjects.add(notiPlayer);
        //adds back button for the notification.
        gameObjects.add(backBtn);
        //gameObjects.add(new Button(111));
    }

    @Override
    /**
     * updates list of select buttons
     */
    public void worldUpdate() {
        //inactivates notification when hits back button in the notification.
        backBtn.setActive(notiPlayer.isActive());
        //updates number of players.
        ZickeZacke.playerList = new boolean[] {selectButtonList.get(0).getState(),
                                                selectButtonList.get(1).getState(),
                                                selectButtonList.get(2).getState(),
                                                selectButtonList.get(3).getState()};
    }
}
