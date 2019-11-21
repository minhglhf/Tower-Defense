package enemys;

import Road.Spawner;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.GameEntity;


public abstract class Enemy extends GameEntity implements Enemies {
    private double speed;
    public boolean run = false;
    private int bonus;
    private int armor;
    private int hp;
    public int beginHp;
    public int beginRec;
    private int dame;
    private String type = "";
    public double realCenX = 0.0;
    public double realCenY = 0.0;

    public Rectangle hpShow = new Rectangle(45, 7, Color.LIMEGREEN);
    public Rectangle hpShow2 = new Rectangle(45, 7, Color.RED);

    public Spawner start = new Spawner(1000, 120);

    public Enemy(){

    }

    public Enemy(double speed, int hp, int bonus, int armor,int dame, String type, String nameOfFile){
        super(nameOfFile);
        this.speed = speed;
        this.hp = hp + armor;
        this.beginHp = hp + bonus;
        this.beginRec = 45;
        this.bonus = bonus;
        this.armor = armor;
        this.dame = dame;
        this.type = type;
        this.run = false;
        this.hpShow.setStroke(Color.RED);
        this.getImageView().setX(this.start.getX());
        this.getImageView().setY(this.start.getY());
        this.hpShow.setX(this.start.getX());
        this.hpShow.setY(this.start.getY() - 10);
        this.hpShow2.setX(this.start.getX());
        this.hpShow2.setY(this.start.getY() - 10);
    }

    public void setRealCenX(double realCenX){this.realCenX = realCenX;}

    public double getRealCenX(){return this.realCenX;}

    public void setRealCenY(double realCenX){this.realCenY = realCenY;}

    public double getRealCenY(){return this.realCenY;}

    public double getSpeed(){return this.speed;}

    public void setSpeed(double speed){this.speed = speed;}

    public void setDame(int dame) { this.dame = dame; }
    public int getDame() {return this.dame;}

    public void setType(String type) { this.type = type; }
    public String getType() {return this.type;}

    public void setRun(boolean run){this.run = run;}

    public boolean getRun(){return this.run;}

    public Rectangle getHpShow2() { return this.hpShow2; }

    public int getHp(){return this.hp;}

    public int getBeginHp(){return this.beginHp;}

    public void setHp(int hp){this.hp = hp;}

    public int getBonus(){return this.bonus;}
    public Rectangle getHpShow(){return this.hpShow;}

    public void reset() {
        this.setRun(false);
        this.getImageView().setX(1000);
        this.getImageView().setY(120);
        this.getHpShow().setWidth(45);
        this.getHpShow().setFill(Color.LIMEGREEN);
        this.getHpShow().setX(this.start.getX());
        this.getHpShow().setY(this.start.getY()-10);
        this.getHpShow2().setX(this.start.getX());
        this.getHpShow2().setY(this.start.getY()-10);
    }
}
