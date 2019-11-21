package map;

import javafx.css.Style;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Title extends StackPane {
    public Boolean isAdd;

    public Title() {
        this.isAdd = false;
    }
    public Boolean getIsAdd(){return this.isAdd;}

    public void setIsAdd(Boolean isAdd){this.isAdd = isAdd;}

    public void show() {
        getStyleClass().add("Spotlight");
    }

    public void hide() {
        getStyleClass().remove("Spotlight");
    }

}
