package Snapp.Controller;

import Snapp.Restaurant;
import Snapp.SnapApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantsList implements Initializable {
    public GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Restaurant.getRestaurantList().size() != 0) {
            gridPane.setPrefHeight(135*Restaurant.getRestaurantList().size());
            Button[] buttons = new Button[Restaurant.getRestaurantList().size()];
            //Restaurant 0
            buttons[0] = new Button(Restaurant.getRestaurantList().get(0).getName());
            ImageView imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
//            buttons[0].setGraphicTextGap(buttons[0].getWidth()-80-buttons[0].getText().length()-20);
            gridPane.add(buttons[0],0,0);
            //Restaurant 1->
            for (int i = 1; i < Restaurant.getRestaurantList().size(); i++){
                buttons[i] = new Button(Restaurant.getRestaurantList().get(i).getName());
                imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
//                buttons[i].setGraphicTextGap(buttons[i].getWidth()-80-buttons[i].getText().length()-20);
                gridPane.addRow(i,buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    User.getActiveUser().setActiveRestaurant(Restaurant.getRestaurantList().get(k));
                    try
                    {
                        SnapApplication.changeScene("restaurant-view.fxml");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                });
            }
            gridPane.setVgap(10);
        }
    }
    public void openUserSignUp() throws IOException
    {
        SnapApplication.changeScene("user-sign-up.fxml");
    }
}
