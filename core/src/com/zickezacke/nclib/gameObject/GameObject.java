package com.zickezacke.nclib.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.component.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Game object 2D and UI
 */
public class GameObject {
    protected int id;
    protected String source2D;
    protected Texture texture;
    protected Vector2 position2D = new Vector2(0,0);   //pivot, left-down
    protected Vector2 size2D = new Vector2(100, 100); //size, width height
    //protected Vector2 oldScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    protected boolean isUI;
    protected boolean isActive;
    protected boolean isText;

    protected List<Component> components = new ArrayList<>();

    /**
     * constructors with no option
     */
    public GameObject(){}

    /**
     * construct a non UI 2D gameObject with ID
     * @param id unique identifier
     */
    public GameObject(int id){
        this(id, false);
    }

    /**
     * constructs a 2D gameObject with UI option
     * @param id unique identifier
     * @param isUI true if it is a UI
     */
    public GameObject(int id, boolean isUI){
        this.id = id;
        this.isUI = isUI;
        this.isActive = true;
    }

    /**
     * runs at start of scene, gets information of object, then builds it, then runs
     * logical initialization
     */
    public void Start(){
        objectInit();
        // update position of model

        //2D sprite
        if (source2D != null){
            texture = new Texture(Gdx.files.internal(source2D));
        }
        objectStart();
    }

    /**
     * runs every frame if the object is active
     */
    public void Update(){
        if (!isActive) return;
        objectUpdate();
        objectLateUpdate();
    }

    // set active of object
    public void setActive(boolean isActive){this.isActive = isActive;}
    
    // change texture after render
    public void setTexture(String newTexture){texture = new Texture(Gdx.files.internal(newTexture));}

    /**
     * checks clicks on 2D objects, interact with input handlers in GameWorld
     * @param x world position x of the click
     * @param y world position y of the click
     * @return true if this object is clicked, false otherwise
     */
    public boolean checkClick(int x, int y){
        if (x < this.position2D.x || x > this.position2D.x + this.size2D.x)
            return false;
        if (y < this.position2D.y || y > this.position2D.y + this.size2D.y)
            return false;
        return true;
    }

    /**
     * dispose this object
     */
    public void dispose(){
        texture.dispose();
    }

    //region support methods

    /**
     * reacts window resizes
     * @param width width of window
     * @param height height of window
     */
    public void resize(int width, int height){
        for (Component component: components) {
            component.resize(width, height);
        }
    }

    //region overrides
    /**
     * overrides this method to define behavior of this game object when clicked mouse down
     * @param x world position x of the click
     * @param y world position y of the click
     * @param pointer type of pointer (shape)
     * @param button left click(0), right click(1) or middle click(2)
     */
    public void MouseDown(int x, int y, int pointer, int button){}

    /**
     * overrides this method to define behavior of this game object when clicked mouse up (release)
     * @param x world position x of the click
     * @param y world position y of the click
     * @param pointer type of pointer (shape)
     * @param button left click(0), right click(1) or middle click(2)
     */
    public void MouseUp(int x, int y, int pointer, int button){}

    /**
     * overrides to specify information about object
     */
    public void objectInit(){} //before creation of texture

    /**
     * overrides to initialize logic variable
     */
    public void objectStart(){} //after creation of texture

    /**
     * overrides to define behaviors of object every frame
     */
    public void objectUpdate(){}    //Update stage 1

    /**
     * overrides to define behaviors after update stage 1
     */
    public void objectLateUpdate(){}    //Update stage 2
    //endregion


    //getters
    public boolean isActive(){return this.isActive;}
    public Texture getTexture() {return this.texture;}
    public int getId(){
        return this.id;
    }

    public boolean isUI(){
        return this.isUI;
    }
    public boolean isText(){return this.isText;}

    public Vector2 getPosition2D(){
        return this.position2D;
    }
    public Vector2 getSize2D(){
        return this.size2D;
    }

    //endregion
}
