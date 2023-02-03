package com.zickezacke.gameObjectStore.UI;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

/**
 * DragBar is used to display value for DragButton
 */
public class DragBar extends GameObject {
    //value of grid layout 12 columns and 10 rows.
    private final int CELL_WIDTH =  1280/12;
    private final int CELL_HEIGHT = 720/10;
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
        //set dragBar position base on y th row.
        position2D.set(5* CELL_WIDTH + 0.25f* CELL_HEIGHT, y* CELL_HEIGHT + 1f);
        this.dragButton = dragButton;
    }

    @Override
    /**
     * initiates a drag bar object
     */
    public void objectInit() {
        //initiates dragBar base on specified file path.
        source2D = "./UI/drag_bar.png";
        //scale the the width of dragBar base on init value from dragButton(default)
        size2D = new Vector2(dragButton.getValue()+0.25f* CELL_HEIGHT, CELL_HEIGHT *0.5f);
    }

    @Override
    /**
     *updates drag bar value base on DragButton
     */
    public void objectUpdate() {
        float tmpVal = dragButton.getPosition2D().x - position2D.x + 0.25f* CELL_HEIGHT;
        size2D.set(tmpVal , size2D.y);
    }
}

