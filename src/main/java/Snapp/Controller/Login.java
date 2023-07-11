package Snapp.Controller;

import Snapp.SnapApplication;

import java.io.IOException;

public class Login {
    public void openUserLogin() throws IOException {
        SnapApplication.changeScene("user-login.fxml");
    }
    public void openAdminLogin() throws IOException {
        SnapApplication.changeScene("admin-login.fxml");
    }
}
