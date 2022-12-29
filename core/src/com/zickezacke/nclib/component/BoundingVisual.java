package com.zickezacke.nclib.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.zickezacke.game.ZickeZacke;


public class BoundingVisual extends Component {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    public BoundingVisual(){}
    public void drawBox(Vector3 dimension, BoundingBox boundingBox, Color color){
        PerspectiveCamera camera3D = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().getCamera3D();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera3D.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        Vector3 center = new Vector3();

        boundingBox.getCenter(center);
        //boundingBox.getDimensions(dimension);
        //Gdx.app.log("center",dimension.toString());
        shapeRenderer.box(
                center.x - dimension.x / 2,
                center.y - dimension.y / 2,
                center.z + dimension.z / 2,
                dimension.x, dimension.y, dimension.z
//                1000, 1000, 1000
        );

        /*shapeRenderer.box(
                10f,1f,1f, 1000, 1000, 1000
        );*/
        shapeRenderer.end();
    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
