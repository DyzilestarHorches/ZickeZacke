package com.zickezacke.nclib.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Object for manipulating text
 */
public class TextObject extends GameObject{
    protected BitmapFont bitmapFont;
    protected String fontLink;
    protected String textValue;
    protected int fontSize;
    protected Color color;

    public TextObject(){}

    /**
     * constructs object with id
     * @param id unique identifier
     */
    public TextObject(int id){
        super(id);
        this.isText = true;
    }

    /**
     * constructs object with UI option
     * @param id unique identifier
     * @param isUI is UI if true, not otherwise
     */
    public TextObject(int id, boolean isUI){
        super(id, isUI);
        this.isText = true;
    }

    /**
     * declares information of the object, then create it, then runs logical initiations
     */
    @Override
    public void Start(){
        objectInit();
        // update position of model

        //2D sprite
        if (source2D != null){
            texture = new Texture(Gdx.files.internal(source2D));
        }
        //use Bitmap and FreeTypeFontGenerator to import customizable fonts
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontLink));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = color;
        bitmapFont = freeTypeFontGenerator.generateFont(parameter);
        freeTypeFontGenerator.dispose();

        objectStart();
    }

    //getters
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
