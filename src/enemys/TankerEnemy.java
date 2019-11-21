package enemys;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Const;

public class TankerEnemy extends Enemy {
    public TankerEnemy(){
        super(1, 1500, 200, 1000,10, Const.TYPE_TANK, "file:Image/tankerEnemy.png");
    }

}
