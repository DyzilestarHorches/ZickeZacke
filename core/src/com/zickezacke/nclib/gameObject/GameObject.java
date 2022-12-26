package com.zickezacke.nclib.gameObject;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.Component;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;
import com.zickezacke.nclib.gameObject.import3D.Instance3D;
import com.zickezacke.nclib.gameObject.import3D.Model3D;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    protected int id;
    protected Vector3 position3D;
    protected Vector3 scale3D;
    protected String source3D;
    protected String source2D;
    protected Instance3D model3D;
    protected Animation3D animation3D;
    protected Texture texture;
    protected boolean isUI;
    protected boolean isActive;

    protected Vector2 position2D = new Vector2(0,0);   //pivot, left-down
    protected Vector2 size2D = new Vector2(100, 100); //size, width height

    protected List<Component> components = new ArrayList<>();


    public GameObject(){}
    public GameObject(int id){
        this(id, false);
    }
    public GameObject(int id, boolean isUI){
        this.id = id;
        this.isUI = isUI;
        this.isActive = true;
        this.scale3D = new Vector3(1, 1, 1);
    }
    public GameObject(int id, Vector3 position3D, String modelLink){
        this(id, false);
        this.position3D = position3D;
        this.model3D = new Model3D(modelLink).getModel();
    }
    public void Start(){
        objectInit();
        // update position of model
        //3D model
        if (source3D != null && position3D != null){
            model3D = new Model3D(source3D, position3D).getModel();
            animation3D = new Animation3D(model3D);
        }
        //2D sprite
        if (source2D != null){
            texture = new Texture(Gdx.files.internal(source2D));
        }
        objectStart();
    }

    public void Update(){
        if (!isActive) return;
        objectUpdate();
        // update position of model
        if (source3D != null && position3D != null){
            model3D.setTranslation(position3D);
            model3D.setScale(scale3D);
            animation3D.update(Gdx.graphics.getDeltaTime());
        }


        objectLateUpdate();
    }

    public void resize(int width, int height){
        for (Component component: components) {
            component.resize(width, height);
        }
    }
    //overrides
    public void objectInit(){} //before creation
    public void objectStart(){} //after creation

    public void objectUpdate(){}    //before render
    public void objectLateUpdate(){}    //after render



    //getters
    public Instance3D getModel(){
        return this.model3D;
    }
    public Texture getTexture() {return this.texture;}
    public int getId(){
        return this.id;
    }

    public boolean isUI(){
        return this.isUI;
    }

    public Vector2 getPosition2D(){
        return this.position2D;
    }
    public Vector2 getSize2D(){
        return this.size2D;
    }
}
