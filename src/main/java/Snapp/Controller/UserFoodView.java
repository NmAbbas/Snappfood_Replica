package Snapp.Controller;

import Snapp.Restaurant;
import Snapp.SnapApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFoodView implements Initializable {

    public ImageView foodImageView;
    public Label priceLabel;
    public Label cookingTimeLabel;
    public Button addToCartButton;
    public Label foodNameLabel;

    public void addToCart() {
//        if ()
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodNameLabel.setText(User.getActiveUser().getActiveFood().getName());
        foodImageView.setImage(new Image(SnapApplication.class.getResourceAsStream(User.getActiveUser().getActiveFood().getImageURL())));
        cookingTimeLabel.setText(User.getActiveUser().getActiveFood().getCookingTime() / 1000 +" s");
        priceLabel.setText(String.valueOf(User.getActiveUser().getActiveFood().getPrice()));
        if (!User.getActiveUser().getActiveFood().isActive()){
            addToCartButton.getStyleClass().add("food-not-active");
            addToCartButton.setText("این غذا موجود نیست");
        }
    }
}
