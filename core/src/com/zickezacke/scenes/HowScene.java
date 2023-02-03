package com.zickezacke.scenes;

import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.UI.FunctionalButton;
import com.zickezacke.gameObjectStore.UI.BackGround;
import com.zickezacke.gameObjectStore.demo.textTest;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
/**
 *HowScene class is used to implement game instruction menu
 */
public class HowScene extends GameWorld {
    /**
     * Constructor for HowScene class
     *
     * @param has3DCamera - boolean - the scene has 3d camera
     * @param has2DCamera - boolean - the scene has 2d camera
     *
     * @return HowScene - a scene for game instruction
     */
    public HowScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    /**
     * adds objects into HowScene
     */
    public void Begin() {
        // adds background
        gameObjects.add(new BackGround(201, "how_background"));
        // adds back button
        gameObjects.add(new FunctionalButton(201, "back_btn",1,1, ZickeZacke.inGame));
    }

    @Override
    /**
     * updates scene that back button should return.
     */
    public void worldUpdate() {
        super.worldUpdate();
        gameObjects.add(new FunctionalButton(301, "back_btn",1,1, ZickeZacke.inGame));
    }
}
