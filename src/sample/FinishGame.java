package sample;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FinishGame {
    private ImageView Img;
    private Pane root;
    private Scene scene;
    public FinishGame (String path){
        this.Img = new ImageView(new Image(path));
    }
    public void setEffect() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2.5), this.Img);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.play();
    }
    public ImageView getGameOverImg() {return this.Img;}
    public Scene getScene() {
        this.root = new Pane(this.getGameOverImg());
        this.scene = new Scene(this.root, 1280, 720);
        return this.scene;
    }
}
