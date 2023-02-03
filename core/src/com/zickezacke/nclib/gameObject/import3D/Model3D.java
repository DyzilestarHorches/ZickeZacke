package com.zickezacke.nclib.gameObject.import3D;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;

import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;

/**
 * class to import 3D model
 */
public class Model3D {
    private Model model;
    private Instance3D instance;

    /**
     * constructs with source link of the model
     * @param source link to model
     */
    public Model3D(String source){
        UBJsonReader jsonReader = new UBJsonReader();
        // Create a model loader passing in our json reader
        G3dModelLoader modelLoader = new G3dModelLoader(jsonReader);
        // Now load the model
        model = modelLoader.loadModel(Gdx.files.getFileHandle(source, Files.FileType.Internal));
        instance = new Instance3D(model);
        if (instance != null) Gdx.app.log("model","exist");
        instance.transform.scale(0.003f, 0.003f, 0.003f);
    }

    /**
     * constructs with the source link of the model and initial position of it
     * @param source link to model
     * @param position3D initial position in game world
     */
    public Model3D(String source, Vector3 position3D){
        UBJsonReader jsonReader = new UBJsonReader();
        // Create a model loader passing in our json reader
        G3dModelLoader modelLoader = new G3dModelLoader(jsonReader);
        // Now load the model
        model = modelLoader.loadModel(Gdx.files.getFileHandle(source, Files.FileType.Internal));
        instance = new Instance3D(model, position3D);
        for (Material material: instance.materials) {
            material.set(
                    new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
            );
        }
        if (model != null) Gdx.app.log("model","exist");
        instance.transform.scale(0.003f, 0.003f, 0.003f);
    }

    /**
     * get the Instance3D to store in GameObject3D for later rendering
     * @return the processed import model
     */
    public Instance3D getModel(){
        //model.dispose();
        return this.instance;
    }

    /**
     * release information of model, which is heavy
     */
    public void dispose(){
        model.dispose();
        model = null;
        //instance.dispose();
    }
}
