package com.zickezacke.nclib.game.screens.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.component.Component;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.nclib.gameObject.TextObject;
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
    private ShapeRenderer shapeRenderer;

    //ViewPort
    private Viewport viewport2D;
    private Viewport viewport3D;

    public Renderer(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    public void Start(){
        //input handler
        InputMultiplexer multiplexer = gameWorld.getInputMultiplexer();
        //3D Camera
        if (gameWorld.hasCamera3D()){
            this.camera3D = gameWorld.getCamera3D();
            modelBatch = new ModelBatch();
            camera3DController = new CameraInputController(camera3D);

            if (ZickeZacke.DEVELOPER_MODE) multiplexer.addProcessor(camera3DController);

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

        Gdx.input.setInputProcessor(multiplexer);
    }

    public void render(){
        if (camera3DController != null) camera3DController.update();

        //load game objects
        List<GameObject> gameObjects;
        gameObjects = gameWorld.getGameObjects();

        //load 3D game objects
        List<GameObject3D> gameObjects3D;
        gameObjects3D = gameWorld.getGameObjects3D();

        //place for components
        List<Component> components = new ArrayList<>();

        //2D render background
        if (spriteBatch != null){
            spriteBatch.begin();
            for (int i = 0; i < gameObjects.size(); i++){
                if(!gameObjects .get(i).isActive()) continue;
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
        if (modelBatch != null){
            modelBatch.begin(camera3D);
            for (int i = 0; i < gameObjects3D.size(); i++){
                if (!gameObjects3D.get(i).isActive()) continue;
                Instance3D tmpInstance = gameObjects3D.get(i).getModel();
                //Gdx.app.log("Screen id", Integer.toString(gameObjects3D.get(i).getId()));
                if (tmpInstance != null) modelBatch.render(tmpInstance, environment);

                //components render
                components = gameObjects3D.get(i).getComponents();
                for (Component component: components){
                    component.Render();
                }
            }
            modelBatch.end();
        }

        if (modelBatch != null){

            for (int i = 0; i < gameObjects3D.size(); i++) {
                if (!gameObjects3D.get(i).isActive()) continue;
                //components render
                components = gameObjects3D.get(i).getComponents();
                for (Component component : components) {
                    component.Render();
                }
            }
        }

        //2D render UI
        if (gameWorld.hasCamera2D()){
            spriteBatch.begin();
            for (int i = 0; i < gameObjects.size(); i++){
                if(!gameObjects.get(i).isActive()) continue;
                Texture tmpTexture = gameObjects.get(i).getTexture();
                if (tmpTexture != null && gameObjects.get(i).isUI()) {
                    Vector2 position = gameObjects.get(i).getPosition2D();
                    Vector2 size = gameObjects.get(i).getSize2D();
                    spriteBatch.draw(tmpTexture, position.x, position.y, size.x, size.y);
                }

                if (gameObjects.get(i).isText()) {
                    TextObject textObject = (TextObject) gameObjects.get(i);
                    Vector2 position = gameObjects.get(i).getPosition2D();
                    //Vector2 size = gameObjects.get(i).getSize2D();
                    BitmapFont bitmapFont = textObject.getBitmapFont();
                    bitmapFont.draw(spriteBatch, textObject.getTextValue(),position.x, position.y);
                }
            }
            spriteBatch.end();
        }
        if (gameWorld.hasCamera3D() && ZickeZacke.DEVELOPER_MODE){
            if (shapeRenderer == null) shapeRenderer = new ShapeRenderer();
            shapeRenderer.setProjectionMatrix(camera3D.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line(0,10, 0, 0, -10, 0);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.line(10, 0, 0, -10, 0,0);
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.line(0, 0, 10, 0, 0, -10);
            shapeRenderer.end();
        }
    }
    public void resize(int width, int height){
        if (gameWorld.hasCamera2D()) viewport2D.update(width, height);
        if (gameWorld.hasCamera3D()) viewport3D.update(width, height);
        List<GameObject> gameObjects;
        gameObjects = gameWorld.getGameObjects();
        for (GameObject gameObject: gameObjects) {
            gameObject.resize(width, height);
        }

        /*List<GameObject3D> gameObjects3D = new ArrayList<>();
        gameObjects = gameWorld.getGameObjects();
        for (GameObject3D gameObject3D: gameObjects3D) {
            gameObject3D.resize(width, height);
        }*/
    }

    //release memory when change screen
    public void dispose(){
        //gameWorld.dispose();
        if (modelBatch != null)
        {
            modelBatch.dispose();
            modelBatch = null;
        }

        if (spriteBatch != null) {
            spriteBatch.dispose();
            spriteBatch = null;
        }

        if (shapeRenderer != null)
        {
            shapeRenderer.dispose();
            shapeRenderer = null;
        }
        //Gdx.app.log("Renderer dispose", "yes");
    }
}
