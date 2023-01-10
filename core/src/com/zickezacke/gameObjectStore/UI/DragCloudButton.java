package com.zickezacke.gameObjectStore.UI;


import com.badlogic.gdx.Gdx;
import com.zickezacke.game.ZickeZacke;
import com.zickezacke.nclib.game.screens.helpers.GameWorld;

public class DragCloudButton extends DragButton {
    public boolean isCloud;
    private float x;
    public DragCloudButton(int id){
        super(id,0f,4);
        this.isCloud = false;
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
                this.isCloud = true;
                position2D.set(START_PIVOT + 0.5f*cellWidth - 12f, 4*cellHeight + 1f);
            }else{
                this.isCloud = false;
                position2D.set(START_PIVOT + 4f, 4*cellHeight + 1f);
            }
        }
    }
}

