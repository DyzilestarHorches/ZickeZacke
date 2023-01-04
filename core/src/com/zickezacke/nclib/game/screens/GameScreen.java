package com.zickezacke.nclib.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.game.screens.helpers.Renderer;

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
        gameWorld.Start();
    }

    //override interface, execute when scene starts, before any render
    @Override
    public void show() {
        if (gameWorld != null){
            gameWorld.Show();
            renderer = new Renderer(gameWorld);
            renderer.Start();
            //Gdx.app.log(Integer.toString(Gdx.graphics.getWidth()),Integer.toString(Gdx.graphics.getHeight()));
            //renderer.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    //runs every delta seconds (every frame)
    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        if (gameWorld != null){
            gameWorld.Update();

        }
        if (renderer != null){
            renderer.render();
        }
    }

    //getters
    public int getId(){
        return this.id;
    }
    public GameWorld getGameWorld(){
        return this.gameWorld;
    }

    //the below is not yet consider / under construction
    @Override
    public void resize(int width, int height) {
        //Gdx.app.log(Integer.toString(width),Integer.toString(height));
        if (renderer != null) renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameWorld.Hide();
        renderer.dispose();
        renderer = null;
        //gameWorld.dispose();
        //gameWorld = null;
        Gdx.app.log("Screen Dispose", Integer.toString(id));
    }

    @Override
    public void dispose() {
        //gameWorld.dispose();
        //renderer.dispose();
    }
}
