package Snapp.Controller;

import Snapp.SnapApplication;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserLogin {
    public Label errorLabel;
    public TextField usernameField;
    public TextField passwordField;
    public void checkLogin() throws IOException {

        SnapApplication.changeScene("user-home.fxml");
    }
    public void openUserSignUp() throws IOException {
        SnapApplication.changeScene("user-sign-up.fxml");
    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("login.fxml");
    }
}
