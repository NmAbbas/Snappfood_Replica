package Snapp.Controller;

import Snapp.SnappApplication;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class UserHome {

    public GridPane gridPane;

    public void openRestaurants() throws IOException {
        SnappApplication.changeScene("restaurants-list.fxml");
    }
    public void openLogin() throws IOException {
        SnappApplication.changeScene("login.fxml");
    }
    public void openCart() throws IOException {
        SnappApplication.changeScene("user-cart.fxml");
    }
    public void restaurantCommand()
    {
            RestaurantsList.command = RestaurantsList.Command.RESTAURANT;
    }

    public void allCommand()
    {
        RestaurantsList.command = RestaurantsList.Command.ALL;
    }

    public void cafeCommand()
    {
        RestaurantsList.command = RestaurantsList.Command.CAFE;
    }

    public void superCommand()
    {
        RestaurantsList.command = RestaurantsList.Command.SUPER;
    }

    public void nearCommand()
    {
        RestaurantsList.command = RestaurantsList.Command.NEAR;
    }

    public void recommendCommand()
    {
        RestaurantsList.command = RestaurantsList.Command.RECOMEND;
    }
}