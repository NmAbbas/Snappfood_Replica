package Snapp.Controller;

import Snapp.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminNewFood {
    public Stage window;
    public TextField foodTypeField;
    public TextField nameField;
    public File imgFile = null;
    public ImageView imgPreview;
    public TextField foodPriceField;
    public TextField cookingTimeField;

    public void addFood() {
        try {
            Food f = Food.createFood(nameField.getText(),Double.parseDouble(foodPriceField.getText()),FoodType.parse(foodTypeField.getText()),Long.parseLong(cookingTimeField.getText()),Admin.getActiveUser().getActiveRestaurant());
            if(imgFile != null)
            {
                f.setImageURL("/images/"+imgFile.getName());
            }
            else
                f.setImageURL("/images/hamburger.png");
            SnappApplication.changeScene("admin-restaurant-panel.fxml");

        } catch (FoodType.UnknownType | Food.InvalidPriceException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openRestaurantPanel() throws IOException {
        SnappApplication.changeScene("admin-restaurant-panel.fxml");
    }

    public void openFileChooser()
    {
        imgFile = null;
        FileChooser fileChooser = new FileChooser();
        imgFile = fileChooser.showOpenDialog(window);
        imgPreview.setImage(new Image(imgFile.getAbsolutePath()));
    }
}
