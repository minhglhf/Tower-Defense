package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class GameStage {
    private Stage stage;
    private Scene scene, scene1;
    private Pane root, root1;
    public GameSound startGameSound;
    public GameSound inGameSound;

    public GameStage(Stage st){
        this.stage = st;
        root = new Pane();
        root1 = new Pane();
        scene = new Scene(root);
        scene1 = new Scene(root1);
    }

    public void setMenu(){
        root.getChildren().add(new ImageView(new Image(Const.MENU_IMAGE)));

        Button buttonStart = new Button();
        buttonStart.setGraphic(new ImageView(new Image(Const.START_BUTTON_IMAGE)));
        buttonStart.setBackground(null);
        buttonStart.setLayoutX(495);
        buttonStart.setLayoutY(406);

        root.getChildren().add( buttonStart);
        buttonStart.setOnAction(actionEvent -> {
            this.stage.setScene(scene1);
            this.startGameSound.audioClip.stop();
            if(this.inGameSound.opening) {
                this.inGameSound.audioClip.play();
            }
        });
    }

    public void createSound() {
        this.startGameSound = new GameSound(Const.MUSIC_START_GAME);
        this.inGameSound = new GameSound(Const.MUSIC_IN_GAME);
        this.inGameSound.setTurnOff();
        inGameSound.audioClip.stop();
        inGameSound.getAudioClip().setVolume(0.3);
    }

    public void mainGame(){
        GameField gameField = new GameField();

        gameField.MainGame(root1, scene1,scene,stage, startGameSound, inGameSound);

        MusicInGame();

        Text textC = this.decorate("Choose Towers", 1050, 90);
        Text text1 = this.decorate("100$", 1130, 210);
        Text text2 = this.decorate("50$", 1130, 360);
        Text text3 = this.decorate("20$", 1130, 510);
        root1.getChildren().addAll(text1,text2,text3,textC);

        scene1.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Tower Defense");
        stage.setResizable(false);
        stage.show();
    }

    public Text decorate(String s, double posX, double posY) {
        Text text = new Text(s);
        text.setX(posX);
        text.setY(posY);
        text.setFont(new Font("Comic Sans MS", 33));
        text.setFill(Color.GOLD);
        text.setStroke(Color.BLACK);
        return text;
    }

    public void MusicInGame(){
        ButtonInGame controlSound = new ButtonInGame(Const.CONTROL_SOUND_IMAGE, 1100, 5, root1);
        controlSound.button.setOnMouseClicked(event -> {
            if(inGameSound.opening) {
                inGameSound.audioClip.stop();
                root1.getChildren().add(inGameSound.getTurnOff());
                inGameSound.setOpening(false);
            }
            else {
                root1.getChildren().remove(inGameSound.getTurnOff());
                inGameSound.audioClip.play();
                inGameSound.setOpening(true);
            }
        });
        inGameSound.getTurnOff().setOnMouseClicked(event1 -> {
            root1.getChildren().remove(inGameSound.getTurnOff());
            inGameSound.audioClip.play();
            inGameSound.setOpening(true);
        });
    }

    public void playGame(){
        createSound();
        setMenu();
        mainGame();
    }

}
