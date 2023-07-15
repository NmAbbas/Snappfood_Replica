package Snapp.Controller;

import Snapp.Account;
import Snapp.SnapApplication;
import Snapp.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSignUp implements Initializable {
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
            if(captchacontent.equals(capField.getText())) User.createUser(usernameField.getText(), passwordField.getText());

            SnapApplication.changeScene("user-login.fxml");
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
        SnapApplication.changeScene("user-login.fxml");
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
