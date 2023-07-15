package Snapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class    SnappApplication extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws IOException {// example creation:
        try
        {
            System.out.println(SnappApplication.class.getResource("").toString());
//            Savior.loading();
            User u1 = User.createUser("sandy", "P@ss12345678");
            User u2 = User.createUser("mylegfish", "P@ss12345678");
            User u3 = User.createUser("patrick", "P@ss12345678");
            u3.setLocation(3);
            DiscountCard.giveDiscountCardToUser(u3, 450);
            u1.setQuestion("how are you?");
            u1.setAnswer("GOOD");


            Admin a1 = Admin.createAccount("Mr.Krabs", "P@ss12345678");
            Admin a2 = Admin.createAccount("plankton", "P@ss12345678");
            a2.setQuestion("how are you?");
            a2.setAnswer("GOOD");

            Delivery d1 = Delivery.createAccount("spongebob", "P@ss12345678");
            d1.setLocation(2);

            Restaurant r1 = Restaurant.createRestaurant("رستوران خرچنگی", FoodType.FRIED, a1, 1);
            Restaurant r11 = Restaurant.createRestaurant("رستوران خرچنگی۲", FoodType.FRIED, a1, 2);
            Restaurant r2 = Restaurant.createRestaurant("چام باکت", FoodType.FRIED, a2, 1);

            Food f1 = Food.createFood("همبرگر خرچنگی", 10, FoodType.FRIED, 10000, r1);
            //f1.deactivate();
            u1.selectRestaurant(1);

            Comment.createComment("متبسیمنتبسمنبتسمبتسیمبنستیمست بنسدمستبمسی بتمد یب ب ی سی س س س بس س    یسیسسیبیسبسبسیبسبسسبسیبسیبسیبسشیبسیبس    زه گوه میده", u1, null, f1);
            Comment.createComment("دوست داشتم", u2, null, f1);
            Comment.createComment("اهههههه!", u3, null, f1);



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
        Scene scene = new Scene(FXMLLoader.load(SnappApplication.class.getResource("login.fxml")));
        window.setTitle("Snapp!");
        window.getIcons().add(new Image(SnappApplication.class.getResourceAsStream("/images/snappFoodIcon.png")));
        window.setScene(scene);
        window.show();
    }
    public static void changeScene(String FXMLName) throws IOException {
        SnappApplication.window.setScene(new Scene(FXMLLoader.load(SnappApplication.class.getResource(FXMLName))));
        window.show();
    }

    public static void main(String[] args) {
        launch();
//        Savior.saving();
    }
}