package Snapp.Controller;

import Snapp.Admin;
import Snapp.FoodType;
import Snapp.Restaurant;
import Snapp.SnappApplication;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.File;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class AdminNewRestaurant {
    public Stage window;
    public TextField foodTypeField;
    public TextField nameField;
    public TextField addressField;
    public File imgFile = null;
    public ImageView imgPreview;

    public void addRestaurant() {
        try {
            Restaurant r =Restaurant.createRestaurant(nameField.getText(),FoodType.parse(foodTypeField.getText()), Admin.getActiveUser(),Integer.parseInt(addressField.getText()));
            if(imgFile != null)
            {
                myFile
            }
            else
                r.setImageURL("images/hamburger.png");
            SnappApplication.changeScene("admin-home.fxml");

        } catch (FoodType.UnknownType | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openAdminHome() throws IOException {
        SnappApplication.changeScene("admin-home.fxml");
    }

    public void openFileChooser()
    {
        imgFile = null;
        FileChooser fileChooser = new FileChooser();
        imgFile = fileChooser.showOpenDialog(window);
        imgPreview.setImage(new Image(imgFile.getAbsolutePath()));
    }
}
