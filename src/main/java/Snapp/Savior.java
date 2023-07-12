package Snapp;

import java.sql.SQLException;
import java.util.ArrayList;

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
            DB.loadCarts();
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
            ArrayList<Cart> carts = new ArrayList<>();
            for(Account a:Account.AccountList){
                if(!a.isDelivery&&!a.isadmin){
                    carts.add(((User)a).getCart());
                }
            }
            DB.saveCarts(carts);
            DB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
