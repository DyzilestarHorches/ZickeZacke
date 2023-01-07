package com.zickezacke.scenes;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.MenuScene.Button;
import com.zickezacke.gameObjectStore.MenuScene.DirectButton;
import com.zickezacke.gameObjectStore.MenuScene.BackButton;
import com.zickezacke.gameObjectStore.MenuScene.backGround;
import com.zickezacke.gameObjectStore.MenuScene.notiScene;
import com.zickezacke.gameObjectStore.MenuScene.playButton;
import com.zickezacke.gameObjectStore.MenuScene.selectButton;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class MenuScene extends GameWorld {
    public MenuScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    public void Begin(){
        //gameObjects.add(new backGround(102));
        gameObjects.add(new backGround(101));
        gameObjects.add(new playButton(102,"play_btn"));
        gameObjects.add(new DirectButton(103,"setting_btn",10,1,3));
        gameObjects.add(new DirectButton(104,"how_btn",11,1,2));
        gameObjects.add(new selectButton(105,"0",5,3.5));
        gameObjects.add(new selectButton(105,"1",5.5,3.5));
        gameObjects.add(new selectButton(105,"2",6,3.5));
        gameObjects.add(new selectButton(105,"3",6.5,3.5));
        gameObjects.add(new notiScene(109));
        gameObjects.add(new BackButton(110,"back_btn"));
        gameObjects.add(new Button(111));
    }

    @Override
    public void worldUpdate() {
        //update number of player
        ZickeZacke.playerList = new boolean[] {((selectButton) Button.thisMenuScene.getGameObjects().get(4)).getState(),
                ((selectButton) Button.thisMenuScene.getGameObjects().get(5)).getState(),
                ((selectButton) Button.thisMenuScene.getGameObjects().get(6)).getState(),
                ((selectButton) Button.thisMenuScene.getGameObjects().get(7)).getState()};
    }
}
