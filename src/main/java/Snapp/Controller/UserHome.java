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
            RestaurantsList.Command = RestaurantsList.Command.RESTAURANT;
    }

    public void allCommand()
    {
        RestaurantsList.Command = RestaurantsList.Command.ALL;
    }

    public void cafeCommand()
    {
        RestaurantsList.Command = RestaurantsList.Command.CAFE;
    }

    public void superCommand()
    {
        RestaurantsList.Command = RestaurantsList.Command.SUPER;
    }

    public void nearCommand()
    {
        RestaurantsList.Command = RestaurantsList.Command.NEAR;
    }

    public void recommendCommand()
    {
        RestaurantsList.Command = RestaurantsList.Command.RECOMEND;
    }
}