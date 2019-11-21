package enemys;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Const;

public class BossEnemy extends Enemy {
    public BossEnemy(){
        super(1, 2000, 300, 1500,10, Const.TYPE_BOSS, "file:Image/bossEnemy.png");
    }
}
