package com.zickezacke.gameObjectStore.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;
public class ToggleButton extends DragButton {
    private float value;
    private float y;
    public ToggleButton(int id, float value, float y){
        super(id,value,y);
        this.value = value;
        this.y = y;
    }

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2(START_PIVOT + value*(END_PIVOT_0-START_PIVOT), y*cellHeight + 1f);
    }

    @Override
    public void objectUpdate() {
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

