/**
 * DragButton is used to implement drag bar to modify DragBar value in setting menu.
 */
package com.zickezacke.gameObjectStore.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;


public class DragButton extends GameObject {
    //value of grid layout 12 columns and 10 rows
    protected final int CELL_WIDTH =  1280/12;
    protected final int CELL_HEIGHT = 720/10;
    protected final float START_PIVOT = 5* CELL_WIDTH;
    protected final float END_PIVOT = 9* CELL_WIDTH - 0.25f* CELL_WIDTH - 7f;
    protected final float END_PIVOT_0 = 6* CELL_WIDTH - 0.5f* CELL_WIDTH - 1f;
    protected boolean trackMouse = false;
    private float y;
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
        source2D = "./UI/drag_button.png";
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

