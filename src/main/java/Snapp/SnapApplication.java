package Snapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SnapApplication extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws IOException {// example creation:
        try
        {
            User u1 = User.createUser("sandy", "P@ss12345678");
            User u2 = User.createUser("mylegfish", "P@ss12345678");
            User u3 = User.createUser("patrick", "P@ss12345678");
            u3.setLocation(3);

            Admin a1 = Admin.createAccount("Mr.Krabs", "P@ss12345678");
            Admin a2 = Admin.createAccount("plankton", "P@ss12345678");

            Delivery d1 = Delivery.createAccount("spongebob", "P@ss12345678");
            d1.setLocation(2);

            Restaurant r1 = Restaurant.createRestaurant("زستوران خرچنگی", FoodType.FRIED, a1, 1);
            Restaurant r11 = Restaurant.createRestaurant("رستوران خرچنگی۲", FoodType.FRIED, a1, 2);
            Restaurant r2 = Restaurant.createRestaurant("چام باکت", FoodType.FRIED, a2, 1);

            Food f1 = Food.createFood("Krabby Patty", 10, FoodType.FRIED, 10000, r1);
            u1.selectRestaurant(1);

            Comment.createComment("it tastes like shit", u1, null, f1);
            Comment.createComment("i like it", u2, null, f1);
            Comment.createComment("uhhhhhhhhhhhh!", u3, null, f1);

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        ////////
        //////
        /////
        ///
        //

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