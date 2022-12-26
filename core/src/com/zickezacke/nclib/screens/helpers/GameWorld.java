package com.zickezacke.nclib.screens.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.zickezacke.nclib.gameObject.GameObject;


import java.util.ArrayList;
import java.util.List;

//not perfect to the ideology, would be improved when needed!
//helper class of GameScreen, used to store GameObjects
public class GameWorld {
    protected Environment environment;
    protected PerspectiveCamera camera3D;
    protected OrthographicCamera camera2D;
    protected List<GameObject> gameObjects = new ArrayList<>();

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
    }

    //called in render, call every gameObject update
    public void Update(){
        for (int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).Update();
        }
    }

    //getters
    public Environment getEnvironment(){
        return this.environment;
    }
    //temporary, should return only the model of game objects
    public List<GameObject> getGameObjects(){
        return gameObjects;
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

    //override methods
    public void Begin(){}
}
