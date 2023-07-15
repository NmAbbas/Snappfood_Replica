package Snapp.Controller;

import Snapp.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderList
{
/*


    public static RestaurantsList.Command command = RestaurantsList.Command.ALL;
    public GridPane gridPane;
    public void openCart() throws IOException
    {
        SnappApplication.changeScene("user-cart.fxml");
    }
    public void openHome() throws IOException {
        SnappApplication.changeScene("user-home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switch (command)
        {
            case ALL -> {
                showRestaurantList(Restaurant.getRestaurantList());
            }

            case RECOMEND -> {
                try
                {
                    showRestaurantList(Restaurant.getRestaurantsOfType(User.getActiveUser().recommend()));
                }
                catch (Exception e)
                {
                    showRestaurantList(Restaurant.getRestaurantList());
                }
            }
            case SUPER -> {
                showRestaurantList(Restaurant.getRestaurantsOfType(FoodType.SUPER_MARKET));
            }
            case CAFE -> {
                showRestaurantList(Restaurant.getRestaurantsOfType(FoodType.CAFE));
            }
            case RESTAURANT -> {
                ArrayList<Restaurant> rs = Restaurant.getRestaurantsOfType(FoodType.FRIED);
                rs.addAll(Restaurant.getRestaurantsOfType(FoodType.IRANIAN));

                showRestaurantList(rs);
            }
            case NEAR -> {
                showRestaurantList(User.getActiveUser().kNearestRestaurants(5));
            }
        }

    }

    public void showOrderList(ArrayList<Order> orders)
    {
        String ftype = "";
        for (FoodType f: User.getActiveUser().getActiveRestaurant().getFoodtype())
        {
            ftype += f.toString() + ", ";
        }
        ftype = ftype.substring(0, ftype.length() - 2);
        foodTypeLabel.setText(ftype);
        restaurantNameLabel.setText(User.getActiveUser().getActiveRestaurant().getName());

        if (orders.size() != 0) {
            gridPane.setPrefHeight(135*orders.size());
            Button[] buttons = new Button[orders.size()];
            //Restaurant 0
            buttons[0] = new Button(orders.get(0).getName());
            ImageView imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream(orders.get(0).getImageURL())));
            imageView.setFitWidth(80);
            imageView.setFitWidth(80);
            buttons[0].setGraphic(imageView);
            gridPane.add(buttons[0],0,0);
            buttons[0].setOnAction(e -> {
                User.getActiveUser().setActiveRestaurant(orders.get(0));
                try
                {
                    User.getActiveUser().setCart(new Cart(User.getActiveUser().getActiveRestaurant(), User.getActiveUser()));
                    SnappApplication.changeScene("user-food-list.fxml");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            });
            //Restaurant 1->
            for (int i = 1; i < orders.size(); i++){
                buttons[i] = new Button(orders.get(i).getName());
                imageView = new ImageView(new Image(SnappApplication.class.getResourceAsStream("images\order")));
                imageView.setFitWidth(80);
                imageView.setFitWidth(80);
                buttons[i].setGraphic(imageView);
                gridPane.addRow(i,buttons[i]);
                int k = i;
                buttons[i].setOnAction(e -> {
                    User.getActiveUser().;
                    try
                    {
                        SnappApplication.changeScene("user-food-list.fxml");
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                });
            }
            gridPane.setVgap(10);
        }
    }*/
}
