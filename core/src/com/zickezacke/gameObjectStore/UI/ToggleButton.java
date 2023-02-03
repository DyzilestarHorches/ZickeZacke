package com.zickezacke.gameObjectStore.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
/**
 * ToggleButton is used to create toggle two states button
 */
public class ToggleButton extends DragButton {
    private float value;
    private float y;

    /**
     * constructor for ToggleButton class
     *
     * @param id - int - unique identifier for object
     * @param value - float - value of ToggleButton
     * @param y - float - y position
     *
     * @return - ToggleButton - a button which can switch 2 different states
     */
    public ToggleButton(int id, float value, float y){
        super(id,value,y);
        this.value = value;
        this.y = y;
    }

    @Override
    /**
     * initiates a toggle button object
     */
    public void objectInit() {
        super.objectInit();
        //initiates toggle button position based on initial value.
        position2D = new Vector2(START_PIVOT + value*(END_PIVOT_0-START_PIVOT), y* CELL_HEIGHT + 1f);
    }

    /**
     * updates a toggle button object
     * a toggle button allows the user to change a setting between two states.
     */
    @Override
    public void objectUpdate() {
        if (trackMouse){
            //tracks mouse is true in drag range.
            if( Gdx.input.getX() > START_PIVOT && Gdx.input.getX()< END_PIVOT_0 - 7f) {
                //updates button position based on updates value.
                value =  ((Gdx.input.getX()-START_PIVOT)/(END_PIVOT_0-START_PIVOT));
                position2D.set(Gdx.input.getX(), y* CELL_HEIGHT + 1f);
            }
        }
        //waits 10 frame and fixates the position of toggle button.
        if(ZickeZacke.waitFrame(10) && !trackMouse){
            //value >0.5, passes true to reference value.
            if( value > 0.5){
                if(y==4){ZickeZacke.getSoundSystem().setIsCucTaCucTac(true);}
                if(y==3){ZickeZacke.getSoundSystem().setTheNextOne(true);}
                //fixates the position of toggle button to the most left of range.
                position2D.set(START_PIVOT + 0.5f* CELL_WIDTH - 12f, y* CELL_HEIGHT + 1f);
            }else{
                //value <=0.5, passes false to reference value.
                if(y==4){ZickeZacke.getSoundSystem().setIsCucTaCucTac(false);}
                if(y==3){ZickeZacke.getSoundSystem().setTheNextOne(false);}
                //fixates the position of toggle button to the most right of range.
                position2D.set(START_PIVOT + 4f, y* CELL_HEIGHT + 1f);
            }
        }
    }
}

