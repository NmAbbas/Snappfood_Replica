package Snapp;

import java.sql.SQLException;

public class Savior {
    static void loading(){
        try {
            DB.init();
            Food.foodList = DB.loadfoods();
            Restaurant.restaurantList = DB.loadRestaurants();
            Account.AccountList = DB.loadAccounts();
            Order.orders = DB.loadOrders();
            Comment.commentList = DB.loadComments();
            Food.LinkBS(Food.foodList);
            Restaurant.LinkBS(Restaurant.restaurantList);
            Account.linkBS();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    static void saving(){
        try {
            DB.init();
            DB.clearEverything();
            DB.savefoods(Food.foodList);
            DB.saveRestaurants(Restaurant.restaurantList);
            DB.saveAccounts(Account.AccountList);
            DB.saveOrders(Order.orders);
            DB.saveCooments(Comment.commentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
