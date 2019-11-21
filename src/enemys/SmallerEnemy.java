package enemys;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Const;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy(){
        super(1.5, 500, 100, 0,3, Const.TYPE_SMALL, "file:Image/smallerEnemy.png");
    }

}
