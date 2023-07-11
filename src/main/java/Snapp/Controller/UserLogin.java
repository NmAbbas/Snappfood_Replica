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
        try
        {
            Account.setActiveUser(Account.login(usernameField.getText(), passwordField.getText()));
        }
        catch (Account.UsernameNotExists e)
        {
            errorLabel.setOpacity(1.00);
        }
        catch (Account.IncorrectPasswordException e)
        {
            errorLabel.setOpacity(1.00);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        SnapApplication.changeScene("user-home.fxml");

    }
    public void openUserSignUp() throws IOException {
        SnapApplication.changeScene("user-sign-up.fxml");
    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("login.fxml");
    }
}
