package com.zickezacke.gameObjectStore.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.scenes.SettingScene;

public class dragZone extends GameObject {
    private final int cellWidth =  1280/12;
    private final int cellHeight = 720/10;
    private final int minLength = 50;
    public float value = 50;      // default starting value
    private boolean trackMouse = false;
    private Vector3 tmp = new Vector3();


    public dragZone(int id){
        super(id, true);
    }

    @Override
    public void objectInit() {
        source2D = "./UI_demo/select_btn_0.9.png";
        position2D.set(5*cellWidth-5, 5*cellHeight-10);
        size2D.set(value * 8, 50);
    }



    @Override
    public void objectUpdate() {
        SettingScene settingScene = (SettingScene) ZickeZacke.getInstance().getGameScreens().get(3).getGameWorld();
        OrthographicCamera camera2D = settingScene.getCamera2D();
        if (trackMouse){

            value = Gdx.input.getX() - position2D.x;
            //camera2D.unproject(tmp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            size2D.set(value, size2D.y);
        }
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        SettingScene settingScene = (SettingScene) ZickeZacke.getInstance().getGameScreens().get(3).getGameWorld();
        OrthographicCamera camera2D = settingScene.getCamera2D();
        //camera2D.unproject(tmp.set(x, y, 0));
        if (button == 0){
            trackMouse = true;
        }
    }

    @Override
    public void MouseUp(int x, int y, int pointer, int button) {
        if (button == 0 && trackMouse){
            trackMouse = false;
        }
    }
}
