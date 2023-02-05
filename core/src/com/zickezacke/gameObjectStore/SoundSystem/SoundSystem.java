package com.zickezacke.gameObjectStore.SoundSystem;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.List;

/**
 * The SoundSystem class is a system that stores game's sound and game's music to play it.
 */
public class SoundSystem{
    //create a list of music to store the game's sound and game's music
    private List <Music> listofMusics = new ArrayList<>();

    private static boolean isCucTaCucTac = true;
    private static boolean theNextOne = true;
    private static float soundVol = 0.4f;

    /**
     * constructor for the SoundSystem class
     */
    public SoundSystem(){}

    /**
     * initializes the Sound's file in Sound System
     */
    public void SoundInIt() {
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/playingSound.wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/chicken-single-alarm-call-6056.wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/Mouse Click.wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/Taco Bell Bong - Sound Effect (HD).wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/Applause  Sound Effect.wav")));
    }

    /**
     * play the background music on loop
     */
    public void playBackgroundMusicOnLoop(){
        listofMusics.get(0).setLooping(true);
        listofMusics.get(0).setVolume(soundVol);
        listofMusics.get(0).play();
    }

    /**
     * play chicken's sound
     */
    public void cucTaCucTac(){
        if (isCucTaCucTac) {
            listofMusics.get(1).play();
        }
    }
    public boolean getIsCucTaCucTac(){return isCucTaCucTac;}
    public void setIsCucTaCucTac(boolean conga){
        isCucTaCucTac = conga;
    }

    /**
    * play the click's sound for the button
     */
    public void click(){
        listofMusics.get(2).play();
    }

    /**
     * if it comes to the next turn of another player, play this sound
     */
    public void nextTurn(){
        if (theNextOne) {
            listofMusics.get(3).play();
        }
    }
    public boolean getTheNextOne(){
        return theNextOne;
    }
    public void setTheNextOne(boolean next){
        theNextOne = next;
    }

    /**
     * applause sound to play in the winner scene
     */
    public void applause() {
        listofMusics.get(4).play();
    }


    /**
     * adjust the sound's volume
     * @return
     */
    public float getVolume (){
        return soundVol;
    };
    public void setVolume (float value2){
        soundVol = value2;
        for (Music m : listofMusics) {
            m.setVolume(soundVol);
        }
    }

}
