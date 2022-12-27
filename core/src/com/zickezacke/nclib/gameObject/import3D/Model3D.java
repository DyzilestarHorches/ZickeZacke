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

public class Model3D {
    private Model model;
    private Instance3D instance;

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

    public Instance3D getModel(){
        //model.dispose();
        return this.instance;
    }

    public void dispose(){
        model.dispose();
        model = null;
        //instance.dispose();
    }
}
