package com.zickezacke.gameObjectStore.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.GameObject;

/**
 * nextTurnNoti is a banner to display when the user finished turn.
 */
public class nextTurnNoti extends GameObject {
    //time for notification can exist
    private final int SHOW_FRAME = 30;

    //value of grid layout 12 columns and 10 rows
    protected final int CELL_WIDTH =  Gdx.graphics.getWidth()/12;
    protected final int CELL_HEIGHT = Gdx.graphics.getHeight()/10;

    //index of next player
    private int nextPlayer;

    //timer to count from 0 to SHOW_FRAME
    private int count = 0;

    /**
     * constructor for nextTurnNoti
     *
     * @param id - int - unique identifier for object
     * @param nextPlayer - int - index of next player
     *
     * @return - nextTurnNoti - a temporary banner
     */
    public nextTurnNoti (int id, int nextPlayer){
        super(id,true);
        this.nextPlayer = nextPlayer;
        //notification is set inactive as default
        setActive(false);
    }

    @Override
    /**
     * initiates a notification for next player
     */
    public void objectInit() {
        //initiates texture for banner corresponding player number.
        source2D = "./UI/next_noti_" + String.valueOf(nextPlayer) + ".png";
        //initiates position for banner.
        position2D = new Vector2(0*CELL_WIDTH,4*CELL_HEIGHT);
        //initiates size for banner.
        size2D = new Vector2(12*CELL_WIDTH,2*CELL_HEIGHT);
    }

    @Override
    /**
     * updates a notification for next player
     */
    public void objectUpdate() {
        showNoti();
    }

    //shows the notification until timer count reaches SHOW_FRAME.
    public  void showNoti(){
        if(this.isActive){
            count++;
            if(count == SHOW_FRAME){
                count = 0;
                //hide banner
                this.setActive(false);
            }
        }
    }
}
