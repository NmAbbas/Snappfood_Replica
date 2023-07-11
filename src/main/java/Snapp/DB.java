package Snapp;

/*import java.sql.*;
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
        preparedStatement = connection.prepareStatement("INSERT INTO db.food (id, name, restaurantid,credit)\n" +
                "VALUES (?, ?, ?,?);");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, pass);
        preparedStatement.setBoolean(3, isadmin);
        preparedStatement.setLong(4, credit);
        preparedStatement.executeUpdate();
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

} // class ends*/