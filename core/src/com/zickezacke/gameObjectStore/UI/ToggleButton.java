/**
 * ToggleButton is used to create toggle two states button
 */
package com.zickezacke.gameObjectStore.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
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
        position2D = new Vector2(START_PIVOT + value*(END_PIVOT_0-START_PIVOT), y*cellHeight + 1f);
    }

    /**
     * updates a toggle button object
     * a toggle button allows the user to change a setting between two states.
     */
    @Override
    public void objectUpdate() {
        //tracks mouse in button area.
        if (trackMouse){
            if( Gdx.input.getX() > START_PIVOT && Gdx.input.getX()< END_PIVOT_0 - 7f) {
                value =  ((Gdx.input.getX()-START_PIVOT)/(END_PIVOT_0-START_PIVOT));
                position2D.set(Gdx.input.getX(), y*cellHeight + 1f);
            }
        }
        if(ZickeZacke.waitFrame(10) && !trackMouse){
            if( value > 0.5){
                if(y==4){ZickeZacke.getSoundSystem().setIsCucTaCucTac(true);}
                if(y==3){ZickeZacke.getSoundSystem().setTheNextOne(true);}
                position2D.set(START_PIVOT + 0.5f*cellWidth - 12f, y*cellHeight + 1f);
            }else{
                if(y==4){ZickeZacke.getSoundSystem().setIsCucTaCucTac(false);}
                if(y==3){ZickeZacke.getSoundSystem().setTheNextOne(false);}
                position2D.set(START_PIVOT + 4f, y*cellHeight + 1f);
            }
        }
    }
}

