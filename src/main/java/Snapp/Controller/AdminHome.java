package Snapp.Controller;

import Snapp.Admin;
import Snapp.Restaurant;
import Snapp.SnapApplication;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHome implements Initializable {
    public GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button addButton = new Button();
        addButton.setPrefWidth(480);
        addButton.setPrefHeight(100);
        ImageView imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream("/images/add.png")));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        addButton.setGraphic(imageView);
        gridPane.add(addButton,0,0);
        if (Admin.getActiveUser().getRestaurants().size() != 0){
            System.out.println(Admin.getActiveUser().getRestaurants().size());
            gridPane.setPrefHeight(135*Admin.getActiveUser().getRestaurants().size());
            Button[] buttons = new Button[Admin.getActiveUser().getRestaurants().size()];
            for (int i = 0; i < Admin.getActiveUser().getRestaurants().size(); i++){
                buttons[i] = new Button(Admin.getActiveUser().getRestaurants().get(i).getName());
                buttons[i].setPrefWidth(480);
                buttons[i].setPrefHeight(100);
                imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(Admin.getActiveUser().getRestaurants().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                gridPane.addRow(i,buttons[i]);
            }
            gridPane.setVgap(10);
        }
    }
}
