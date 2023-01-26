package com.zickezacke.gameObjectStore.UI;


import com.badlogic.gdx.Gdx;
import com.zickezacke.game.ZickeZacke;
public class DragCuctacButton extends DragButton {
    private float x;
    public DragCuctacButton(int id){
        super(id,0f,4);
    }

    @Override
    public void objectUpdate() {
        if (trackMouse){
            if( Gdx.input.getX() > START_PIVOT && Gdx.input.getX()< END_PIVOT_0 - 7f) {
                x =  Gdx.input.getX();
                position2D.set(x, 4*cellHeight + 1f);
            }
        }
        if(ZickeZacke.waitFrame(10) && !trackMouse){
            if( x > (-START_PIVOT+END_PIVOT_0)/2 + START_PIVOT){
                ZickeZacke.getSoundSystem().setIsCucTaCucTac(true);
                position2D.set(START_PIVOT + 0.5f*cellWidth - 12f, 4*cellHeight + 1f);
            }else{
                ZickeZacke.getSoundSystem().setIsCucTaCucTac(false);
                position2D.set(START_PIVOT + 4f, 4*cellHeight + 1f);
            }
        }
    }
}

