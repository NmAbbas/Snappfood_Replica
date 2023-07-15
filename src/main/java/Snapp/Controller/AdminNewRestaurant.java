package Snapp.Controller;

import Snapp.Admin;
import Snapp.FoodType;
import Snapp.Restaurant;
import Snapp.SnappApplication;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AdminNewRestaurant {
    public TextField foodTypeField;
    public TextField nameField;
    public TextField addressField;

    public void addRestaurant() {
        try {
            Restaurant.createRestaurant(nameField.getText(),FoodType.parse(foodTypeField.getText()), Admin.getActiveUser(),Integer.parseInt(addressField.getText()));
            SnappApplication.changeScene("admin-home.fxml");

        } catch (FoodType.UnknownType | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openAdminHome() throws IOException {
        SnappApplication.changeScene("admin-home.fxml");
    }
}
