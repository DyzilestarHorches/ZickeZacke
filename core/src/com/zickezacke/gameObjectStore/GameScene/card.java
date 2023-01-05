package com.zickezacke.gameObjectStore.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.zickezacke.nclib.gameObject.GameObject3D;

public class card extends GameObject3D {
    private float x, y;

    public card(int id, float x, float y) {
        super(id, true);
        this.x = x;
        this.y = y;
    }

    @Override
    public void objectInit() {
        source3D = "card_pick_0.g3db";
        position3D = new Vector3(x, 1f, y);
    }
}

