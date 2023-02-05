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

    //  controls whether the chicken is able to make a sound or not
    private static boolean isCucTaCucTac = true;

    //  controls whether the next turn of another player is able to make a sound or not
    private static boolean theNextOne = true;

    //  set the pitch of sound volume to 0.4
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

    /**
     *  gets the static variable isCucTaCucTac
     *
     *  @return - boolean - the static variable isCucTaCucTac
     */
    public boolean getIsCucTaCucTac(){return isCucTaCucTac;}

    /**
     * if the static variable is true, play this sound
     *
     * @param conga - boolean - new value for the static variable
     */
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

    /**
     * gets static boolean variable to play sound theNextOne
     *
     * @return - boolean - static boolean variable to play sound theNextOne
     */
    public boolean getTheNextOne(){
        return theNextOne;
    }

    /**
     * if the static variable is true, play this sound
     * 
     * @param next - boolean - new value for the static variable
     */
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
     *
     * @return - float - the sound volume
     */
    public float getVolume (){
        return soundVol;
    };

    /**
     * return the sound's volume of the system
     *
     * @param value2 - float - new variable for the sound volume
     */
    public void setVolume (float value2){
        soundVol = value2;
        for (Music m : listofMusics) {
            m.setVolume(soundVol);
        }
    }

}
