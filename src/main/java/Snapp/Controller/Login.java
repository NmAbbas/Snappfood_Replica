package Snapp.Controller;

import Snapp.SnappApplication;

import java.io.IOException;

public class Login {
    public void openUserLogin() throws IOException {
        SnappApplication.changeScene("user-login.fxml");
    }
    public void openAdminLogin() throws IOException {
        SnappApplication.changeScene("admin-login.fxml");
    }
}
