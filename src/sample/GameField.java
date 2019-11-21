package sample;

import Road.Road;
import Tower.Bullet;
import Tower.Tower;
import enemys.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.application.Application.launch;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.TitleMap;
import Tower.Money;
import java.util.ArrayList;
import java.util.List;
import Road.Target;

public class GameField{
    public GameField(){

    }

    protected Tower tower = null;
    protected Money money;
    private boolean clicked = false;
    protected boolean sell = false;
    private int moneyHave = 1000;
    protected Pane textLayer = new Pane();
    protected Level level = new Level();
    protected Target target = new Target(50, 600);
    protected List<Tower> towers = new ArrayList<>();
    protected List<Bullet> bullets = new ArrayList<>();
    protected List<Enemy> enemies = new ArrayList<>();
    private boolean dameInfIsAdded = false;
    private boolean passImgIsAdded = false;
    protected Text dameInf;
    private int levelInt = 1;
    private List<Double> listMouseClickedX = new ArrayList<>();
    private List<Double> listMouseClickedY = new ArrayList<>();

    public void setTotalEnemies() { this.level.setTotalEnemies(); }

    public void processFinishGame(Pane root1,TitleMap titleMap,  List<Tower> towers, List<Bullet> bullets) {
        for(int i = 0; i < towers.size(); i++) {
            root1.getChildren().removeAll(towers.get(i).getImageView(), bullets.get(i).getImageView());
            towers.remove(i);
            bullets.remove(i);
            titleMap.getTitle(listMouseClickedX.get(i), listMouseClickedY.get(i)).setIsAdd(false);
            listMouseClickedX.remove(i);
            listMouseClickedY.remove(i);
            i--;
        }
        if(dameInfIsAdded) {
            root1.getChildren().remove(dameInf);
            dameInfIsAdded = false;
        }
        this.moneyHave = 1000;
        this.money.setMoneyText(moneyHave);
        level.setLevel(0);
        level.setShowLevels();
        target.reset();
    }

    public void MainGame(Pane root1,Scene scene1, Scene scene, Stage stage, GameSound startGameSound, GameSound inGameSound){
        this.setTotalEnemies();
        GameSound enemyDie = new GameSound(Const.ENEMY_DIE_SOUND);
        enemyDie.getAudioClip().setVolume(50.0);
        enemyDie.getAudioClip().stop();
        GameSound collision = new GameSound(Const.COLLISION_SOUND);
        collision.getAudioClip().setVolume(0.2);
        collision.getAudioClip().stop();
        FinishGame gameOver = new FinishGame(Const.GAME_OVER);
        FinishGame gameWin = new FinishGame(Const.WIN_GAME);
        Scene gameOverScene = gameOver.getScene();
        Scene gameWinScene = gameWin.getScene();
        ImageView passLevel = new ImageView(new Image(Const.PASS_LEVEL));
        passLevel.setX(200);
        passLevel.setY(100);
        ImageView nextImg = new ImageView(new Image(Const.NEXT_BUTTON_IMG));
        Button nextButton = new Button();
        nextButton.setGraphic(nextImg);
        nextButton.setBackground(null);
        nextButton.setLayoutX(453);
        nextButton.setLayoutY(410);
        nextButton.setOnMouseClicked(event -> {
            if(passImgIsAdded) {
                root1.getChildren().removeAll(nextButton, passLevel);
                clicked = false;
                passImgIsAdded = false;
            }
        });

        root1.getChildren().addAll(new ImageView(new Image(Const.BACKGROUND_IMAGE)), level.getShowLevels());
        root1.getChildren().addAll(target.towerTarget, target.HPRed, target.HPGreen,target.heart);

        ButtonInGame button1 = new ButtonInGame(Const.SNIPER_TOWER_IMAGE, 1050, 150, root1);
        ButtonInGame button2 = new ButtonInGame(Const.MACHINE_GUN_TOWER_IMAGE, 1050, 300, root1);
        ButtonInGame button3 = new ButtonInGame(Const.NORMAL_TOWER_IMAGE, 1050, 450, root1);
        ButtonInGame sellButton = new ButtonInGame(Const.SELL_BUTTON_IMAGE, 1050, 600, root1);

        scene1.setOnMouseMoved(event -> {
            button1.getButton().setOnMouseMoved(event1 -> {
                if(!dameInfIsAdded) {
                    dameInf = button1.setTextEffect(root1, Const.SNIPER_TOWER_DAMAGE, Color.RED);
                    dameInfIsAdded = true;
                }
            });
            button2.getButton().setOnMouseMoved(event2 -> {
                if(!dameInfIsAdded) {
                    dameInf = button2.setTextEffect(root1, Const.MACHINE_GUN_TOWER_DAMAGE, Color.BLUE);
                    dameInfIsAdded = true;
                }
            });
            button3.getButton().setOnMouseMoved(event3 -> {
                if(!dameInfIsAdded) {
                    dameInf = button3.setTextEffect(root1, Const.NORMAL_TOWER_DAMAGE, Color.BROWN);
                    dameInfIsAdded = true;
                }
            });
            if(dameInfIsAdded) {
                root1.getChildren().remove(dameInf);
                dameInfIsAdded = false;
            }
        });

        button1.getButton().setOnAction(actionEvent -> {
            tower = button1.chooseTower(moneyHave,scene1,1);
            if(tower != null) subMoney(tower.getMoney());
            money.setMoneyText(moneyHave);
        });
        button2.getButton().setOnAction(actionEvent -> {
            tower = button2.chooseTower(moneyHave,scene1,2);
            if(tower != null) subMoney(tower.getMoney());
            money.setMoneyText(moneyHave);
        });
        button3.getButton().setOnAction(actionEvent -> {
            tower = button3.chooseTower(moneyHave,scene1,3);
            if(tower != null) subMoney(tower.getMoney());
            money.setMoneyText(moneyHave);
        });
        sellButton.getButton().setOnAction(actionEvent -> {
            scene1.setCursor(new ImageCursor(new Image(Const.SELL_BUTTON_IMAGE)));
            sell = true;
        });

        TitleMap titleMap = new TitleMap();
        titleMap.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (titleMap.getTitle(mouseEvent.getX(), mouseEvent.getY()) != null  && tower != null && !titleMap.getTitle(mouseEvent.getX(), mouseEvent.getY()).getIsAdd()) {

                listMouseClickedX.add(mouseEvent.getX());
                listMouseClickedY.add(mouseEvent.getY());

                towers.add(tower);
                BoundingBox boundingBox = titleMap.setBoundingBox(mouseEvent.getX(), mouseEvent.getY());

                tower.getImageView().setX(boundingBox.getMinX()+ boundingBox.getWidth() / 2- tower.getImage().getWidth()/2);
                tower.getImageView().setY(boundingBox.getMinY()+ boundingBox.getHeight() / 2 - tower.getImage().getHeight()/2);

                titleMap.getTitle(mouseEvent.getX(), mouseEvent.getY()).setIsAdd(true);

                Bullet bullet = new Bullet(tower.getBulletName(), tower);
                bullets.add(bullet);

                root1.getChildren().addAll(tower.getImageView(), bullet.getImageView());
                scene1.setCursor(Cursor.DEFAULT);

                tower = null;
                sell = false;
            }
        });

        root1.getChildren().add(textLayer);
        root1.getChildren().add(titleMap);

        Road road = new Road();
        money = new Money();
        textLayer.getChildren().add(money.getMoneyText());

        AnimationTimer moneyLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {money.setMoneyText(moneyHave);

            }
        };
        moneyLoop.start();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(enemies.get(0).getRun()==false) {
                    root1.getChildren().addAll(enemies.get(0).getImageView(), enemies.get(0).getHpShow2(), enemies.get(0).getHpShow());
                    enemies.get(0).setRun(true);
                }
                road.setPath(enemies.get(0), root1);
                for(int i = 1; i < enemies.size(); i++){
                    if(enemies.get(i-1).getImageView().getX() <= 800){
                        if(enemies.get(i).getRun() == false) {
                            root1.getChildren().addAll(enemies.get(i).getImageView(), enemies.get(i).getHpShow2(), enemies.get(i).getHpShow());
                            enemies.get(i).setRun(true);
                        }
                        road.setPath(enemies.get(i), root1);
                    }
                    else if(enemies.get(i-1).getImageView().getY() > 130) road.setPath(enemies.get(i), root1);
                }
                for(int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).getImageView().getX() <= target.getX() || enemies.get(i).getHpShow().getWidth() <= 0) {
                        if (enemies.get(i).getImageView().getX() <= target.getX()) target.setHPGreen(enemies.get(i).getDame());
                        if(enemies.get(i).getHpShow().getWidth() <= 0){
                            moneyHave+=enemies.get(i).getBonus();
                            if(inGameSound.opening) enemyDie.getAudioClip().play();
                        }
                        root1.getChildren().removeAll(enemies.get(i).getHpShow(), enemies.get(i).getHpShow2(), enemies.get(i).getImageView());
                        if (enemies.size() == 1) {
                            clicked = true;
                            enemies.remove(i);
                            levelInt++;
                            stop();
                            if(!passImgIsAdded) {
                                root1.getChildren().addAll(passLevel, nextButton);
                                passImgIsAdded = true;
                            }
                            break;
                        }
                        else {
                            enemies.remove(i);
                        }
                        i--;
                    }
                }
                if(target.getHPGreen().getWidth() <= 0){
                    for(int j = 0; j < enemies.size(); j++){
                        root1.getChildren().removeAll(enemies.get(j).getHpShow(), enemies.get(j).getHpShow2(), enemies.get(j).getImageView());
                        if (enemies.size() == 1) {
                            enemies.remove(j);
                            break;
                        } else {
                            enemies.remove(j);
                        }
                        j--;
                    }
                    levelInt = 1;
                    clicked = false;
                    stop();
                    stage.setScene(gameOverScene);
                }
                if(levelInt == 7 && target.getHPGreen().getWidth() >= 0) {
                    levelInt = 1;
                    clicked = false;
                    root1.getChildren().removeAll(passLevel, nextButton);
                    passImgIsAdded = false;
                    stop();
                    gameWin.setEffect();
                    stage.setScene(gameWinScene);
                }

                for(Tower t : towers) towerEvent(t, root1, scene1, titleMap);

                titleMap.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        if (titleMap.getTitle(e.getX(), e.getY()) != null && sell) {
                            scene1.setCursor(Cursor.DEFAULT);
                            sell = false;
                        }
                    }
                });

                for (Bullet bl : bullets) bl.findClosetEnemy(enemies ,collision, inGameSound.opening);

                for(Tower t : towers) t.findClosetEnemy(enemies);
            }
        };
        gameOverScene.setOnMouseClicked(event -> {
            this.processFinishGame(root1, titleMap, towers, bullets);
            stage.setScene(scene);
            startGameSound.audioClip.play();
            inGameSound.audioClip.stop();
        });
        gameWinScene.setOnMouseClicked(event -> {
            this.processFinishGame(root1, titleMap, towers, bullets);
            stage.setScene(scene);
            startGameSound.audioClip.play();
            inGameSound.audioClip.stop();
        });
        ButtonInGame playButton = new ButtonInGame(Const.PLAY_BUTTON, 350, 600, root1);
        playButton.getButton().setOnAction(event -> {
            if(!clicked && this.levelInt <= 6) {
                level.setLevel(this.levelInt);
                level.setShowLevels();
                if(enemies.size() == 0) {
                    enemies = level.setLevel();
                    System.out.println(enemies.size());
                }
                gameLoop.start();
                clicked = true;
            }
        });
        ButtonInGame quitButton = new ButtonInGame(Const.QUIT_BUTTON, 650, 600, root1);
        quitButton.getButton().setOnAction(event -> {
            if(!passImgIsAdded) {
                gameLoop.stop();
                stage.setScene(scene);
                clicked = false;
                inGameSound.audioClip.stop();
                startGameSound.audioClip.play();
            }
        });
    }

    public void towerEvent(Tower t, Pane pane1, Scene scene1, TitleMap titleMap){
        if(sell == true){
                t.getImageView().setOnMouseEntered(e ->{
                    t.confirmSell(pane1);
                });
                t.getImageView().setOnMouseExited(e ->{
                    t.removeConfirmSell(pane1);
                });
                t.removeCircle(pane1);
                t.getImageView().setOnMouseClicked(event -> {
                    t.removeConfirmSell(pane1);
                    titleMap.getTitle(event.getX(), event.getY()).setIsAdd(false);
                    pane1.getChildren().remove(t.getImageView());
                    for (int i = 0; i < bullets.size(); i++) {
                        if (bullets.get(i).getStartPosX() == t.getCenX() - bullets.get(i).getImage().getWidth() / 2 && bullets.get(i).getStartPosY() == t.getCenY()) {
                            pane1.getChildren().remove(bullets.get(i).getImageView());
                            bullets.remove(bullets.get(i));
                            i--;
                        }
                    }
                    towers.remove(t);
                    sell = false;
                    scene1.setCursor(Cursor.DEFAULT);
                    moneyHave += t.getMoney() / 2;
                });
        }
        else {
            t.getImageView().setOnMouseEntered(e -> {
                t.drawCircle(pane1);
            });
            t.getImageView().setOnMouseExited(e -> {
                t.removeCircle(pane1);
            });
        }
    }

    public void subMoney(int towerKind){
        this.moneyHave -= towerKind;
    }
}
