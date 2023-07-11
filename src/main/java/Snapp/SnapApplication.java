package Snapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SnapApplication extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        Scene scene = new Scene(FXMLLoader.load(SnapApplication.class.getResource("login.fxml")));
        window.setTitle("Snapp!");
        window.getIcons().add(new Image(SnapApplication.class.getResourceAsStream("/images/snappFoodIcon.png")));
        window.setScene(scene);
        window.show();
    }
    public static void changeScene(String FXMLName) throws IOException {
        SnapApplication.window.setScene(new Scene(FXMLLoader.load(SnapApplication.class.getResource(FXMLName))));
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}