package Tower;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Money {
    private Text moneyText = new Text();

    public Money(){
        this.moneyText.setFont(new Font("Comic Sans Ms", 45));
        this.moneyText.setStroke(Color.BLACK);
        this.moneyText.setFill(Color.GOLD);
        this.moneyText.setText("1000");
        this.moneyText.relocate(900, 600);
    }

    public Text getMoneyText(){
        return this.moneyText;
    }

    public void setMoneyText(int updateMoney){
        this.moneyText.setText( String.valueOf(updateMoney));
    }

}
