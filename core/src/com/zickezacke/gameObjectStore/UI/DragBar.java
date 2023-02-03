package com.zickezacke.gameObjectStore.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

/**
 * DragBar is used to display value for DragButton
 */
public class DragBar extends GameObject {
    //value of grid layout 12 columns and 10 rows.
    protected final int CELL_WIDTH =  Gdx.graphics.getWidth()/12;
    protected final int CELL_HEIGHT = Gdx.graphics.getHeight()/10;
    //dragButton that dragBar value use for reference.
    private DragButton dragButton;

    /**
     * constructor for DragBar class
     *
     * @param id - int - unique identifier for object
     * @param y - double - y position
     * @param dragButton - DragButton - a button which can drag to modify value
     *
     * @return DragBar - a UI can be modified by DragButton
     */
    public DragBar(int id, float y, DragButton dragButton){
        super(id, true);
        //set dragBar position based on y th row.
        position2D.set(5* CELL_WIDTH + 0.25f* CELL_HEIGHT, y* CELL_HEIGHT + 1f);
        this.dragButton = dragButton;
    }

    @Override
    /**
     * initiates a drag bar object
     */
    public void objectInit() {
        //initiates dragBar based on specified file path.
        source2D = "./UI/drag_bar.png";
        //scale the the width of dragBar based on initial value from dragButton(default)
        size2D = new Vector2(dragButton.getValue()+0.25f* CELL_HEIGHT, CELL_HEIGHT *0.5f);
    }

    @Override
    /**
     *updates drag bar value base on DragButton
     */
    public void objectUpdate() {
        // updates x position of dragButton
        float tmpVal = dragButton.getPosition2D().x - position2D.x + 0.25f* CELL_HEIGHT;
        // scale width of dragBar based on x value.
        size2D.set(tmpVal , size2D.y);
    }
}

