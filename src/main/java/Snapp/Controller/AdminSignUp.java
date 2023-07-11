package Snapp.Controller;

import Snapp.SnapApplication;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminSignUp {
    public TextField passwordField;
    public TextField usernameField;
    public Label usernameErrorLabel;
    public Label passwordErrorLabel;

    public void checkSignUp(){

    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("admin-login.fxml");
    }

}
