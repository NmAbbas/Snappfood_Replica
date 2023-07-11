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
        String a = "";
        if (Restaurant.getRestaurantList().size() != 0) {
            gridPane.setPrefHeight(105*Restaurant.getRestaurantList().size());
            Button[] buttons = new Button[Restaurant.getRestaurantList().size()];
            //
            buttons[0] = new Button(Restaurant.getRestaurantList().get(0).getName());
            buttons[0].setPrefWidth(480);
            buttons[0].setPrefHeight(100);
            ImageView imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
            buttons[0].setGraphicTextGap(buttons[0].getWidth()-80-buttons[0].getText().length()-20);
            gridPane.add(buttons[0],0,0);
            for (int i = 0; i < Restaurant.getRestaurantList().size(); i++){
                buttons[i] = new Button(Restaurant.getRestaurantList().get(i).getName());
                buttons[i].setPrefWidth(480);
                buttons[i].setPrefHeight(100);
                imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(i).getImageURL())));
                buttons[i].setGraphic(imageView);
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
    }
}
