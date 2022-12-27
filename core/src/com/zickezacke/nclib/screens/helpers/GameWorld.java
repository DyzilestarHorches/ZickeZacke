package com.zickezacke.nclib.screens.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.gameObject.GameObject3D;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//not perfect to the ideology, would be improved when needed!
//helper class of GameScreen, used to store GameObjects
public class GameWorld {
    protected Environment environment;
    protected PerspectiveCamera camera3D;
    protected OrthographicCamera camera2D;
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected List<GameObject3D> gameObjects3D = new ArrayList<>();

    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public GameWorld(){

    }
    //initialize camera, environment and objects for the god to see the world!
    public GameWorld(boolean has3DCamera, boolean has2DCamera){
        if (has3DCamera){
            camera3D = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        }
        if (has2DCamera){
            camera2D = new OrthographicCamera();
        }
        environment = new Environment();

        inputMultiplexer.addProcessor(create2DInputeHandler());
    }

    //camera and environment default set up
    public void Start(){
        if (camera2D != null) {
            camera2D.setToOrtho(true,1024f, 712f);
        }
        else if (camera3D != null) {
            camera3D.position.set(0f, 0f, 0f);
            camera3D.lookAt(0, 0, -1);
            camera3D.update();
        }

        //temporary set environment
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        //Begin is to override and add GameObjects!
        Begin();

        //after adding game objects, Start them!
        for (int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).Start();
        }

        for (int i = 0; i < gameObjects3D.size(); i++){
            gameObjects3D.get(i).Start();
        }
    }

    //called in render, call every gameObject update
    public void Update(){
        for (int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).Update();
        }
        for (int i = 0; i < gameObjects3D.size(); i++) {
            gameObjects3D.get(i).Update();
        }
    }

    //release memory when changeScreen
    public void dispose(){
        for (GameObject gameObject : gameObjects) {
            gameObject.dispose();
            //gameObjects.remove(gameObject);
        }


        for (GameObject3D gameObject3D : gameObjects3D) {
            gameObject3D.dispose();
            //gameObjects3D.remove(gameObject3D);
        }
    }

    //region getters
    public Environment getEnvironment(){
        return this.environment;
    }
    //temporary, should return only the model of game objects
    public List<GameObject> getGameObjects(){
        return gameObjects;
    }
    public List<GameObject3D> getGameObjects3D(){
        return gameObjects3D;
    }
    public boolean hasCamera3D(){
        if (camera3D != null) return true;
        else return false;
    }

    public boolean hasCamera2D(){
        if (camera2D != null) return true;
        else return false;
    }
    public OrthographicCamera getCamera2D(){
        return this.camera2D;
    }

    public PerspectiveCamera getCamera3D(){
        return this.camera3D;
    }

    public InputMultiplexer getInputMultiplexer(){
        return this.inputMultiplexer;
    }
    //endregion
    //override methods
    public void Begin(){}

    //region support method
    public InputAdapter create2DInputeHandler(){
        InputAdapter inputAdapter2D = new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                for (GameObject gameObject: gameObjects) {
                    if (gameObject.checkClick(screenX, screenY))
                        if (button == 0)
                            Gdx.app.log("Input", Integer.toString(gameObject.getId()));
                        gameObject.MouseDown(screenX, screenY);
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                for (GameObject gameObject: gameObjects) {
                    if (gameObject.checkClick(screenX, screenY))
                        gameObject.MouseUp(screenX, screenY);
                }
                return false;
            }
        };
        return inputAdapter2D;
    }
    //endregion
}
