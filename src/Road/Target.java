package Road;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Const;

public class Target {
    private double x;
    private double y;
    public ImageView towerTarget;
    public ImageView heart;
    public Rectangle HPGreen = new Rectangle(100, 10, Color.LIMEGREEN);
    public Rectangle HPRed = new Rectangle(100, 10, Color.RED);
    public void reset() {
        this.HPGreen.setWidth(100);
        this.HPGreen.setStroke(Color.RED);
    }
    public Target(double x, double y) {
        this.towerTarget = new ImageView(new Image(Const.TOWER_TARGET));
        this.towerTarget.setX(10);
        this.towerTarget.setY(410);
        this.heart = new ImageView(new Image(Const.HEART));
        this.heart.setX(20);
        this.heart.setY(615);
        this.HPRed.setX(55);
        this.HPRed.setY(630);
        this.HPGreen.setX(55);
        this.HPGreen.setY(630);
        reset();
        this.x = x;
        this.y = y;
    }
    public void setHPGreen(double width) {
        this.HPGreen.setWidth(this.HPGreen.getWidth() - width);
    }
    public Rectangle getHPGreen(){return this.HPGreen;}
    public double getX() {return this.x;}
    public double getY() {
        return this.y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
}
