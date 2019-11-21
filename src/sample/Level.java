package sample;

import enemys.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public int level = 1;
    private int capacity = 40;
    public List<Enemy> totalEnemies = new ArrayList<>(capacity);
    public Text showLevels = new Text("Level: 0/6");
    public Level() {
        this.showLevels.setFont(new Font("Comic Sans MS", 30));
        this.showLevels.setFill(Color.BLUE);
        this.showLevels.setX(1050);
        this.showLevels.setY(600);
    }
    public void setTotalEnemies() {
        Enemy enemy = null;
        for(int i = 0; i < this.capacity; i++) {
            if(i >= 0 && i < 10) enemy = new SmallerEnemy();
            else if(i >= 10 && i < 20) enemy = new NormalEnemy();
            else if(i >= 20 && i < 30) enemy = new TankerEnemy();
            else if(i >= 30 && i < 40) enemy = new BossEnemy();

            this.totalEnemies.add(enemy);
        }
    }
    public List<Enemy> getTotalEnemies() {return this.totalEnemies;}
    public void setLevel(int level){
        this.level = level;
    }
    public void setShowLevels() {
        String str = String.valueOf(this.level);
        str = "Level: " + str + "/6";
        this.showLevels.setText(str);
    }

    public List setLevel(){
        List<Enemy> enemies = new ArrayList<>();
        if(this.level == 1) {
            this.setEnemies(0, 5, enemies);
            this.setEnemies(10, 12, enemies);
            this.setEnemies(10, 12, enemies);
            this.setEnemies(0, 2, enemies);
            this.setEnemies(10, 12, enemies);
        }
        else if(this.level == 2) {
            this.setEnemies(0, 10, enemies);
            this.setEnemies(10, 15, enemies);
            this.setEnemies(0, 5, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(10, 20, enemies);
        }
        else if(this.level == 3) {
            this.setEnemies(20, 25, enemies);
            this.setEnemies(10, 15, enemies);
            this.setEnemies(20, 32, enemies);
            this.setEnemies(10, 12, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(0, 15, enemies);
        }
        else if(this.level == 4) {
            this.setEnemies(30, 34, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(10, 15, enemies);
            this.setEnemies(10, 15, enemies);
            this.setEnemies(30, 35, enemies);
            this.setEnemies(0, 5, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(5, 15, enemies);
        }
        else if(this.level == 5) {
            this.setEnemies(20, 22, enemies);
            this.setEnemies(0, 5, enemies);
            this.setEnemies(22, 25, enemies);
            this.setEnemies(30, 35, enemies);
            this.setEnemies(10, 25, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(30, 35, enemies);
            this.setEnemies(0, 10, enemies);
            this.setEnemies(10, 20, enemies);
        }
        else if(level == 6) {
            this.setEnemies(30, 35, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(30, 35, enemies);
            this.setEnemies(20, 27, enemies);
            this.setEnemies(30, 35, enemies);
            this.setEnemies(0, 10, enemies);
            this.setEnemies(30, 32, enemies);
            this.setEnemies(10, 16, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(30, 35, enemies);
            this.setEnemies(0, 5, enemies);
            this.setEnemies(30, 35, enemies);
            this.setEnemies(20, 25, enemies);
            this.setEnemies(10, 15, enemies);
            this.setEnemies(30, 35, enemies);
        }
        return enemies;
    }

    public Enemy checkTypeEnemy(Enemy enemy, String type) {
        enemy = null;
        switch (type) {
            case "normal":
                enemy = new NormalEnemy();
                break;
            case "small":
                enemy = new SmallerEnemy();
                break;
            case "tank":
                enemy = new TankerEnemy();
                break;
            case "boss":
                enemy = new BossEnemy();
                break;
            default:
                System.out.println("type of enemy not exist.");
        }
        return enemy;
    }

    public void setEnemies(int n, int m, List<Enemy> enemies) {
        Enemy enemy = null;
        for(int i = n ; i < m; i++) {
            if(enemies.contains(this.getTotalEnemies().get(i))) {
                String type= this.getTotalEnemies().get(i).getType();
                enemy = checkTypeEnemy(enemy, type);
                enemy.reset();
                enemies.add(enemy);
            }
            else {
                this.getTotalEnemies().get(i).reset();
                enemies.add(this.getTotalEnemies().get(i));
            }
        }
    }
    public Text getShowLevels() {return this.showLevels;}
}
