package com.zickezacke.scenes;

import com.badlogic.gdx.Gdx;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class WinnerScene extends GameWorld {
    public WinnerScene(boolean has3DCamera, boolean has2DCamera){
        super(has3DCamera,has2DCamera);
    }

    @Override
    public void Begin() {
       // gameObjects.add(new winnerBackground(901));

    }

    @Override
    public void Show() {
       // winnerPortrait portrait = new winnerPortrait(909,ZickeZacke.winner);
        //gameObjects.add(portrait);
        //portrait.Start();
    }

}
