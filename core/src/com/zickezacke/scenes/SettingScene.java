package com.zickezacke.scenes;


import com.badlogic.gdx.Gdx;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.DragBar;
import com.zickezacke.gameObjectStore.UI.DragButton;
import com.zickezacke.gameObjectStore.UI.DragCloudButton;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import java.util.ArrayList;
import java.util.List;

public class SettingScene extends GameWorld {
    private List<DragButton> dragButtonList = new ArrayList<>();
    private DragCloudButton dragCloudButton;
    public SettingScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
        gameObjects.add(new BackGround(301, "setting_background"));
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.ingame));
        dragButtonList.add(new DragButton(303,ZickeZacke.brightnessVol,6));
        dragButtonList.add(new DragButton(304,ZickeZacke.getSoundSystem().getVolume(),5));
        dragCloudButton = new DragCloudButton(305);

        gameObjects.add(new DragBar(302,4,dragCloudButton));
        gameObjects.add(new DragBar(302,5,dragButtonList.get(1)));
        gameObjects.add(new DragBar(302,6,dragButtonList.get(0)));

        for(DragButton i : dragButtonList){gameObjects.add(i);}
        gameObjects.add(dragCloudButton);
    }

    @Override
    public void worldUpdate() {
        super.worldUpdate();
        ZickeZacke.brightnessVol = dragButtonList.get(0).getValue();
        ZickeZacke.getSoundSystem().setVolume(dragButtonList.get(1).getValue());
        ZickeZacke.isCloud = dragCloudButton.isCloud;
        //Gdx.app.log("bright==========================",String.valueOf(ZickeZacke.brightnessVol));
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.ingame));
    }
}
