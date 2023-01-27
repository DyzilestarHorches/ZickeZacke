package com.zickezacke.gameObjectStore.SoundSystem;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.List;

public class SoundSystem{
    private List <Music> listofMusics = new ArrayList<>();
    private static boolean isCucTaCucTac = true;
    private static boolean theNextOne = true;
    private static float soundVol = 0.4f;

    public SoundSystem(){}

    public void SoundInIt() {
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/playingSound.wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/chicken-single-alarm-call-6056.wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/Mouse Click.wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/Taco Bell Bong - Sound Effect (HD).wav")));
        listofMusics.add(Gdx.audio.newMusic(Gdx.files.internal("./Sounds/Applause  Sound Effect.wav")));
    }

    public void playBackgroundMusicOnLoop(){
        listofMusics.get(0).setLooping(true);
        listofMusics.get(0).setVolume(soundVol);
        listofMusics.get(0).play();
    }

    public void cucTaCucTac(){
        if (isCucTaCucTac) {
            listofMusics.get(1).play();
        }
    }
    public void setIsCucTaCucTac(boolean conga){
        isCucTaCucTac = conga;
    }

    public void click(){
        listofMusics.get(2).play();
    }

    public void nextTurn(){
        if (theNextOne) {
            listofMusics.get(3).play();
        }
    }
    public void setTheNextOne(boolean next) {
        theNextOne = next;
    }

    public void applause() {
        listofMusics.get(4).play();
    }


    //adjust volume
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
