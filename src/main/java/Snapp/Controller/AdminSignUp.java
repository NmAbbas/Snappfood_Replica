package Snapp.Controller;

import Snapp.Account;
import Snapp.Admin;
import Snapp.SnappApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminSignUp implements Initializable {
    public TextField passwordField;
    public TextField usernameField;
    public Label usernameErrorLabel;
    public Label passwordErrorLabel;

    public ImageView cap;
    public TextField capField;
    String captchacontent;
    public void checkSignUp(){
        passwordErrorLabel.setOpacity(0.0);
        usernameErrorLabel.setOpacity(0.0);
        try
        {
            Admin.createAccount(usernameField.getText(), passwordField.getText());
            SnappApplication.changeScene("admin-login.fxml");
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
        SnappApplication.changeScene("admin-login.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] maw = Cap.getrandomacp();
        System.out.println(getClass().getResource("/images/" + maw[0]).toString());
        Image i = new Image(getClass().getResource("/images/" + maw[0]).toString());
        captchacontent = maw[1];
        cap.setImage(i);
    }
}
