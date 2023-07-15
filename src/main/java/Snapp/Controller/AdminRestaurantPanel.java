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

public class AdminRestaurantPanel implements Initializable
{

    public GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Admin.getActiveUser().getActiveRestaurant().getMenu().size() != 0) {
            gridPane.setPrefHeight(135*Admin.getActiveUser().getActiveRestaurant().getMenu().size());
            Button[] buttons = new Button[Admin.getActiveUser().getActiveRestaurant().getMenu().size()];
            //Food 0
            buttons[0] = new Button(Admin.getActiveUser().getActiveRestaurant().getMenu().get(0).getName());
            ImageView imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(Admin.getActiveUser().getActiveRestaurant().getMenu().get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
//            buttons[0].setGraphicTextGap(buttons[0].getWidth()-80-buttons[0].getText().length()-20);
            gridPane.add(buttons[0],0,0);
            buttons[0].setOnAction(e -> {
                Admin.getActiveUser().setActiveFood(Admin.getActiveUser().getActiveRestaurant().getMenu().get(0));
                try
                {
                    SnappApplication.changeScene("admin-food-view.fxml");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            });
            //Restaurant 1->
            for (int i = 1; i < Admin.getActiveUser().getActiveRestaurant().getMenu().size(); i++){
                buttons[i] = new Button(Admin.getActiveUser().getActiveRestaurant().getMenu().get(i).getName());
                imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(Admin.getActiveUser().getActiveRestaurant().getMenu().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
//                buttons[i].setGraphicTextGap(buttons[i].getWidth()-80-buttons[i].getText().length()-20);
                gridPane.addRow(i,buttons[i]);
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
}
