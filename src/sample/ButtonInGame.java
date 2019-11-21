package sample;

import Tower.MachineGunTower;
import Tower.NormalTower;
import Tower.SniperTower;
import Tower.Tower;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ButtonInGame extends Button{
    public Button button;
    Circle circle = new Circle();

    ButtonInGame(String path, double X, double Y, Pane pane){
        button = new Button();
        button.setGraphic(new ImageView(new Image(path)));
        button.setLayoutX(X);
        button.setLayoutY(Y);
        pane.getChildren().add(button);
        button.setBackground(null);
    }

    public Button getButton(){
        return this.button;
    }


    public Tower chooseTower(int moneyHave, Scene scene1, int towerNumber){
            if(towerNumber ==1 ) {
                Tower tower = new SniperTower();
                if(moneyHave >= tower.getMoney()) {
                    scene1.setCursor(new ImageCursor(new Image(Const.SNIPER_TOWER_IMAGE)));
                    return tower;
                }
            }

            if(towerNumber ==2 ) {
                Tower tower = new MachineGunTower();
                if(moneyHave >= tower.getMoney()) {
                    scene1.setCursor(new ImageCursor(new Image(Const.MACHINE_GUN_TOWER_IMAGE)));
                    return tower;
                }
            }
            if(towerNumber ==3 ){
                Tower tower = new NormalTower();
                if(moneyHave >= tower.getMoney()) {
                    scene1.setCursor(new ImageCursor(new Image(Const.NORMAL_TOWER_IMAGE)));
                    return tower;
                }
            }
            return null;
        }

        public Text setTextEffect(Pane pane, int x, Color color){
            Text dameInf = new Text("Damage " + String.valueOf(x));
            dameInf.setX(this.button.getLayoutX());
            dameInf.setY(this.button.getLayoutY() + 100);
            dameInf.setFont(new Font("Comic Sans Ms", 20));
            dameInf.setFill(color);
            pane.getChildren().add(dameInf);
            return dameInf;
        }
}
