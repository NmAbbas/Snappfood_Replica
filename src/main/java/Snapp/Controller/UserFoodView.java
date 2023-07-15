package Snapp.Controller;

import Snapp.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFoodView implements Initializable {

    public ImageView foodImageView;
    public Label priceLabel;
    public Label cookingTimeLabel;
    public Button addToCartButton;
    public Label foodNameLabel;
    public GridPane gridPane;
    public Label addedToCartLabel;

    public void addToCart() {
        if (!User.getActiveUser().getActiveFood().isActive())
            return;
        try
        {
            User.getActiveUser().getCart().addFood(User.getActiveUser().getActiveFood());
            addedToCartLabel.setOpacity(1.0);
//            System.out.println("food added to cart successfully!"); // change label
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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

        //add comments to food

        //add your comment button
        Button addYourCommentButton = new Button();
        ImageView imageView = new ImageView(new Image(SnapApplication.class.getResourceAsStream("/images/add.png")));
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        addYourCommentButton.setGraphic(imageView);
        addYourCommentButton.setText("نظر خود را اضافه کنید");
        gridPane.add(addYourCommentButton,0,0);
        addYourCommentButton.setOnAction(e -> {
            try
            {
                gridPane.getChildren().remove(addYourCommentButton);
                Label yourName = new Label(User.getActiveUser().getName());
                AnchorPane anchorPane = new AnchorPane(yourName);
                AnchorPane.setTopAnchor(yourName,20.0);
                AnchorPane.setRightAnchor(yourName,30.0);
                TextField textField = new TextField();
                textField.setPromptText("نظر خود را اضافه کنید");
                anchorPane.getChildren().add(textField);
                AnchorPane.setTopAnchor(textField,42.0);
                AnchorPane.setRightAnchor(textField,40.0);
                Button addButton = new Button("اضافه کردن");
                addButton.setMaxWidth(120);
                addButton.setMaxHeight(30);
                anchorPane.getChildren().add(addButton);
                AnchorPane.setTopAnchor(addButton,134.0);
                AnchorPane.setLeftAnchor(addButton,15.0);
                gridPane.add(anchorPane,0,0);
                addButton.setOnAction(event -> {
                    try{
                        Comment.createComment(textField.getText(),User.getActiveUser(),User.getActiveUser().getActiveRestaurant(),User.getActiveUser().getActiveFood());

                    } catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                });
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        if (User.getActiveUser().getActiveFood().getComments().size() != 0) {
            gridPane.setPrefHeight(135+ 60*(User.getActiveUser().getActiveFood().getComments().size()+1));
            AnchorPane[] anchorPanes = new AnchorPane[User.getActiveUser().getActiveFood().getComments().size()];
            Label[] names = new Label[User.getActiveUser().getActiveFood().getComments().size()];
            Label[] comments = new Label[User.getActiveUser().getActiveFood().getComments().size()];
            // adding Comments
            for (int i = 0; i < User.getActiveUser().getActiveFood().getComments().size(); i++) {
                names[i] = new Label(User.getActiveUser().getActiveFood().getComments().get(i).getCommenter().getName());
                anchorPanes[i] = new AnchorPane(names[i]);
                AnchorPane.setTopAnchor(names[i], 15.0);
                AnchorPane.setRightAnchor(names[i], 30.0);
                comments[i] = new Label(User.getActiveUser().getActiveFood().getComments().get(i).getMessage());
                anchorPanes[i].getChildren().add(comments[i]);
                AnchorPane.setTopAnchor(comments[i], 37.0);
                AnchorPane.setRightAnchor(comments[i], 40.0);
                gridPane.addRow(i+1, anchorPanes[i]);

            }
        }
    }
}
