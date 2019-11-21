package Tower;

import enemys.Enemy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import map.Title;
import sample.Const;
import sample.GameEntity;
import sample.GameTile;

import java.util.List;

public abstract class Tower extends GameTile implements Towers {
    private int shootSpeed;
    private int money;
    private double shootingRange;
    private int damageT;
    public double closetEnemy;
    public double r = 0.0;
    private Circle circle = new Circle();
    public String bulletName;
    private Text sellOrNot = new Text();

    public Tower(int money, double shootingRange, String nameOfFile){
        super(nameOfFile);
        this.money = money;
        this.shootingRange = shootingRange;
    }

    public Tower(int money, double shootingRange, String nameOfFile, String bulletName){
        super(nameOfFile);
        this.money = money;
        this.shootingRange = shootingRange;
        this.bulletName = bulletName;
    }

    public Tower(int money, double shootingRange, int shootSpeed,int damageT , String nameOfFile, String bulletName){
        super(nameOfFile);
        this.money = money;
        this.shootingRange = shootingRange;
        this.shootSpeed = shootSpeed;
        this.damageT = damageT;
        this.bulletName = bulletName;
    }

    public void drawCircle(Pane pane){
        this.circle.setRadius(this.getShootingRange());
        this.circle.setCenterX(this.getCenX());
        this.circle.setCenterY(this.getCenY());
        this.circle.setFill(Color.TRANSPARENT);
        this.circle.setStroke(Color.BLUE);
        pane.getChildren().add(this.circle);
    }

    public void findClosetEnemy(List<Enemy> enemies){
        int enemyIndex = 0;
        if(enemies.size() != 0) {
            this.closetEnemy = getDisEnemyAndTower(enemies.get(0));
            for (int i = 0; i < enemies.size(); i++) {
                double distance = getDisEnemyAndTower(enemies.get(i));
                if (distance <= this.closetEnemy) {
                    this.closetEnemy = distance;
                    enemyIndex = i;
                }
            }
            if (getDisEnemyAndTower(enemies.get(enemyIndex)) <= this.getShootingRange()) {
                rotateTower(enemies.get(enemyIndex));
            } else this.getImageView().setRotate(0);
        }
        else{
           this.getImageView().setRotate(0);
        }
    }

    public void rotateTower(Enemy enemy){
            if (enemy.getCenX() >= this.getCenX() && enemy.getCenY() <= this.getCenY()) {
                double disX = -this.getCenX() + enemy.getCenX();
                double disY = this.getCenY() - enemy.getCenY();

                double r = Math.atan(disX / disY);
                this.getImageView().setRotate(Math.toDegrees(r));
            }
            if (enemy.getCenX() < this.getCenX() && enemy.getCenY() <= this.getCenY()) {
                double disX = this.getCenX() - enemy.getCenX();
                double disY = this.getCenY() - enemy.getCenY();

                double r = Math.atan(disX / disY);
                this.getImageView().setRotate(-Math.toDegrees(r));
            }
            if (enemy.getCenX() < this.getCenX() && enemy.getCenY() > this.getCenY()) {
                double disX = this.getCenX() - enemy.getCenX();
                double disY = -this.getCenY() + enemy.getCenY();

                double r = Math.atan(disX / disY);
                this.getImageView().setRotate(Math.toDegrees(r) - 180);
            }
            if (enemy.getCenX() >= this.getCenX() && enemy.getCenY() > this.getCenY()) {
                double disX = -this.getCenX() + enemy.getCenX();
                double disY = -this.getCenY() + enemy.getCenY();

                double r = Math.atan(disX / disY);
                this.getImageView().setRotate(180 - Math.toDegrees(r));
            }
    }

    public void confirmSell(Pane pane){
        this.sellOrNot = new Text("Sell this tower?");
        if(this.getCenY() >= Const.MAP_HEIGHT / 2){
            this.sellOrNot.setX(this.getCenX() - 50);
            this.sellOrNot.setY(this.getCenY() - 30);
        }
        else if(this.getCenY() < Const.MAP_HEIGHT / 2) {
            this.sellOrNot.setX(this.getCenX() - 50);
            this.sellOrNot.setY(this.getCenY() + 30);
        }
        this.sellOrNot.setFont(new Font("Comic Sans Ms", 20));
        this.sellOrNot.setFill(Color.BLUE);
        pane.getChildren().add(this.sellOrNot);
    }

    public void removeConfirmSell(Pane pane){
        pane.getChildren().remove(this.sellOrNot);
    }

    public double getDisEnemyAndTower(Enemy enemy){
        double distance = Math.sqrt((this.getCenX() - enemy.getCenX()) * (this.getCenX() - enemy.getCenX()) +
                (this.getCenY() - enemy.getCenY()) * (this.getCenY() - enemy.getCenY()));
        return distance;
    }

    public void removeCircle(Pane pane){
        pane.getChildren().remove(this.circle);
    }

    public String getBulletName(){
        return this.bulletName;
    }

    public int getShootSpeed(){
        return this.shootSpeed;
    }

    public int getDamageT(){return this.damageT;}

    public int getMoney(){return this.money;}

    public void addTower(List<Tower> towers, Tower tower){
        towers.add(tower);
    }

    public void sellTower(List<Tower> towers, Tower tower){
        towers.remove(tower);
    }

    public double getShootingRange(){return this.shootingRange;}


}
