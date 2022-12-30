
package com.zickezacke.nclib.component;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.component.Component;

public class UIHelpers extends Component {
    private float screenWidth;
    private float screenHeight;
    private Vector2 screenCenter = new Vector2();

    public UIHelpers() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        screenCenter.x = screenWidth/2;
        screenCenter.y = screenHeight/2;
    }

    public void resize(int width, int height){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        screenCenter.x = screenWidth/2;
        screenCenter.y = screenHeight/2;
    }
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
