package enemys;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Const;

public class NormalEnemy extends Enemy {
    public NormalEnemy() {
        super(2,  500, 100, 500,5, Const.TYPE_NORMAL, "file:Image/normalEnemy.png");
    }
}
