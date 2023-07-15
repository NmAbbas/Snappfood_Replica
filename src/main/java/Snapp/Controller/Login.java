package Snapp.Controller;

import Snapp.Account;
import Snapp.SnapApplication;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public void openUserLogin() throws IOException {
        SnappApplication.changeScene("user-login.fxml");
    }
    public void openAdminLogin() throws IOException {
        SnappApplication.changeScene("admin-login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
