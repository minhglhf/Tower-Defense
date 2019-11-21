package enemys;

import sample.GameEntities;

/**
 * Created by hung on 19/11/2019.
 */
public interface Enemies extends GameEntities{
    double getSpeed();
    void setSpeed(double speed);
    int getDame();
    void setDame(int dame);
    boolean getRun();
    void setRun(boolean run);
    int getHp();
    void setHp(int hp);
    void reset();
}