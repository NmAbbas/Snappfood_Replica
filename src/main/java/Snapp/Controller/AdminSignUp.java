package Snapp.Controller;

import Snapp.Account;
import Snapp.Admin;
import Snapp.SnapApplication;
import Snapp.User;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminSignUp {
    public TextField passwordField;
    public TextField usernameField;
    public Label usernameErrorLabel;
    public Label passwordErrorLabel;

    public void checkSignUp(){
        passwordErrorLabel.setOpacity(0.0);
        usernameErrorLabel.setOpacity(0.0);
        try
        {
            Admin.createAccount(usernameField.getText(), passwordField.getText());
            SnapApplication.changeScene("admin-login.fxml");
        }
        catch (Account.InvalidUsernameException | Account.UsernameTakenException e)
        {
            usernameErrorLabel.setOpacity(1.00);
        }
        catch (Account.InvalidPasswordException e)
        {
            passwordErrorLabel.setOpacity(1.00);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("admin-login.fxml");
    }

}
