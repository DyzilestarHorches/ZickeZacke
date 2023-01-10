package com.zickezacke.gameObjectStore.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;


public class DragBar extends GameObject {
    private final int cellWidth =  1280/12;
    private final int cellHeight = 720/10;
    private DragButton dragButton;
    public DragBar(int id, float y, DragButton dragButton){
        super(id, true);
        position2D.set(5*cellWidth + 0.25f*cellHeight, y*cellHeight + 1f);
        this.dragButton = dragButton;
    }

    @Override
    public void objectInit() {
        source2D = "./UI/drag_bar.png";
        size2D = new Vector2(dragButton.getValue()+0.25f*cellHeight, cellHeight*0.5f);
    }

    @Override
    public void objectUpdate() {
        float tmpVal = dragButton.getPosition2D().x - position2D.x + 0.25f*cellHeight;
        size2D.set(tmpVal , size2D.y);
    }
}

