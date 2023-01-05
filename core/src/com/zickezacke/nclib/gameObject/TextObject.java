package com.zickezacke.nclib.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextObject extends GameObject{
    protected BitmapFont bitmapFont;
    protected String fontLink;
    protected String textValue;
    protected int fontSize;
    protected Color color;

    public TextObject(){}
    public TextObject(int id){
        super(id);
        this.isText = true;
    }
    public TextObject(int id, boolean isUI){
        super(id, isUI);
        this.isText = true;
    }

    @Override
    public void Start(){
        objectInit();
        // update position of model

        //2D sprite
        if (source2D != null){
            texture = new Texture(Gdx.files.internal(source2D));
        }
        //Bitmap
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontLink));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = color;
        bitmapFont = freeTypeFontGenerator.generateFont(parameter);
        freeTypeFontGenerator.dispose();

        objectStart();
    }

    public BitmapFont getBitmapFont() {
        return this.bitmapFont;
    }
    public String getTextValue(){
        return this.textValue;
    }
    public void setTextValue(String textValue){
        this.textValue = textValue;
    }
}
