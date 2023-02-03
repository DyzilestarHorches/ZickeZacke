
package com.zickezacke.nclib.component;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.component.Component;

/**
 * this class helps UI arrangement in developing stage. is not used in releases
 */
public class UIHelpers extends Component {
    private float screenWidth;
    private float screenHeight;
    private Vector2 screenCenter = new Vector2();

    /**
     * constructs the object
     */
    public UIHelpers() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        screenCenter.x = screenWidth/2;
        screenCenter.y = screenHeight/2;
    }

    /**
     * reacts to window resize
     * @param width window width
     * @param height window height
     */
    public void resize(int width, int height){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        screenCenter.x = screenWidth/2;
        screenCenter.y = screenHeight/2;
    }

    /**
     * call in update or renderer to draw a grid
     * @param divideWidth number of horizontal segments
     * @param divideHeight number of vertical segments
     */
    public void drawGrid(int divideWidth, int divideHeight){
        float partWidth = screenWidth/divideWidth;
        float partHeight = screenHeight/divideHeight;
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        for (int i = 1; i < divideWidth; i++){
            shapeRenderer.line(0, i * partHeight, screenWidth, i * partHeight);
        }
        for (int i = 1; i < divideHeight; i++){
            shapeRenderer.line( i * partWidth, 0, i * partWidth,screenHeight);
        }
        shapeRenderer.end();
        shapeRenderer.dispose();
    }
}
