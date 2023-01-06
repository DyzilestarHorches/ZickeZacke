package com.zickezacke.nclib.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.zickezacke.nclib.component.BoundingVisual;
import com.zickezacke.nclib.component.Component;
import com.zickezacke.nclib.gameObject.import3D.Animation3D;
import com.zickezacke.nclib.gameObject.import3D.Instance3D;
import com.zickezacke.nclib.gameObject.import3D.Model3D;

import java.util.ArrayList;
import java.util.List;

public class GameObject3D{
    protected int id;
    protected Vector3 position3D;
    protected Vector3 prePos3D;
    protected Vector3 distance3D;
    protected Vector3 scale3D = new Vector3();
    protected Vector3 center3D = new Vector3();
    protected Vector3 offset3D = new Vector3();
    protected String source3D;

    protected Instance3D model3D;
    protected Model3D model;
    protected Animation3D animation3D;

    protected boolean isActive;
    protected boolean hasBound;

    protected List<Component> components = new ArrayList<>();

    public final Vector3 center = new Vector3();
    public final Vector3 dimensions = new Vector3();
    protected final BoundingBox bounds = new BoundingBox();

    public GameObject3D(){}
    public GameObject3D(int id, boolean hasBound){
        this.id = id;
        this.isActive = true;
        this.scale3D = new Vector3(1, 1, 1);
        this.hasBound = hasBound;
    }

    public void Start(){
        objectInit();
        // update position of model
        //3D model
        if (source3D != null && position3D != null){
            prePos3D = position3D.cpy();
            model = new Model3D(source3D, position3D);
            model3D = model.getModel();
            animation3D = new Animation3D(model3D);

            //use bound to calculate center and offset
            model3D.calculateBoundingBox(bounds);
            bounds.mul(model3D.transform);
            bounds.getDimensions(dimensions);
            bounds.getCenter(center);
            offset3D.set(center.x - position3D.x, center.y - position3D.y, center.z - position3D.z);
            scale3D.set(dimensions);
            center3D.set(position3D.x + offset3D.x, position3D.y + offset3D.y, position3D.z + offset3D.z);

            if (!hasBound){
                bounds.clr();
            }

        }

        objectStart();
    }

    public void Update(){
        if (!isActive) return;
        objectUpdate();
        // update position of model
        System.out.println(id);
        distance3D = position3D.cpy().sub(prePos3D);
        //Gdx.app.log("Center", distance3D.toString());
        prePos3D.x = position3D.x;  prePos3D.y = position3D.y; prePos3D.z= position3D.z;
        if (source3D != null && position3D != null){
            model3D.setTranslation(position3D);
            animation3D.update(Gdx.graphics.getDeltaTime());

            center3D.set(position3D.x + offset3D.x, position3D.y + offset3D.y, position3D.z + offset3D.z);
            //bounds.mul(new Matrix4(distance3D, new Quaternion().idt(), new Vector3(1, 1, 1)));
        }

        if (hasBound && bounds != null){
            bounds.getCenter(center);
            bounds.mul(new Matrix4(distance3D, new Quaternion().idt(), new Vector3(1, 1, 1)));
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
        for (Component component : components){
            component.dispose();
        }
    }
    //overrides
    public void objectInit(){} //before creation
    public void objectStart(){} //after creation

    public void objectUpdate(){}    //before render
    public void objectLateUpdate(){}    //after render

    public void MouseDown(int screenX, int screenY, int pointer, int button){}
    public void MouseUp(int screenX, int screenY, int pointer, int button){}

    //getters
    public boolean isActive(){
        return this.isActive;
    }
    public Instance3D getModel(){
        return this.model3D;
    }
    public int getId(){
        return this.id;
    }
    public BoundingBox getBounds(){
        return this.bounds;
    }
    public Vector3 getCNposition(){
        return  this.position3D;
    }
}
