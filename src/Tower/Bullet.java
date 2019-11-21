package Tower;

import enemys.Enemy;
import javafx.geometry.BoundingBox;
import javafx.scene.layout.Pane;
import sample.GameEntity;
import sample.Const;
import sample.GameSound;

import java.awt.image.TileObserver;
import java.util.List;

public class Bullet extends GameEntity {
    private double bulletSpeed;
    private double maxDistanceCanShoot;
    private int damage;
    private int timeShoot;
    private double startPosX;
    private double startPosY;
    private double closetEnemy;
    private double getTime = 0;
    private double r = 0.0;
    private double aimX = 0.0;
    private double aimY = 0.0;
    private double rNow = 0.0;
    private int flag = 1;

    public Bullet(){}

    public Bullet(String nameOfFile){
        super(nameOfFile);
    }

    public Bullet(String nameOfFile, Tower tower){
        super(nameOfFile);
        this.startPosX = tower.getCenX() - this.getImage().getWidth() / 2;
        this.startPosY = tower.getCenY();
        this.getImageView().setX(tower.getCenX() - this.getImage().getWidth() / 2);
        this.getImageView().setY(tower.getCenY());
        this.maxDistanceCanShoot = tower.getShootingRange();
        this.bulletSpeed = Const.BULLET_SPEED;
        this.timeShoot = tower.getShootSpeed();
        this.damage = tower.getDamageT();
    }

    public double getDamage(){return this.damage;}
    public void setDamage() {
        if(this.getNameOfFile() == Const.SNIPER_TOWER_BULLET_IMAGE) this.damage = Const.SNIPER_TOWER_DAMAGE;
        else if(this.getNameOfFile() == Const.MACHINE_GUN_BULLET_IMAGE) this.damage = Const.MACHINE_GUN_TOWER_DAMAGE;
        else if(this.getNameOfFile() == Const.NORMAL_BULLET_IMAGE) this.damage = Const.NORMAL_TOWER_DAMAGE;
    }

    public double getStartPosX(){
        return this.startPosX;
    }

    public double getStartPosY(){
        return this.startPosY;
    }

    public void findClosetEnemy(List<Enemy> enemies, GameSound collision, boolean isOpening){
        getTime += 0.1;
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
            double tempX = enemies.get(enemyIndex).getCenX() + enemies.get(enemyIndex).getRealCenX();
            double tempY = enemies.get(enemyIndex).getCenY() + enemies.get(enemyIndex).getRealCenY();

            double disX = Math.abs(this.getStartPosX() - tempX);
            double disY =  Math.abs(this.getStartPosY() - tempY);

            r = Math.atan(disX / disY);
            if (this.getImageView().getX() == this.getStartPosX() && this.getImageView().getY() == this.getStartPosY()) {
                rNow = r;
                aimX = tempX;
                aimY = tempY;
            }

            if (checkEnemyInRange(enemies.get(enemyIndex))) {
                Shoot(enemies.get(enemyIndex),rNow, aimX, aimY, collision, isOpening);
            }
            else{
                this.getImageView().setX(this.getStartPosX());
                this.getImageView().setY(this.getStartPosY());
            }
        }
        else{
            this.getImageView().setX(this.getStartPosX());
            this.getImageView().setY(this.getStartPosY());
        }
    }

    public void Shoot(Enemy enemy,double rNow, double aimX, double aimY, GameSound collision, boolean isOpening) {
        if ((int) getTime == this.timeShoot) {
            flag = 1;
        }
            if (checkCollision(enemy)) {
                enemy.getHpShow().setWidth(enemy.getHpShow().getWidth() - (double) this.damage / enemy.getBeginHp() * 45);
                if(isOpening) collision.getAudioClip().play();
                this.getImageView().setX(this.getStartPosX());
                this.getImageView().setY(this.getStartPosY());
                getTime = 0.0;
                flag = 0;
            }
            if (getDisBulletAndTower(enemy) >= this.maxDistanceCanShoot) {
                this.getImageView().setX(this.getStartPosX());
                this.getImageView().setY(this.getStartPosY());
                getTime = 0.0;
                flag = 0;
            }
            if (flag == 1) {

                if (aimX >= this.getStartPosX() && aimY < this.getStartPosY()) {
                    this.getImageView().setX(this.getImageView().getX() + Math.sin(rNow) * Const.BULLET_SPEED);
                    this.getImageView().setY(this.getImageView().getY() - Math.cos(rNow) * Const.BULLET_SPEED);
                }
                if (aimX < this.getStartPosX() && aimY < this.getStartPosY()) {
                    this.getImageView().setX(this.getImageView().getX() - Math.sin(rNow) * Const.BULLET_SPEED);
                    this.getImageView().setY(this.getImageView().getY() - Math.cos(rNow) * Const.BULLET_SPEED);
                }
                if (aimX < this.getStartPosX() && aimY >= this.getStartPosY()) {
                    this.getImageView().setX(this.getImageView().getX() - Math.sin(rNow) * Const.BULLET_SPEED);
                    this.getImageView().setY(this.getImageView().getY() + Math.cos(rNow) * Const.BULLET_SPEED);
                }
                if (aimX >= this.getStartPosX() && aimY >= this.getStartPosY()) {
                    this.getImageView().setX(this.getImageView().getX() + Math.sin(rNow) * Const.BULLET_SPEED);
                    this.getImageView().setY(this.getImageView().getY() + Math.cos(rNow) * Const.BULLET_SPEED);
                }
            }
    }

    public boolean checkCollision(Enemy enemy){
            BoundingBox boundingBox = new BoundingBox(enemy.getImageView().getX(), enemy.getImageView().getY(),
                    enemy.getImage().getWidth(), enemy.getImage().getHeight());

            if (this.getCenX() >= boundingBox.getMinX() && this.getCenX() <= boundingBox.getMinX() + boundingBox.getWidth() &&
                    this.getCenY() >= boundingBox.getMinY() && this.getCenY() <= boundingBox.getMinY() + boundingBox.getHeight()) {
                return true;
            }
            return false;
    }

    public boolean checkEnemyInRange(Enemy enemy) {
        double distance = getDisEnemyAndTower(enemy);
        if (distance >= this.maxDistanceCanShoot) this.flag = 1;
        if (distance <= this.maxDistanceCanShoot) return true;
        return false;
    }

    public double getDisEnemyAndTower(Enemy enemy){
        double distance = Math.sqrt((this.getStartPosX() - enemy.getCenX()) * (this.getStartPosX() - enemy.getCenX()) +
                (this.getStartPosY() - enemy.getCenY()) * (this.getStartPosY() - enemy.getCenY()));
        return distance;
    }

    public double getDisBulletAndTower(Enemy enemy){
        double distance = Math.sqrt((this.getStartPosX() - this.getCenX()) * (this.getStartPosX() - this.getCenX()) +
                (this.getStartPosY() - this.getCenY()) * (this.getStartPosY() - this.getCenY()));
        return distance;
    }


}
