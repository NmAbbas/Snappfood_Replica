package Snapp.Controller;

import Snapp.Cart;
import Snapp.Restaurant;
import Snapp.SnappApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RestaurantsList implements Initializable {
    public GridPane gridPane;
    public void openCart() throws IOException {
        SnappApplication.changeScene("user-cart.fxml");
    }
    public void openHome() throws IOException {
        SnappApplication.changeScene("user-home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showRestaurantList(Restaurant.getRestaurantList());
       /* if (Restaurant.getRestaurantList().size() != 0) {
            gridPane.setPrefHeight(135*Restaurant.getRestaurantList().size());
            Button[] buttons = new Button[Restaurant.getRestaurantList().size()];
            //Restaurant 0
            buttons[0] = new Button(Restaurant.getRestaurantList().get(0).getName());
            ImageView imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
            gridPane.add(buttons[0],0,0);
            buttons[0].setOnAction(e -> {
                User.getActiveUser().setActiveRestaurant(Restaurant.getRestaurantList().get(0));
                try
                {
                    User.getActiveUser().setCart(new Cart(User.getActiveUser().getActiveRestaurant(), User.getActiveUser()));
                    SnappApplication.changeScene("user-food-list.fxml");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            });
            //Restaurant 1->
            for (int i = 1; i < Restaurant.getRestaurantList().size(); i++){
                buttons[i] = new Button(Restaurant.getRestaurantList().get(i).getName());
                imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(Restaurant.getRestaurantList().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                gridPane.addRow(i,buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    User.getActiveUser().setActiveRestaurant(Restaurant.getRestaurantList().get(k));
                    try
                    {
                        SnappApplication.changeScene("user-food-list.fxml");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                });
            }
            gridPane.setVgap(10);
        } */
    }

    public void showRestaurantList(ArrayList<Restaurant> restaurants)
    {
        if (restaurants.size() != 0) {
            gridPane.setPrefHeight(135*restaurants.size());
            Button[] buttons = new Button[restaurants.size()];
            //Restaurant 0
            buttons[0] = new Button(restaurants.get(0).getName());
            ImageView imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(restaurants.get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
            gridPane.add(buttons[0],0,0);
            buttons[0].setOnAction(e -> {
                User.getActiveUser().setActiveRestaurant(restaurants.get(0));
                try
                {
                    User.getActiveUser().setCart(new Cart(User.getActiveUser().getActiveRestaurant(), User.getActiveUser()));
                    SnappApplication.changeScene("user-food-list.fxml");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            });
            //Restaurant 1->
            for (int i = 1; i < restaurants.size(); i++){
                buttons[i] = new Button(restaurants.get(i).getName());
                imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(restaurants.get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                gridPane.addRow(i,buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    User.getActiveUser().setActiveRestaurant(restaurants.get(k));
                    try
                    {
                        SnappApplication.changeScene("user-food-list.fxml");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                });
            }
            gridPane.setVgap(10);
        }
    }
}
