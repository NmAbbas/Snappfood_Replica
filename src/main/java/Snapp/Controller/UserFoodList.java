package Snapp.Controller;

import Snapp.SnapApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFoodList implements Initializable {

    public GridPane gridPane;
    public void openCart() throws IOException {
        SnapApplication.changeScene("user-cart.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (User.getActiveUser().getActiveRestaurant().getMenu().size() != 0) {
            gridPane.setPrefHeight(135*User.getActiveUser().getActiveRestaurant().getMenu().size());
            Button[] buttons = new Button[User.getActiveUser().getActiveRestaurant().getMenu().size()];
            //Food 0
            buttons[0] = new Button(User.getActiveUser().getActiveRestaurant().getMenu().get(0).getName());
            ImageView imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(User.getActiveUser().getActiveRestaurant().getMenu().get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
//            buttons[0].setGraphicTextGap(buttons[0].getWidth()-80-buttons[0].getText().length()-20);
            gridPane.add(buttons[0],0,0);
            buttons[0].setOnAction(e -> {
                User.getActiveUser().setActiveFood(User.getActiveUser().getActiveRestaurant().getMenu().get(0));
                try
                {
                    SnapApplication.changeScene("user-food-view.fxml");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            });
            //Restaurant 1->
            for (int i = 1; i < User.getActiveUser().getActiveRestaurant().getMenu().size(); i++){
                buttons[i] = new Button(User.getActiveUser().getActiveRestaurant().getMenu().get(i).getName());
                imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(User.getActiveUser().getActiveRestaurant().getMenu().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
//                buttons[i].setGraphicTextGap(buttons[i].getWidth()-80-buttons[i].getText().length()-20);
                gridPane.addRow(i,buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    User.getActiveUser().setActiveFood(User.getActiveUser().getActiveRestaurant().getMenu().get(k));
                    try
                    {
                        SnapApplication.changeScene("user-food-view.fxml");
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
