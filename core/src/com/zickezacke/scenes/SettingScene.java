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
/**
 * setting scene class is used to implement setting menu
 */
public class SettingScene extends GameWorld {
    //list of button to drag.
    private List<DragButton> dragButtonList = new ArrayList<>();

    //buttons to toggle.
    private ToggleButton toggleCuctacButton;
    private ToggleButton toggleNextPlayerButton;

    //float values that the sound variable should be mapped.
    private float cuctacVal = (ZickeZacke.getSoundSystem().getIsCucTaCucTac()) ? 0.8f : 0;
    private float nextPlayerVal = (ZickeZacke.getSoundSystem().getTheNextOne()) ? 0.8f : 0;

    /**
     * Constructor for SettingScene class
     *
     * @param has3DCamera - boolean - the scene has 3d camera
     * @param has2DCamera - boolean - the scene has 2d camera
     */
    public SettingScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    /**
     * adds objects into scene
     */
    @Override
    public void Begin() {
        //adds background
        gameObjects.add(new BackGround(301, "setting_background"));
        //adds back button
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.inGame));
        //adds drag button for brightness modification
        dragButtonList.add(new DragButton(303,ZickeZacke.brightnessVol,6));
        //adds drag button for sound modification
        dragButtonList.add(new DragButton(304,ZickeZacke.getSoundSystem().getVolume(),5));
        //creates toggle button for chicken sound modification
        toggleCuctacButton = new ToggleButton(305,cuctacVal,4);
        //creates toggle button for next player notification sound modification
        toggleNextPlayerButton = new ToggleButton(306,nextPlayerVal,3);

        //adds UI elements for displaying drag button's value
        gameObjects.add(new DragBar(306,3,toggleNextPlayerButton));
        gameObjects.add(new DragBar(305,4,toggleCuctacButton));
        gameObjects.add(new DragBar(304,5,dragButtonList.get(1)));
        gameObjects.add(new DragBar(303,6,dragButtonList.get(0)));

        //adds dragButton list into the setting scene.
        for(DragButton i : dragButtonList){gameObjects.add(i);}
        //adds toggle button for chicken sound modification
        gameObjects.add(toggleCuctacButton);
        //adds toggle button for next player notification sound modification
        gameObjects.add(toggleNextPlayerButton);
    }

    /**
     * updates value that are modified.
     */
    @Override
    public void worldUpdate() {
        super.worldUpdate();
        //updates brightness after each modification.
        ZickeZacke.brightnessVol = dragButtonList.get(0).getValue();
        ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().setEnvironment(ZickeZacke.brightnessVol);
        //updates sound after each modification.
        ZickeZacke.getSoundSystem().setVolume(dragButtonList.get(1).getValue());
        //updates sound of next player notification after each modification.
        nextPlayerVal = (ZickeZacke.getSoundSystem().getTheNextOne()) ? 0.8f : 0;
        //updates cuctac sound after each modification.
        cuctacVal = (ZickeZacke.getSoundSystem().getIsCucTaCucTac()) ? 0.8f : 0;
        //updates scene that back button should return.
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.inGame));
    }
}
