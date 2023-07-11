package Snapp;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    static private PreparedStatement preparedStatement = null;
    public static void main(String[] args) throws SQLException {
        init();
//        addaccount("abbas","mewmew","1234",true,2000);
//        System.out.println(usertaken("mae"));
        ArrayList<Integer> path = new ArrayList<>();
    }
    public static void init()
    {

        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db",
                    "root", "snappfood");
            statement = connection.createStatement();
//            preparedStatement = connection.prepareStatement("INSERT INTO db.users (name, username, password, isadmin,credit)\n" +
//                    "VALUES (?, ?, ?, ?,?);");
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, username);
//            preparedStatement.setString(3, pass);
//            preparedStatement.setBoolean(4, isadmin);
//            preparedStatement.setLong(5, credit);
//            preparedStatement.executeUpdate();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }
    static void savefoods(ArrayList<Food> foods) throws SQLException {
        for(Food f:foods){
            preparedStatement = connection.prepareStatement("INSERT INTO db.food (id, name, restaurantid,type,price,discount)\n" +
                    "VALUES (?, ?, ?,?,?,?);");
            preparedStatement.setInt(1, f.getId());
            preparedStatement.setString(2, f.getName());
            preparedStatement.setInt(3, f.getOwner().getId());
            preparedStatement.setString(4, f.getFoodType().toString());
            preparedStatement.setDouble(5, f.getPrice());
            preparedStatement.setDouble(6, f.getDiscount());
            preparedStatement.executeUpdate();
        }
    }
    static ArrayList<Food> loadfoods() throws SQLException, Food.InvalidPriceException, FoodType.UnknownType {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select (id, name, restaurantid,type,price,discount,cookingtime) from db.food ;");
        ArrayList<Food> foods = new ArrayList<>();
        while (resultSet.next()){
            Food f = new Food(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getLong("price"),FoodType.parse(resultSet.getString("type")),resultSet.getLong("cookingtime"),null);
            f.ownerid=resultSet.getInt("restaurantid");
            foods.add(f);
        }
        return foods;
    }
    static void saveRestaurants(ArrayList<Restaurant> restaurants) throws SQLException {
        for(Restaurant f:restaurants){
            preparedStatement = connection.prepareStatement("INSERT INTO db.restaurants (id, name, adminid,type)\n" +
                    "VALUES (?, ?, ?,?,?,?);");
            preparedStatement.setInt(1, f.getId());
            preparedStatement.setString(2, f.getName());
            preparedStatement.setInt(3, f.getOwner().id);
            preparedStatement.setString(4, f.getFoodtype().toString());
            preparedStatement.executeUpdate();
        }
    }
    static ArrayList<Restaurant> loadRestaurants() throws SQLException, Food.InvalidPriceException, FoodType.UnknownType, Restaurant.FoodTypeUnchangable {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select (id, name, restaurantid,type,price,discount,cookingtime) from db.food ;");
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        while (resultSet.next()){
            String type = resultSet.getString("type");
            ArrayList<FoodType> foodtype = new ArrayList<>();
            for(String s:type.substring(1, type.length() - 1).split(", ")){
                foodtype.add(FoodType.parse(s));
            }
            Restaurant f = new Restaurant(resultSet.getString("name"),resultSet.getInt("id"),foodtype.get(0) ,Admin.getAdminByID(resultSet.getInt("restaurantid")),resultSet.getInt("loc"));
            f.addFoodtype(foodtype);
            f.ownerid=resultSet.getInt("restaurantid");
            restaurants.add(f);
        }
        return restaurants;
    }

//    void saveusers() throws SQLException {
//        preparedStatement = connection.prepareStatement("delete from users ;");
//        for (Account u :Account.AccountList){
//            addaccount(u.name,u.pass,u.isadmin,u.credit);
//        }
//    }
//    void saveusers() throws SQLException {
//        preparedStatement = connection.prepareStatement("delete from users ;");
//        for (Account u :Account.AccountList){
//            addaccount(u.name,u.pass,u.isadmin,u.credit);
//        }
//    }
//    static boolean usertaken(String username) throws SQLException {
//        preparedStatement = connection.prepareStatement("select username from users where username=?;");
//        preparedStatement.setString(1, username);
//        resultSet = preparedStatement.executeQuery();
//        return resultSet.next();
//    }
//    static void updateUser(Account u){
//        preparedStatement = connection.prepareStatement("update db.users  set(name=?, username=?, password=?, isadmin=?,credit=?)\n" +
//                " where (username = ?);");
//        preparedStatement.setString(1, u.name);
//        preparedStatement.setString(2, u.);
//        preparedStatement.setString(3, pass);
//        preparedStatement.setBoolean(4, isadmin);
//        preparedStatement.setLong(5, credit);
//        preparedStatement.executeUpdate();
//    }

} // class ends