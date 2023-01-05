package com.zickezacke.gameObjectStore.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.zickezacke.nclib.gameObject.TextObject;

public class textTest extends TextObject {
    public textTest(int id, boolean isUI){
        super(id, isUI);
    }
    @Override
    public void objectInit() {
        position2D = new Vector2(400f, 320f);
        fontSize = 20;
        color = Color.WHITE;
        textValue = "Lorem Ipsum is simply dummy text of the printing\n" +
                "and typesetting industry. Lorem Ipsum has been\n" +
                "the industry's standard dummy text ever since the 1500s,\n" +
                "when an unknown printer took a galley of type\n" +
                "and scrambled it to make a type specimen book.\n" +
                "It has survived not only five centuries,\n" +
                "but also the leap into electronic typesetting.";
        fontLink = "OptimaMedium.ttf";
    }
}
