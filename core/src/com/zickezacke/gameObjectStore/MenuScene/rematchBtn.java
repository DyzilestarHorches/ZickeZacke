package com.zickezacke.gameObjectStore.MenuScene;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.game.ZickeZacke;


public class rematchBtn extends Btn {
    public rematchBtn(int id, String type){super(id,type);}

    @Override
    public void objectInit() {
        super.objectInit();
        position2D = new Vector2(2*cellWidth,2*cellHeight);
        size2D = new Vector2(2*cellWidth,cellHeight);
    }

    @Override
    public void MouseDown(int x, int y, int pointer, int button) {
        if(button == 0){
            //isActive = false;
            ZickeZacke.getInstance().setScreen(2);
        }
    }
}
