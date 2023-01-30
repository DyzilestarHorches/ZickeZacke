package com.zickezacke.scenes;


import com.badlogic.gdx.Gdx;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.DragBar;
import com.zickezacke.gameObjectStore.UI.DragButton;
import com.zickezacke.gameObjectStore.UI.ToggleButton;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import java.util.ArrayList;
import java.util.List;

public class SettingScene extends GameWorld {
    private List<DragButton> dragButtonList = new ArrayList<>();
    private ToggleButton toggleCuctacButton;
    private ToggleButton toggleNextPlayerButton;
    private float cuctacVal = (ZickeZacke.getSoundSystem().getIsCucTaCucTac()) ? 0.8f : 0;
    private float nextPlayerVal = (ZickeZacke.getSoundSystem().getTheNextOne()) ? 0.8f : 0;
    public SettingScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
        gameObjects.add(new BackGround(301, "setting_background"));
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.inGame));
        dragButtonList.add(new DragButton(303,ZickeZacke.brightnessVol,6));
        dragButtonList.add(new DragButton(304,ZickeZacke.getSoundSystem().getVolume(),5));
        toggleCuctacButton = new ToggleButton(305,cuctacVal,4);
        toggleNextPlayerButton = new ToggleButton(306,nextPlayerVal,3);

        gameObjects.add(new DragBar(306,3,toggleNextPlayerButton));
        gameObjects.add(new DragBar(305,4,toggleCuctacButton));
        gameObjects.add(new DragBar(304,5,dragButtonList.get(1)));
        gameObjects.add(new DragBar(303,6,dragButtonList.get(0)));

        for(DragButton i : dragButtonList){gameObjects.add(i);}
        gameObjects.add(toggleCuctacButton);
        gameObjects.add(toggleNextPlayerButton);
    }

    @Override
    public void worldUpdate() {
        super.worldUpdate();
        ZickeZacke.brightnessVol = dragButtonList.get(0).getValue();
        ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().setEnvironment(ZickeZacke.brightnessVol);
        ZickeZacke.getSoundSystem().setVolume(dragButtonList.get(1).getValue());
        nextPlayerVal = (ZickeZacke.getSoundSystem().getTheNextOne()) ? 0.8f : 0;
        cuctacVal = (ZickeZacke.getSoundSystem().getIsCucTaCucTac()) ? 0.8f : 0;
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.ingame));
    }
}
