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

/**
 * Game Object 3D
 */
public class GameObject3D{
    protected int id;
    protected Vector3 position3D;   //position
    protected Vector3 prePos3D;     //position last frame
    protected Vector3 distance3D;   //distance from last frame
    protected Vector3 scale3D = new Vector3();  //scale size of model
    protected Vector3 center3D = new Vector3(); //center of model
    protected Vector3 offset3D = new Vector3(); //offset between center and position
    protected String source3D; //link of 3D model

    protected Instance3D model3D;   //store the model for rendering
    protected Model3D model;    //import the model
    protected Animation3D animation3D;  //animation controller

    protected boolean isActive; //false then update does not run
    protected boolean hasBound; //bounding box for checking click

    protected List<Component> components = new ArrayList<>(); //supporting components

    public final Vector3 center = new Vector3();    //center of bounding box
    public final Vector3 dimensions = new Vector3();    //size of bounding box
    protected final BoundingBox bounds = new BoundingBox(); //bounding box

    public GameObject3D(){}

    /**
     * constructor with id
     * @param id unique identifier
     * @param hasBound is clickable?
     */
    public GameObject3D(int id, boolean hasBound){
        this.id = id;
        this.isActive = true;
        this.scale3D = new Vector3(1, 1, 1);
        this.hasBound = hasBound;
    }

    /**
     * get the information of model, import and initialize it then run the logical initiation
     */
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

        objectStart();  // logic initiates
    }

    /**
     * runs every frame if the object is Active, updates bounding and other properties to movement
     */
    public void Update(){
        if (!isActive) return;
        objectUpdate();
        // update position of model

        distance3D = position3D.cpy().sub(prePos3D);

        prePos3D.x = position3D.x;  prePos3D.y = position3D.y; prePos3D.z= position3D.z;
        if (source3D != null && position3D != null){
            model3D.setTranslation(position3D);
            animation3D.update(Gdx.graphics.getDeltaTime());

            center3D.set(position3D.x + offset3D.x, position3D.y + offset3D.y, position3D.z + offset3D.z);
        }

        if (hasBound && bounds != null){
            bounds.getCenter(center);
            bounds.mul(new Matrix4(distance3D, new Quaternion().idt(), new Vector3(1, 1, 1)));
        }

        objectLateUpdate();
    }

    /**
     * reacts to window resize
     * @param width window width
     * @param height window height
     */
    public void resize(int width, int height){
        for (Component component: components) {
            component.resize(width, height);
        }
    }

    /**
     * releases memory
     */
    public void dispose(){
        model.dispose();
        model3D.dispose();
        for (Component component : components){
            component.dispose();
        }
    }
    //overrides
    /**
     * overrides to specify information about object
     */
    public void objectInit(){} //before creation of model
    /**
     * overrides to initialize logic variable
     */
    public void objectStart(){} //after creation of model

    /**
     * overrides to initialize logic variable
     */
    public void objectUpdate(){}    //update

    /**
     * overrides to define behaviors of object every frame
     */
    public void objectLateUpdate(){}    //after update

    /**
     * overrides this method to define behavior of this game object when clicked mouse down
     * @param screenX world position x of the click
     * @param screenY world position y of the click
     * @param pointer type of pointer (shape)
     * @param button left click(0), right click(1) or middle click(2)
     */
    public void MouseDown(int screenX, int screenY, int pointer, int button){}

    /**
     * overrides this method to define behavior of this game object when clicked mouse up (release)
     * @param screenX world position x of the click
     * @param screenY world position y of the click
     * @param pointer type of pointer (shape)
     * @param button left click(0), right click(1) or middle click(2)
     */
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
    public Vector3 getPosition3D(){
        return  this.position3D;
    }
    public List<Component> getComponents(){return this.components;}
}
