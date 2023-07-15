package Snapp.Controller;

import Snapp.Admin;
import Snapp.Comment;
import Snapp.SnappApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminFoodView implements Initializable {

    public ImageView foodImageView;
    public Label priceLabel;
    public Label cookingTimeLabel;
    public Button addToCartButton;
    public Label foodNameLabel;
    public GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodNameLabel.setText(Admin.getActiveUser().getActiveFood().getName());
        foodImageView.setImage(new Image(SnappApplication.class.getResourceAsStream(Admin.getActiveUser().getActiveFood().getImageURL())));
        cookingTimeLabel.setText(Admin.getActiveUser().getActiveFood().getCookingTime() / 1000 +" s");
        priceLabel.setText(String.valueOf(Admin.getActiveUser().getActiveFood().getPrice()));

        //add comments to food

        if (Admin.getActiveUser().getActiveFood().getComments().size() != 0) {
            gridPane.setPrefHeight(100*Admin.getActiveUser().getActiveFood().getComments().size());
            AnchorPane[] anchorPanes = new AnchorPane[Admin.getActiveUser().getActiveFood().getComments().size()];
            Label[] names = new Label[Admin.getActiveUser().getActiveFood().getComments().size()];
            Label[] comments = new Label[Admin.getActiveUser().getActiveFood().getComments().size()];
            Label[] replies = new Label[Admin.getActiveUser().getActiveFood().getComments().size()];
            TextField[] addComment = new TextField[Admin.getActiveUser().getActiveFood().getComments().size()];
            Button[] addCommentButton = new Button[Admin.getActiveUser().getActiveFood().getComments().size()];
            int row = 0;
            // adding Comments
            for (int i = 0; i < Admin.getActiveUser().getActiveFood().getComments().size(); i++)
                if (Admin.getActiveUser().getActiveFood().getComments().get(i).getUpper() == null) {
                    names[i] = new Label(Admin.getActiveUser().getActiveFood().getComments().get(i).getCommenter().getName());
                    anchorPanes[i] = new AnchorPane(names[i]);
                    AnchorPane.setTopAnchor(names[i], 15.0);
                    AnchorPane.setRightAnchor(names[i], 30.0);
                    comments[i] = new Label(Admin.getActiveUser().getActiveFood().getComments().get(i).getMessage());
                    anchorPanes[i].getChildren().add(comments[i]);
                    AnchorPane.setTopAnchor(comments[i], 37.0);
                    AnchorPane.setRightAnchor(comments[i], 40.0);
                    //adding replies
                    if (Admin.getActiveUser().getActiveFood().getComments().get(i).getReply() != null) {
                        replies[i] = new Label("->"+Admin.getActiveUser().getActiveFood().getComments().get(i).getReply().getMessage());
                        anchorPanes[i].getChildren().add(replies[i]);
                        AnchorPane.setTopAnchor(replies[i],100.0);
                        AnchorPane.setRightAnchor(replies[i],50.0);
                    }
                    else {// you can add a comment
                        addComment[i] = new TextField();
                        addComment[i].setPromptText("add your comment");
                        addComment[i].setMaxWidth(300);
                        addComment[i].setMaxHeight(40);
                        AnchorPane.setTopAnchor(addComment[i],100.0);
                        AnchorPane.setRightAnchor(addComment[i],50.0);
                        addCommentButton[i] = new Button("اضافه");
                        addCommentButton[i].setMaxWidth(120);
                        addCommentButton[i].setMaxHeight(30);
                        AnchorPane.setTopAnchor(addCommentButton[i],150.0);
                        AnchorPane.setLeftAnchor(addCommentButton[i],15.0);
                    }
                    gridPane.addRow(row, anchorPanes[i]);
                    row++;
                }
        }
    }
}
