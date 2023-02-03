package com.zickezacke.nclib.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;
import com.zickezacke.nclib.game.screens.helpers.Renderer;

/**
 * class to create scenes (e.g GameScene, MainMenu,...)
 */
public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private Renderer renderer;
    private int id;
    public GameScreen(){}

    /**
     * initiates scene without world => useless, used for testing
     * @param id unique identifier to set/change scene
     */
    public GameScreen(int id){
        //log
        Gdx.app.log("GameScreen", "Attached");
        this.id = id;
    }

    /**
     * initiates scene
     * @param id unique identifier to set/change scene
     * @param gameWorld where stores the game objects
     */
    public GameScreen(int id, GameWorld gameWorld){
        this.id = id;
        this.gameWorld = gameWorld;
        gameWorld.Start();
    }

    /**
     * override interface, execute when scene starts, before any render
     */
    @Override
    public void show() {
        if (gameWorld != null){
            gameWorld.Show();
            renderer = new Renderer(gameWorld);
            renderer.Start();
        }
    }

    /**
     * runs every delta seconds (every frame)
     * @param delta
     */
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

    /**
     * runs when GameScreen stops (switch to another one)
     */
    @Override
    public void hide() {
        gameWorld.Hide();
        renderer.dispose();
        renderer = null;
    }

    /**
     * reacts to resize of window (not yet fully functioned)
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        if (renderer != null) renderer.resize(width, height);
    }

    //getters
    public int getId(){
        return this.id;
    }
    public GameWorld getGameWorld(){
        return this.gameWorld;
    }
    //the below comings are not yet consider / under construction

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }



    @Override
    public void dispose() {
        //gameWorld.dispose();
        //renderer.dispose();
    }
}
