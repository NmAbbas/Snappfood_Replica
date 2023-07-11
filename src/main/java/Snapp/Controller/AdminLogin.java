package Snapp.Controller;

import Snapp.SnapApplication;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLogin {
    public Label errorLabel;
    public TextField usernameField;
    public TextField passwordField;

    public void checkLogin() throws IOException {

        SnapApplication.changeScene("admin-home.fxml");
    }
    public void openAdminSignUp() throws IOException {
        SnapApplication.changeScene("admin-sign-up.fxml");
    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("login.fxml");
    }

}
