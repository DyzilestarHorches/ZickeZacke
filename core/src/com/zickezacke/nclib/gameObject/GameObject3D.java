package com.zickezacke.nclib.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.component.Component;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;
import com.zickezacke.nclib.gameObject.import3D.Instance3D;
import com.zickezacke.nclib.gameObject.import3D.Model3D;

import java.util.ArrayList;
import java.util.List;

public class GameObject3D{
    protected int id;
    protected Vector3 position3D;
    protected Vector3 scale3D;
    protected String source3D;

    protected Instance3D model3D;
    protected Model3D model;
    protected Animation3D animation3D;

    protected boolean isActive;

    protected List<Component> components = new ArrayList<>();

    public GameObject3D(){}
    public GameObject3D(int id){
        this.id = id;
        this.isActive = true;
        this.scale3D = new Vector3(1, 1, 1);
    }

    public void Start(){
        objectInit();
        // update position of model
        //3D model
        if (source3D != null && position3D != null){
            model = new Model3D(source3D, position3D);
            model3D = model.getModel();
            animation3D = new Animation3D(model3D);
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

    public void dispose(){
        model.dispose();
        model3D.dispose();
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
    public int getId(){
        return this.id;
    }
}
