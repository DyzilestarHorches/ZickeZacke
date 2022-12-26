package com.zickezacke.nclib.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.zickezacke.nclib.screens.helpers.GameWorld;
import com.zickezacke.nclib.screens.helpers.Renderer;

//class to create scenes (e.g GameScene, MainMenu,...)
public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private Renderer renderer;
    private int id;
    public GameScreen(){}

    //initiate scene without world => useless, used for testing
    public GameScreen(int id){
        //log
        Gdx.app.log("GameScreen", "Attached");
        this.id = id;
    }
    public GameScreen(int id, GameWorld gameWorld){
        this.id = id;
        this.gameWorld = gameWorld;
    }

    //override interface, execute when scene starts, before any render
    @Override
    public void show() {
        if (gameWorld != null){
            gameWorld.Start();
            renderer = new Renderer(gameWorld);
            renderer.Start();
        }
    }

    //runs every delta seconds (every frame)
    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        if (gameWorld != null){
            gameWorld.Update();
            renderer.render();
        }
    }

    //getters
    public int getId(){
        return this.id;
    }

    //the below is not yet consider / under construction
    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
