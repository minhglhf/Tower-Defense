package sample;


import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.nio.file.Paths;

public class GameSound {
    public String musicFile;
    public Media sound;
    public AudioClip audioClip;
    public Text turnOff = new Text("X");
    public boolean opening = true;

    public GameSound(String musicFile){
        this.musicFile = musicFile;
        sound = new Media(Paths.get(musicFile).toUri().toString());
        this.audioClip = new AudioClip(sound.getSource());
        this.audioClip.play();
    }
    public void setTurnOff() {
        this.turnOff.setFont(new Font("Comic Sans MS", 53));
        this.turnOff.setFill(Color.RED);
        this.turnOff.setX(1130);
        this.turnOff.setY(50);
    }

    public void setOpening(boolean other) {
        this.opening = other;
    }
    public Text getTurnOff() {return this.turnOff;}
    public AudioClip getAudioClip() {return this.audioClip;}


}
