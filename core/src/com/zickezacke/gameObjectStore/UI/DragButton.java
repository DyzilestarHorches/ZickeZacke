package com.zickezacke.gameObjectStore.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

/**
 * DragButton is used to implement drag bar to modify DragBar value in setting menu.
 */
public class DragButton extends GameObject {
    //value of grid layout 12 columns and 10 rows
    protected final int CELL_WIDTH =  Gdx.graphics.getWidth()/12;
    protected final int CELL_HEIGHT = Gdx.graphics.getHeight()/10;
    //begin point of drag range and toggle range.
    protected final float START_PIVOT = 5* CELL_WIDTH;
    //end point of drag range.
    protected final float END_PIVOT = 9* CELL_WIDTH - 0.25f* CELL_WIDTH - 7f;
    //end point of toggle range.
    protected final float END_PIVOT_0 = 6* CELL_WIDTH - 0.5f* CELL_WIDTH - 1f;
    protected boolean trackMouse = false;
    private float y;
    //value for UI to present.
    private float value;
    /**
     * constructor for DragButton class
     *
     * @param id - int - unique identifier for object
     * @param value - float - value of ToggleButton
     * @param y - float - y position
     *
     * @return - DragButton - a button which can drag to modify value
     */
    public DragButton(int id,float value, float y){
        super(id, true);
        this.value = value;
        this.y = y;
    }

    @Override
    /**
     * initiates a drag button object
     */
    public void objectInit() {
        //initiates texture for button.
        source2D = "./UI/drag_button.png";
        //initiates button position based on the initial value.
        position2D = new Vector2(START_PIVOT + value*(END_PIVOT-START_PIVOT), y* CELL_HEIGHT + 1f);
        size2D = new Vector2(0.5f* CELL_HEIGHT,0.5f* CELL_HEIGHT);
    }

    @Override
    /**
     * set drag button position corresponding mouse position from START_PIVOT to END_PIVOT
     */
    public void objectUpdate() {
        if (trackMouse){
            if( Gdx.input.getX() > START_PIVOT && Gdx.input.getX()< END_PIVOT) {
                // updates button position based on the updates value.
                value = ((Gdx.input.getX()-START_PIVOT)/(END_PIVOT-START_PIVOT));
                position2D.set(Gdx.input.getX(), y* CELL_HEIGHT + 1f);
            }
        }
    }

    @Override
    /**
     * starts mouse tracking
     */
    public void MouseDown(int x, int y, int pointer, int button) {
            if (button == 0){
                trackMouse = true;
            }
    }

    /**
     *stops mouse tracking
     */
    @Override
    public void MouseUp(int x, int y, int pointer, int button) {
            if (button == 0 && trackMouse){
                trackMouse = false;
            }
    }

    /**
     * gets value of drag button
     * @return value - float - value of brightness volume or sound
     */
    public float getValue() {
        return this.value;
    }
}

