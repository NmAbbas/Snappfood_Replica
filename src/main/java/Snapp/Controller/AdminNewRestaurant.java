package Snapp.Controller;

import Snapp.Admin;
import Snapp.FoodType;
import Snapp.Restaurant;
import Snapp.SnapApplication;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AdminNewRestaurant {
    public TextField foodTypeField;
    public TextField nameField;
    public TextField addressField;

    public void addRestaurant() {
        try {
            Restaurant.createRestaurant(nameField.getText(),FoodType.parse(foodTypeField.getText()), Admin.getActiveUser(),Integer.parseInt(addressField.getText()));
            SnapApplication.changeScene("admin-home.fxml");

        } catch (FoodType.UnknownType e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openAdminHome() throws IOException {
        SnapApplication.changeScene("admin-home.fxml");
    }
}
