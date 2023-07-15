package Snapp.Controller;

import Snapp.SnapApplication;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class UserHome {

    public GridPane gridPane;

    public void openRestaurants() throws IOException {
        SnapApplication.changeScene("restaurants-list.fxml");
    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("login.fxml");
    }
}