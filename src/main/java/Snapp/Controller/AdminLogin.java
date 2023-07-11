package Snapp.Controller;

import Snapp.Account;
import Snapp.SnapApplication;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLogin {
    public Label errorLabel;
    public TextField usernameField;
    public TextField passwordField;

    public void checkLogin() throws IOException {
        errorLabel.setRotate(0.0);
        try
        {
            Account account = Account.login(usernameField.getText(), passwordField.getText());
            if (account.isIsadmin())
            {
                Account.setActiveUser(account);
                SnapApplication.changeScene("admin-home.fxml");
            }
            else
                throw new Account.UsernameNotExists();
        }
        catch (Account.UsernameNotExists | Account.IncorrectPasswordException e)
        {
            errorLabel.setOpacity(1.00);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void openAdminSignUp() throws IOException {
        SnapApplication.changeScene("admin-sign-up.fxml");
    }
    public void openLogin() throws IOException {
        SnapApplication.changeScene("login.fxml");
    }

}
