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

        //your comment
        /*Comment thisUserComment = Comment.checkIfHasCommentedInList(Admin.getActiveUser().getActiveFood().getComments(),Admin.getActiveUser().getId());
        if (thisUserComment == null){
            Button addYourCommentButton = new Button();
            ImageView imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream("/images/add.png")));
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
                            Comment.createComment(textField.getText(),User.getActiveUser(),null,Admin.getActiveUser().getActiveFood());
                            Label yourCommentName = new Label(User.getActiveUser().getName());
                            AnchorPane commentAnchorPane = new AnchorPane(yourCommentName);
                            AnchorPane.setTopAnchor(yourCommentName, 15.0);
                            AnchorPane.setRightAnchor(yourCommentName, 30.0);
                            Label yourComment = new Label(thisUserComment.getMessage());
                            commentAnchorPane.getChildren().add(yourComment);
                            AnchorPane.setTopAnchor(yourComment, 37.0);
                            AnchorPane.setRightAnchor(yourComment, 40.0);
                            gridPane.addRow(0, commentAnchorPane);

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
        }
        else {
            Label yourCommentName = new Label(User.getActiveUser().getName());
            AnchorPane anchorPane = new AnchorPane(yourCommentName);
            AnchorPane.setTopAnchor(yourCommentName, 15.0);
            AnchorPane.setRightAnchor(yourCommentName, 30.0);
            Label yourComment = new Label(thisUserComment.getMessage());
            anchorPane.getChildren().add(yourComment);
            AnchorPane.setTopAnchor(yourComment, 37.0);
            AnchorPane.setRightAnchor(yourComment, 40.0);
            gridPane.addRow(0, anchorPane);
        }*/
        if (Admin.getActiveUser().getActiveFood().getComments().size() != 0) {
            gridPane.setPrefHeight(135+ 60*(Admin.getActiveUser().getActiveFood().getComments().size()+1));
            AnchorPane[] anchorPanes = new AnchorPane[Admin.getActiveUser().getActiveFood().getComments().size()];
            Label[] names = new Label[Admin.getActiveUser().getActiveFood().getComments().size()];
            Label[] comments = new Label[Admin.getActiveUser().getActiveFood().getComments().size()];
            Label[] replies = new Label[Admin.getActiveUser().getActiveFood().getComments().size()];
            int row = 0;
            // adding Comments
            for (int i = 0; i < User.getActiveUser().getActiveFood().getComments().size() && i != User.getActiveUser().getActiveFood().getComments().indexOf(thisUserComment); i++)
                if (User.getActiveUser().getActiveFood().getComments().get(i).getUpper() == null) {
                    names[i] = new Label(User.getActiveUser().getActiveFood().getComments().get(i).getCommenter().getName());
                    anchorPanes[i] = new AnchorPane(names[i]);
                    AnchorPane.setTopAnchor(names[i], 15.0);
                    AnchorPane.setRightAnchor(names[i], 30.0);
                    comments[i] = new Label(User.getActiveUser().getActiveFood().getComments().get(i).getMessage());
                    anchorPanes[i].getChildren().add(comments[i]);
                    AnchorPane.setTopAnchor(comments[i], 37.0);
                    AnchorPane.setRightAnchor(comments[i], 40.0);
                    //adding replies
                    if (User.getActiveUser().getActiveFood().getComments().get(i).getReply() != null) {
                        replies[i] = new Label("->"+User.getActiveUser().getActiveFood().getComments().get(i).getReply().getMessage());
                        anchorPanes[i].getChildren().add(replies[i]);
                        AnchorPane.setTopAnchor(replies[i],100.0);
                        AnchorPane.setRightAnchor(replies[i],50.0);
                    }
                    gridPane.addRow(row, anchorPanes[i]);
                    row++;
                }
        }
    }
}
