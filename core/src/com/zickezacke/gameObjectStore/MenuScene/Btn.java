package com.zickezacke.gameObjectStore.MenuScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.gameObject.GameObject;

public class Btn extends GameObject {
    public static String play_btn = "./UI_demo/play_btn.png";
    public static String how_btn = "./UI_demo/how_btn.png";
    public static String setting_btn = "./UI_demo/setting_btn.png";
    public static String back_btn = "./UI_demo/back_btn.png";
    public static String exit_btn = "./UI_demo/exit_btn.png";
    public static String rematch_btn = "./UI_demo/rematch_btn.png";
    public static String select_btn_0 = "./UI_demo/select_btn_0.png";
    public static String select_btn_1 = "./UI_demo/select_btn_1.png";
    public static String select_btn_2 = "./UI_demo/select_btn_2.png";
    public static String select_btn_3 = "./UI_demo/select_btn_3.png";
    public static String selected_btn_0 = "./UI_demo/selected_btn_0.png";
    public static String selected_btn_1 = "./UI_demo/selected_btn_1.png";
    public static String selected_btn_2 = "./UI_demo/selected_btn_2.png";
    public static String selected_btn_3 = "./UI_demo/selected_btn_3.png";
    public int cellWidth =  1280/12;
    public int cellHeight = 720/10;
    private String type;

    public GameWorld thisMenuScene;


    public Btn(int id){
        super(id, true);
    }
    public Btn(int id,String type){
        super(id,true);
        this.type = type;
    }

    @Override
    public void objectInit() {
        source2D = type;
    }

    @Override
    public void objectUpdate() {
        thisMenuScene = ZickeZacke.getInstance().getGameScreens().get(1).getGameWorld();
    }
}
