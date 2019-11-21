package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class GameEntity implements GameEntities{
    protected String nameOfFile;
    protected Image image;
    protected ImageView imageView;

    public GameEntity(){

    }


    public GameEntity(String nameOfFile){
        this.nameOfFile = nameOfFile;
        this.image = new Image(nameOfFile);
        this.imageView = new ImageView(image);
    }

    public GameEntity(String nameOfFile, double x, double y){
        this.nameOfFile = nameOfFile;
        this.image = new Image(nameOfFile);
        this.imageView = new ImageView(image);
        this.getImageView().setX(x);
        this.getImageView().setY(y);
    }
public String getNameOfFile(){return this.nameOfFile;}

    public ImageView getImageView(){return imageView;}

    public Image getImage(){return this.image;}

    public double getCenX(){
        return this.getImageView().getX() + this.getImage().getWidth()/2;
    }

    public double getCenY(){
        return this.getImageView().getY() + this.getImage().getHeight()/2;
    }

    public void setImageView(ImageView imageView){this.imageView = imageView;}

    public void setImage(Image image){this.image = image;}

}
