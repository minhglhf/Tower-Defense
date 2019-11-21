package map;

import Tower.Bullet;
import javafx.geometry.BoundingBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sample.GameTile;

public class Mountain extends GameTile{
    private Title pre = null;

    public Mountain() {

    }

    public void setPre(Title pre){this.pre = pre;}

    public Title getPre(){return this.pre;}

    public void setMove(TitleMap titleMap, double posX, double posY){
        Title title = titleMap.getTitle(posX, posY);
        if( title != pre) {
            if( title != null && !title.getIsAdd()) title.show();
            if( pre != null) pre.hide();
            pre = title;
        }
    }

    public void setMountainEvent(TitleMap titleMap){
        titleMap.setOnMouseEntered(event ->{ setMove(titleMap, event.getX(), event.getY()); });
        titleMap.setOnMouseMoved(event ->{ setMove(titleMap, event.getX(), event.getY()); });
        titleMap.setOnMouseExited(event ->{
            if( pre != null) pre.hide();
            pre = null;
        });
    }
}
