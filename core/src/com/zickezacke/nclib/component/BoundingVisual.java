package com.zickezacke.nclib.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.zickezacke.game.ZickeZacke;

/**
 * class to draw a visual box of boundingBox of 3D GameObject, use for debug in creating click function
 * and later developing game
 */
public class BoundingVisual extends Component {
    private Vector3 dimension;
    private BoundingBox boundingBox;
    private Color color;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private boolean isActive;

    public BoundingVisual(){}

    /**
     * sets the bounding visual to a bounding box with customized size
     * @param dimension size of bounding visual
     * @param boundingBox the boundingBox to be attached to
     * @param color the color of bounding visual
     */
    public void set(Vector3 dimension, BoundingBox boundingBox, Color color){
        this.boundingBox = boundingBox;
        this.dimension = dimension;
        this.color = color;
    }

    /**
     * uses in Render to draw the box on every frame
     */
    private void drawBox(){
        PerspectiveCamera camera3D = ZickeZacke.getInstance().getGameScreens().get(0).getGameWorld().getCamera3D();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
                dimension.x, dimension.y
                ,dimension.z
                //1000, 1000, 1000
        );

        /*shapeRenderer.box(
                10f,1f,1f, 1000, 1000, 1000
        );*/
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * overrides to be automatically called in the class renderer
     */
    @Override
    public void Render() {
        if (isActive) drawBox();
    }

    /**
     * releases memory
     */
    public void dispose(){
        shapeRenderer.dispose();
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
