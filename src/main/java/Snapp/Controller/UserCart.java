package Snapp.Controller;

import Snapp.DiscountCard;
import Snapp.SnapApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCart implements Initializable {
    public Label totalCostLabel;

    public GridPane gridPane;

    public void pay()
    {

        try
        {
            User.getActiveUser().getCart().buy();
            //TODO "order confirmed succcessfully"
            int disc = DiscountCard.giveDiscountCardToUser(User.getActiveUser(), User.getActiveUser().getCart().price());
            if (disc > 0)
            {
                //TODO "you have a new discount card"
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void reset() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalCostLabel.setText(String.valueOf(User.getActiveUser().getCart().price()));
        if (User.getActiveUser().getCart().getFoods().size() != 0) {
            gridPane.setPrefHeight(135 * User.getActiveUser().getCart().getFoods().size());
            Button[] buttons = new Button[User.getActiveUser().getCart().getFoods().size()];
            //Food 0
            buttons[0] = new Button(User.getActiveUser().getCart().getFoods().get(0).getName()+"\n"+User.getActiveUser().getCart().getFoods().get(0).getPrice());
            ImageView imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(User.getActiveUser().getCart().getFoods().get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
            gridPane.add(buttons[0], 0, 0);
            buttons[0].setOnAction(e -> {
                User.getActiveUser().getCart().removeFood(User.getActiveUser().getCart().getFoods().get(0));
                gridPane.getChildren().remove(0,0);
            });
            //Food 1->
            for (int i = 1; i < User.getActiveUser().getCart().getFoods().size(); i++) {
                buttons[i] = new Button(User.getActiveUser().getCart().getFoods().get(i).getName()+"\n"+User.getActiveUser().getCart().getFoods().get(i).getPrice());
                imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream(User.getActiveUser().getCart().getFoods().get(i).getImageURL())));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                gridPane.addRow(i, buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    User.getActiveUser().getCart().removeFood(User.getActiveUser().getCart().getFoods().get(k));
                    gridPane.getChildren().remove(k,0);
                });
            }
            gridPane.setVgap(10);

        }
    }
}
