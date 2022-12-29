package com.zickezacke.nclib.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.component.Component;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    protected int id;

    protected String source2D;
    protected Texture texture;
    protected Vector2 position2D = new Vector2(0,0);   //pivot, left-down
    protected Vector2 size2D = new Vector2(100, 100); //size, width height
    //protected Vector2 oldScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    protected boolean isUI;
    protected boolean isActive;

    protected List<Component> components = new ArrayList<>();


    public GameObject(){}
    public GameObject(int id){
        this(id, false);
    }
    public GameObject(int id, boolean isUI){
        this.id = id;
        this.isUI = isUI;
        this.isActive = true;
    }
    public void Start(){
        objectInit();
        // update position of model

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
        //if (checkClick(Gdx.input))


        objectLateUpdate();
    }

    public boolean checkClick(int x, int y){
        if (x < this.position2D.x || x > this.position2D.x + this.size2D.x)
            return false;
        if (y < this.position2D.y || y > this.position2D.y + this.size2D.y)
            return false;
        return true;
    }

    public void MouseDown(int x, int y, int pointer, int button){

    }

    public void MouseUp(int x, int y, int pointer, int button){}

    public void dispose(){
        texture.dispose();
    }
    //region support methods
    public void resize(int width, int height){
        for (Component component: components) {
            component.resize(width, height);
        }

        /*if (isUI){
            size2D.x *= width/oldScreenSize.x;
            size2D.y *= height/oldScreenSize.y;
        }*/
    }
    //overrides
    public void objectInit(){} //before creation
    public void objectStart(){} //after creation

    public void objectUpdate(){}    //before render
    public void objectLateUpdate(){}    //after render



    //getters
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

    //endregion
}
