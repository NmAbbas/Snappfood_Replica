package Snapp.Controller;

import Snapp.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RestaurantsList implements Initializable {

    public enum Command
    {
        ALL, RESTAURANT, CAFE, SUPER, RECOMEND, NEAR;
    }

    public Command command = Command.ALL;
    public GridPane gridPane;
    public void openCart() throws IOException {
        SnappApplication.changeScene("user-cart.fxml");
    }
    public void openHome() throws IOException {
        SnappApplication.changeScene("user-home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switch (command)
        {
            case ALL -> {
                showRestaurantList(Restaurant.getRestaurantList());
            }

            case RECOMEND -> {
                try
                {
                    showRestaurantList(Restaurant.getRestaurantsOfType(User.getActiveUser().recommend()));
                }
                catch (Exception e)
                {
                    showRestaurantList(Restaurant.getRestaurantList());
                }
            }
            case SUPER -> {
                showRestaurantList(Restaurant.getRestaurantsOfType(FoodType.SUPER_MARKET));
            }
            case CAFE -> {
                showRestaurantList(Restaurant.getRestaurantsOfType(FoodType.CAFE));
            }
            case RESTAURANT -> {
                ArrayList<Restaurant> rs = Restaurant.getRestaurantsOfType(FoodType.FRIED);
                rs.addAll(Restaurant.getRestaurantsOfType(FoodType.IRANIAN));

                showRestaurantList(rs);
            }
            case NEAR -> {
                showRestaurantList(User.getActiveUser().kNearestRestaurants(5));
            }
        }
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
