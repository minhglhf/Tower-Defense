package Tower;

import enemys.Enemy;
import javafx.scene.layout.Pane;
import sample.GameTiles;

import java.util.List;

/**
 * Created by hung on 19/11/2019.
 */
public interface Towers extends GameTiles {
    void drawCircle(Pane pane);
    void findClosetEnemy(List<Enemy> enemies);
    void rotateTower(Enemy enemy);
    double getDisEnemyAndTower(Enemy enemy);
    void removeCircle(Pane pane);
    String getBulletName();
    int getShootSpeed();
    int getMoney();
    void addTower(List<Tower> towers, Tower tower);
    void sellTower(List<Tower> towers, Tower tower);
    double getShootingRange();
}
