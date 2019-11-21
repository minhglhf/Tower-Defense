package Road;

import Tower.Tower;
import enemys.Enemy;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import sample.GameField;
import sample.GameStage;

public class Road {
    public Road(){

    }

    public void setPath(Enemy enemy, Pane pane) {
        if (enemy.getImageView().getX() > 136 && enemy.getImageView().getY() < 274) {
            enemy.getImageView().setRotate(90);
            enemy.getImageView().setX(enemy.getImageView().getX() - enemy.getSpeed());
            enemy.getHpShow().setX(enemy.getHpShow().getX() - enemy.getSpeed());
            enemy.getHpShow2().setX(enemy.getHpShow2().getX() - enemy.getSpeed());
            enemy.setRealCenX(-22);
            enemy.setRealCenY(0);
        } else if (enemy.getImageView().getX() <= 136 && enemy.getImageView().getX() >= 118 && enemy.getImageView().getY() < 274  ) {
            enemy.getImageView().setRotate(0);
            enemy.getImageView().setY(enemy.getImageView().getY() + enemy.getSpeed());
            enemy.getHpShow().setY(enemy.getHpShow().getY() + enemy.getSpeed());
            enemy.getHpShow2().setY(enemy.getHpShow2().getY() + enemy.getSpeed());
            enemy.setRealCenX(0);
            enemy.setRealCenY(22);
        } else if (enemy.getImageView().getY() >= 274 && enemy.getImageView().getY() <= 289 && enemy.getImageView().getX() < 865) {
            enemy.getImageView().setRotate(-90);
            enemy.getImageView().setX(enemy.getImageView().getX() + enemy.getSpeed());
            enemy.getHpShow().setX(enemy.getHpShow().getX() + enemy.getSpeed());
            enemy.getHpShow2().setX(enemy.getHpShow2().getX() + enemy.getSpeed());
            enemy.setRealCenX(22);
            enemy.setRealCenY(0);
        } else if (enemy.getImageView().getX() >= 865 && enemy.getImageView().getX() <= 926 && enemy.getImageView().getY() < 442) {
            enemy.getImageView().setRotate(0);
            enemy.getImageView().setY(enemy.getImageView().getY() + enemy.getSpeed());
            enemy.getHpShow().setY(enemy.getHpShow().getY() + enemy.getSpeed());
            enemy.getHpShow2().setY(enemy.getHpShow2().getY() + enemy.getSpeed());
            enemy.setRealCenX(0);
            enemy.setRealCenY(22);
        } else if (enemy.getImageView().getX() > 0 && enemy.getImageView().getY() >= 442 && enemy.getImageView().getY() <= 484) {
            enemy.getImageView().setRotate(90);
            enemy.getImageView().setX(enemy.getImageView().getX() - enemy.getSpeed());
            enemy.getHpShow().setX(enemy.getHpShow().getX() - enemy.getSpeed());
            enemy.getHpShow2().setX(enemy.getHpShow2().getX() - enemy.getSpeed());
            enemy.setRealCenX(-22);
            enemy.setRealCenY(0);
        }
        else if(enemy.getImageView().getX() <= 0) {
            pane.getChildren().removeAll(enemy.getImageView(), enemy.getHpShow(), enemy.getHpShow2());
        }
    }
}
