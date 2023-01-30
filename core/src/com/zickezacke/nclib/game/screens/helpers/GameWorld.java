package com.zickezacke.nclib.game.screens.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
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

        if (camera2D != null)inputMultiplexer.addProcessor(create2DInputHandler());
        if (camera3D != null)inputMultiplexer.addProcessor(create3DInputHandler());
    }

    //camera and environment default set up
    public void Start(){
        if (camera2D != null) {
            camera2D.setToOrtho(true,1024f, 712f);
        }
        else if (camera3D != null) {
            camera3D.position.set(0f, 0, 0f);
            camera3D.lookAt(0, 0, -1);
            camera3D.update();
        }

        //temporary set environment
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, 0f, -1f, 1.5f));
        environment.add(new DirectionalLight().set(0.2f, 0.2f, 0.2f, 0f, -2f, 0f));
        environment.add(new DirectionalLight().set(0.4f, 0.3f, 0.2f, 10f, -10f, 10f));
        //Begin is to override and add GameObjects!
        Begin();

        //after adding game objects, Start them!
        for (int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).Start();
        }

        for (int i = 0; i < gameObjects3D.size(); i++){
            gameObjects3D.get(i).Start();
        }
        if (camera3D != null) {
            camera3D.lookAt(0,-1f,0.25f);
            camera3D.position.set(0f, 22f, 7f);
            camera3D.rotate(new Vector3(0,1,0),180);
        }

    }

    //called in render, call every gameObject update
    public void Update(){
        worldUpdate();


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

    //region override methods
    public void worldUpdate(){}

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
    public void Show(){}
    public void Hide(){}
    //region support method
    public InputAdapter create2DInputHandler(){
        InputAdapter inputAdapter2D = new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Gdx.graphics.getHeight() - screenY;
                for (int i = gameObjects.size() - 1; i >= 0; i--) {
                    if (!gameObjects.get(i).isActive()) continue;
                        if (gameObjects.get(i).checkClick(screenX, screenY)) {
                            gameObjects.get(i).MouseDown(screenX, screenY, pointer, button);
                            return true;
                        }
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                for (int i = gameObjects.size() - 1; i >= 0; i--) {
                    if (!gameObjects.get(i).isActive()) continue;
                    if (true) {
                        gameObjects.get(i).MouseUp(screenX, screenY, pointer, button);
                       // return true;
                    }
                }
                return false;
            }
        };
        return inputAdapter2D;
    }

    public static boolean isIntersect (Ray ray, BoundingBox box, Vector3 intersection) {
        Vector3 v1 = new Vector3();
        Vector3 v2 = new Vector3();
        if (box.contains(ray.origin)) {
            if (intersection != null) intersection.set(ray.origin);
            return true;
        }
        float lowest = 0, t;
        boolean hit = false;

        // min x
        if (ray.origin.x <= box.min.x && ray.direction.x > 0) {
            t = (box.min.x - ray.origin.x) / ray.direction.x;
            if (t >= 0) {
                v2.set(ray.direction).scl(t).add(ray.origin);
                if (v2.y >= box.min.y && v2.y <= box.max.y && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                    hit = true;
                    lowest = t;
                }
            }
        }
        // max x
        if (ray.origin.x >= box.max.x && ray.direction.x < 0) {
            t = (box.max.x - ray.origin.x) / ray.direction.x;
            if (t >= 0) {
                v2.set(ray.direction).scl(t).add(ray.origin);
                if (v2.y >= box.min.y && v2.y <= box.max.y && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                    hit = true;
                    lowest = t;
                }
            }
        }
        // min y
        if (ray.origin.y <= box.min.y && ray.direction.y > 0) {
            t = (box.min.y - ray.origin.y) / ray.direction.y;
            if (t >= 0) {
                v2.set(ray.direction).scl(t).add(ray.origin);
                if (v2.x >= box.min.x && v2.x <= box.max.x && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                    hit = true;
                    lowest = t;
                }
            }
        }
        // max y
        if (ray.origin.y >= box.max.y && ray.direction.y < 0) {
            t = (box.max.y - ray.origin.y) / ray.direction.y;
            if (t >= 0) {
                v2.set(ray.direction).scl(t).add(ray.origin);
                if (v2.x >= box.min.x && v2.x <= box.max.x && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                    hit = true;
                    lowest = t;
                }
            }
        }
        // min z
        if (ray.origin.z <= box.min.z && ray.direction.z > 0) {
            t = (box.min.z - ray.origin.z) / ray.direction.z;
            if (t >= 0) {
                v2.set(ray.direction).scl(t).add(ray.origin);
                if (v2.x >= box.min.x && v2.x <= box.max.x && v2.y >= box.min.y && v2.y <= box.max.y && (!hit || t < lowest)) {
                    hit = true;
                    lowest = t;
                }
            }
        }
        // max z
        if (ray.origin.z >= box.max.z && ray.direction.z < 0) {
            t = (box.max.z - ray.origin.z) / ray.direction.z;
            if (t >= 0) {
                v2.set(ray.direction).scl(t).add(ray.origin);
                if (v2.x >= box.min.x && v2.x <= box.max.x && v2.y >= box.min.y && v2.y <= box.max.y && (!hit || t < lowest)) {
                    hit = true;
                    lowest = t;
                }
            }
        }
        if (hit && intersection != null) {
            intersection.set(ray.direction).scl(lowest).add(ray.origin);
            if (intersection.x < box.min.x) {
                intersection.x = box.min.x;
            } else if (intersection.x > box.max.x) {
                intersection.x = box.max.x;
            }
            if (intersection.y < box.min.y) {
                intersection.y = box.min.y;
            } else if (intersection.y > box.max.y) {
                intersection.y = box.max.y;
            }
            if (intersection.z < box.min.z) {
                intersection.z = box.min.z;
            } else if (intersection.z > box.max.z) {
                intersection.z = box.max.z;
            }
        }
        return hit;
    }

    public GameObject3D getObject (int screenX, int screenY) {
        camera3D.update();
        Ray ray = camera3D.getPickRay(screenX, screenY);
        float distance = -1;
        int choose = -1;

        for (int i = 0; i < gameObjects3D.size(); i++){
            BoundingBox bb = gameObjects3D.get(i).getBounds();
            Vector3 intersect = new Vector3();
            if (isIntersect(ray, bb, intersect)){
                float tmpDist = ray.origin.dst(intersect);
                if (distance == -1 ||  tmpDist < distance){
                    distance = tmpDist;
                    choose = i;
                }
            }
        }
        if (choose != -1) return gameObjects3D.get(choose);
        return null;
    }

    public InputAdapter create3DInputHandler(){
        InputAdapter inputAdapter3D = new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                GameObject3D gameObject3D = getObject(screenX, screenY);
                if (gameObject3D != null) gameObject3D.MouseDown(screenX, screenY, pointer, button);
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                GameObject3D gameObject3D = getObject(screenX, screenY);
                if (gameObject3D != null) gameObject3D.MouseUp(screenX, screenY, pointer, button);
                return false;
            }
        };
        return inputAdapter3D;
    }
    public void setEnvironment(float value){
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, value, value, value, 1f));
    }
    public void setDefaultCamera(){
        if (camera3D != null) {
            camera3D.position.set(0f, 22f, 7f);
            camera3D.lookAt(0,-1f,0.25f);
        }
    }
    public void setTopDownCamera(){
        if (camera3D != null) {
            camera3D.position.set(0f, 22f, 0f);
            camera3D.lookAt(0,-1f,0f);
        }
    }
    public void setTileCamera(Vector3 tmp){
        if (camera3D != null) {
            camera3D.position.set(tmp);
        }
    }
    //endregion
}
