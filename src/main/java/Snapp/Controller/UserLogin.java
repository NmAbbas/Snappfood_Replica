package Snapp.Controller;

import Snapp.Account;
import Snapp.SnapApplication;
import Snapp.User;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserLogin {
    public Label errorLabel;
    public TextField usernameField;
    public TextField passwordField;
    public void checkLogin() throws IOException {
        errorLabel.setOpacity(0.0);
        try
        {
            Account.setActiveUser(Account.login(usernameField.getText(), passwordField.getText()));
            SnapApplication.changeScene("user-home.fxml");
        }
        catch (Account.UsernameNotExists | Account.IncorrectPasswordException e)
        {
            errorLabel.setOpacity(1.00);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void openUserSignUp() throws IOException {
        SnapApplication.changeScene("user-sign-up.fxml");
    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("login.fxml");
    }
}
