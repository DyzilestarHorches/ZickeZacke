package com.zickezacke.nclib.screens.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.gameObject.import3D.Instance3D;



import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private GameWorld gameWorld;
    private PerspectiveCamera camera3D;
    private Environment environment;
    private ModelBatch modelBatch;
    private CameraInputController camera3DController;

    private OrthographicCamera camera2D;
    private SpriteBatch spriteBatch;
    private CameraInputController camera2DController;

    //ViewPort
    private Viewport viewport2D;
    private Viewport viewport3D;

    public Renderer(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    public void Start(){
        //3D Camera
        if (gameWorld.hasCamera3D()){
            this.camera3D = gameWorld.getCamera3D();
            modelBatch = new ModelBatch();
            camera3DController = new CameraInputController(camera3D);
            Gdx.input.setInputProcessor(camera3DController);
            viewport3D = new ScreenViewport(camera3D);
        }
        //2D Camera
        if (gameWorld.hasCamera2D()){
            this.camera2D = gameWorld.getCamera2D();
            spriteBatch = new SpriteBatch();
            camera2DController = new CameraInputController(camera2D);
            viewport2D = new ScreenViewport(camera2D);
        }


        this.environment = gameWorld.getEnvironment();
    }

    public void render(){
        if (camera3DController != null) camera3DController.update();

        //load game objects
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects = gameWorld.getGameObjects();

        //2D render background
        if (gameWorld.hasCamera2D()){
            spriteBatch.begin();
            for (int i = 0; i < gameObjects.size(); i++){
                Texture tmpTexture = gameObjects.get(i).getTexture();
                if (tmpTexture != null && !gameObjects.get(i).isUI()) {
                    Vector2 position = gameObjects.get(i).getPosition2D();
                    Vector2 size = gameObjects.get(i).getSize2D();
                    spriteBatch.draw(tmpTexture, position.x, position.y, size.x, size.y);
                }
            }
            spriteBatch.end();
        }

        //3D render
        if (gameWorld.hasCamera3D()){
            modelBatch.begin(camera3D);
            for (int i = 0; i < gameObjects.size(); i++){
                Instance3D tmpInstance = gameObjects.get(i).getModel();
                if (tmpInstance != null) modelBatch.render(tmpInstance, environment);
            }
            modelBatch.end();
        }

        //2D render UI
        if (gameWorld.hasCamera2D()){
            spriteBatch.begin();
            for (int i = 0; i < gameObjects.size(); i++){
                Texture tmpTexture = gameObjects.get(i).getTexture();
                if (tmpTexture != null && gameObjects.get(i).isUI()) {
                    Vector2 position = gameObjects.get(i).getPosition2D();
                    Vector2 size = gameObjects.get(i).getSize2D();
                    spriteBatch.draw(tmpTexture, position.x, position.y, size.x, size.y);
                }
            }
            spriteBatch.end();
        }

    }
    public void resize(int width, int height){
        if (gameWorld.hasCamera2D()) viewport2D.update(width, height);
        if (gameWorld.hasCamera3D()) viewport3D.update(width, height);
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects = gameWorld.getGameObjects();
        for (GameObject gameObject: gameObjects) {
            gameObject.resize(width, height);
        }
    }
}
