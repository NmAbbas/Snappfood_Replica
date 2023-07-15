package Snapp.Controller;

import Snapp.Admin;
import Snapp.SnappApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminRestaurantPanel implements Initializable
{

    public GridPane gridPane;
    public Label restaurantNameLabel;
    public Label foodTypeLabel;
    public ImageView restaurantImage;

    public void removeRestaurant() {
        try {
            Admin.getActiveUser().getRestaurants().remove(Admin.getActiveUser().getActiveRestaurant());
            SnappApplication.changeScene("admin-home.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        restaurantNameLabel.setText(Admin.getActiveUser().getActiveRestaurant().getName());
        foodTypeLabel.setText(Admin.getActiveUser().getActiveRestaurant().getFoodtype().toString());
        restaurantImage.setImage(new Image(SnappApplication.class.getResourceAsStream(Admin.getActiveUser().getActiveRestaurant().getImageURL())));
        if (Admin.getActiveUser().getActiveRestaurant().getMenu().size() != 0){
            ImageView imageView;
            gridPane.setPrefHeight(135*(Admin.getActiveUser().getActiveRestaurant().getMenu().size()+1));
            Button[] buttons = new Button[Admin.getActiveUser().getActiveRestaurant().getMenu().size()];
            for (int i = 0; i < Admin.getActiveUser().getActiveRestaurant().getMenu().size(); i++){
                buttons[i] = new Button(Admin.getActiveUser().getActiveRestaurant().getMenu().get(i).getName());
                imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(Admin.getActiveUser().getActiveRestaurant().getMenu().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                gridPane.addRow(i+1,buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    Admin.getActiveUser().setActiveFood(Admin.getActiveUser().getActiveRestaurant().getMenu().get(k));
                    try
                    {
                        SnappApplication.changeScene("admin-food-view.fxml");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                });
            }
            gridPane.setVgap(10);
        }
    }

    public void openHome() throws IOException {
        SnappApplication.changeScene("admin-home.fxml");
    }
    public void openAdminNewFood() throws IOException {
        SnappApplication.changeScene("admin-new-food.fxml");
    }
}
