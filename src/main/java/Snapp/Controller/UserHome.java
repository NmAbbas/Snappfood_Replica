package Snapp.Controller;

import Snapp.SnappApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHome implements Initializable {

    public GridPane gridPane;
    public Label CREDIT;
    public Button ADDCREDIT;

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
    public void addcredit() throws IOException {
        User.getActiveUser().setCurrency(User.getActiveUser().getCurrency()+10);
        SnappApplication.changeScene("user-home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CREDIT.setText(String.valueOf(User.getActiveUser().getCurrency()));
    }
}