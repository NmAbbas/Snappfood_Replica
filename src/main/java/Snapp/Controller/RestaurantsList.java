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
            gridPane.setPrefHeight(105*Restaurant.getRestaurantList().size());
            Button[] buttons = new Button[Restaurant.getRestaurantList().size()];
            //Restaurant 0
            buttons[0] = new Button(Restaurant.getRestaurantList().get(0).getName());
            buttons[0].setPrefWidth(480);
            buttons[0].setPrefHeight(100);
            ImageView imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
            buttons[0].setGraphicTextGap(buttons[0].getWidth()-80-buttons[0].getText().length()-20);
            gridPane.add(buttons[0],0,0);
            //Restaurant 1->
            for (int i = 1; i < Restaurant.getRestaurantList().size(); i++){
                buttons[i] = new Button(Restaurant.getRestaurantList().get(i).getName());
                buttons[i].setPrefWidth(480);
                buttons[i].setPrefHeight(100);
                imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                buttons[i].setGraphicTextGap(buttons[i].getWidth()-80-buttons[i].getText().length()-20);
                gridPane.addRow(i,buttons[i]);
            }
        }
    }
}
