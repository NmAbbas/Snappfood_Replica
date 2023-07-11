package Snapp.Controller;

import Snapp.Restaurant;
import Snapp.SnapApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantsList implements Initializable {
    public GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       if (Restaurant.getRestaurantList().size() != 0) {
            gridPane.setPrefHeight();
            for (Restaurant temp : Restaurant.getRestaurantList()){
                gridPane
            }
        }
        Button b = new Button("رستوران اکبر");
        b.setPrefWidth(480);
        b.setPrefHeight(100);
        ImageView i = new ImageView(new Image(SnapApplication.class.getResourceAsStream("/images/hamburger.png")));
        i.setFitWidth(80);
        i.setFitWidth(80);
        b.setGraphic(i);
        b.setGraphicTextGap(50);
        gridPane.add(b,0,0);
        Button a = new Button("رستوران اکبر");
        a.setPrefWidth(480);
        a.setPrefHeight(100);
        ImageView g = new ImageView(new Image(SnapApplication.class.getResourceAsStream("/images/hamburger.png")));
        i.setFitWidth(80);
        i.setFitWidth(80);
        a.setGraphic(g);
        a.setGraphicTextGap(50);
        gridPane.addRow(1,a);
    }
}
