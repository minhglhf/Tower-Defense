package map;

import javafx.geometry.BoundingBox;
import javafx.scene.layout.Pane;
import sample.Const;


public class TitleMap extends Pane{
    private int rows;
    private int columns;
    private double titleWidth;
    private double titleHeight;
    private Title[][] titleArray;

    public TitleMap() {
        this.columns = Const.MAP_COLUMN;
        this.rows = Const.MAP_ROW;
        this.titleWidth = Const.MAP_WIDTH/Const.MAP_COLUMN;
        this.titleHeight = Const.MAP_HEIGHT/Const.MAP_ROW;

        titleArray = new Title[rows][columns];
        getMap();

    }

    public void getMap(){
        int[][] map;
        map = new int[][]
                {
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
                        {0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 },
                        {1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }
                };
        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.columns; j++){
                if(map[i][j] == 1){
                    Title title = new Title();
                    title.setLayoutX(titleWidth * j);
                    title.setLayoutY(titleHeight * i);
                    title.setPrefWidth(titleWidth);
                    title.setPrefHeight(titleHeight);
                    titleArray[i][j] = null;
                    getChildren().add(title);
                }
                else{
                    Title title = new Title();
                    title.setLayoutX(titleWidth * j);
                    title.setLayoutY(titleHeight * i);
                    title.setPrefWidth(titleWidth);
                    title.setPrefHeight(titleHeight);
                    titleArray[i][j] = title;
                    getChildren().add(title);
                }
            }
        }

        Mountain mountain = new Mountain();
        mountain.setMountainEvent(this);

    }


    public BoundingBox setBoundingBox(double posX, double posY) {
        double XMin = titleWidth * (int) ( posX/titleWidth);
        double YMin = titleHeight *  (int) ( posY/titleHeight);
        return new BoundingBox( XMin, YMin, titleWidth, titleHeight);
    }

    public Title getTitle(double X, double Y) {
        int titleRow = (int) (Y/titleHeight);
        int titleColumn = (int) (X/titleWidth);
        if( titleColumn < 0 || titleColumn >= columns || titleRow < 0 || titleRow >= rows) return null;
        else return titleArray[titleRow][titleColumn];
    }
}
