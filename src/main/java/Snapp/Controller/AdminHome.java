package Snapp.Controller;

import Snapp.Admin;
import Snapp.SnappApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHome implements Initializable {
    public GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView imageView;
        if (Admin.getActiveUser().getRestaurants().size() != 0){
            gridPane.setPrefHeight(135*(Admin.getActiveUser().getRestaurants().size()+1));
            Button[] buttons = new Button[Admin.getActiveUser().getRestaurants().size()];
            for (int i = 0; i < Admin.getActiveUser().getRestaurants().size(); i++){
                buttons[i] = new Button(Admin.getActiveUser().getRestaurants().get(i).getName());
                imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(Admin.getActiveUser().getRestaurants().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                gridPane.addRow(i+1,buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    Admin.getActiveUser().setActiveRestaurant(Admin.getActiveUser().getRestaurants().get(k));
                    try
                    {
                        SnappApplication.changeScene("admin-restaurant-panel.fxml");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                });
            }
            gridPane.setVgap(10);
        }
    }

    public void openAdminNewRestaurant() throws IOException {
        SnappApplication.changeScene("admin-new-restaurant.fxml");
    }
}
