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
}