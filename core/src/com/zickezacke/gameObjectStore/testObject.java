package com.zickezacke.gameObjectStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.gameObjectStore.GameScene.Chicken;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.gameObject.GameObject;
import com.zickezacke.nclib.gameObject.GameObject3D;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;


public class testObject extends GameObject3D {

    private boolean isMoving;
    private BoundingVisual boundingVisual = new BoundingVisual();

    public testObject(int id){
        super(id, true);
    }
    public void objectInit(){
        source3D = "demo.g3db";
        position3D = new Vector3(0f, 0f, -2f);
        components.add(boundingVisual);
    }
    public void objectStart(){
        isMoving = false;
    }
    public void animation(){
        String Default = model3D.animations.get(0).id;
        String Ani1 = model3D.animations.get(1).id;
        if (!isMoving) animation3D.setAnimation(Default, 10);
        if (isMoving)
            animation3D.animate(Ani1, new Animation3D.AnimationListener(){
                @Override
                public void onEnd(Animation3D.AnimationDesc animation) {
                    isMoving = false;
                }

                @Override
                public void onLoop(Animation3D.AnimationDesc animation) {

                }
            }, 0f);
    }
    public void movement(){
        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            model3D.setRotation(new Vector3(0,0,1), 2f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z)){
            model3D.setScale(new Vector3(1.1f,1.1f,1.1f));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
            Gdx.app.log("Object","Move");
            position3D.x += 10/60f;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
            Gdx.app.log("Object","Move");
            position3D.x -= 10/60f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
            Gdx.app.log("Object","Move");
            position3D.z += 10/60f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
            Gdx.app.log("Object","Move");
            position3D.z -= 10/60f;
        }
    }
    public void openMenu(){
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            ZickeZacke.getInstance().setScreen(1);
        }

    }
    public void objectUpdate(){

        //bounds.getCenter(center);
        //Gdx.app.log("Center", bounds..toString());
        //Gdx.app.log("scale", model3D.transform.getScale(new Vector3()).toString());
        boundingVisual.drawBox(dimensions, bounds, Color.BLUE);

       /* ShapeRenderer shapeRenderer = new ShapeRenderer();
        PerspectiveCamera camera3D = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().getCamera3D();

        shapeRenderer.setProjectionMatrix(camera3D.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.LIME);
        Vector3 trf = new Vector3();
        model3D.transform.getTranslation(trf);
        shapeRenderer.box(
                trf.x, trf.y, trf.z,
                10, 10, 10
        );
        shapeRenderer.end();*/

        animation();
        movement();
        openMenu();
    }

    @Override
    public void MouseDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("Mouse", "Down");
    }
}
