package Tower;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import sample.Const;
import enemys.Enemy;

public class SniperTower extends Tower {

    public SniperTower() {
        super( Const.SNIPER_TOWER_MONEY, Const.SNIPER_TOWER_RANGE,Const.SNIPER_TOWER_SHOOT_SPEED, Const.SNIPER_TOWER_DAMAGE,
                Const.SNIPER_TOWER_IMAGE,Const.SNIPER_TOWER_BULLET_IMAGE);
    }

}
