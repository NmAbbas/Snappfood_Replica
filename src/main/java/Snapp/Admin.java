package Snapp;

import java.util.ArrayList;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;

public class Admin extends Account {

    public Admin() {

    }

    public static Admin createAccount(String name, String pass)
            throws Account.InvalidUsernameException, Account.InvalidPasswordException, NoSuchAlgorithmException, InvalidKeySpecException, UsernameTakenException {
        try
        {
            findAccount(name);
        }
        catch (Exception e)
        {
            Admin admin = new Admin(name, pass, nextID++);
            admin.setadmin();
            AccountList.add(admin);
            return admin;
        }
        throw new UsernameTakenException();
    }

    public static Admin getActiveUser()
    {
        return (Admin) activeUser;
    }
    static Admin getAdminByID(int id){
        for(Account a:Account.AccountList){
            if(a.id==id && a.isadmin){
                return (Admin)a;
            }
        }
        return null;
    }


    Admin(String name, String pass, int id)
            throws Account.InvalidUsernameException, Account.InvalidPasswordException, NoSuchAlgorithmException, InvalidKeySpecException {
        super(name, pass, id);
    }


    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    private Restaurant activeRestaurant = null;

    public Food getActiveFood() {
        return activeFood;
    }

    public Order getActiveOrder() {
        return activeOrder;
    }

    private Food activeFood = null;
    private Order activeOrder= null;
    public void addRestaurant(String name,FoodType foodType,int loc)
    {
        Snapp.Restaurant r = Restaurant.createRestaurant(name,foodType,this,loc);
        restaurants.add(r);
    }
    public void printRestaurants()
    {
        if(restaurants.isEmpty())
        {
            System.out.println("you own no restaurant!");
            return;
        }
        Restaurant.sort(restaurants);

        System.out.println("your restaurant(s):");
        System.out.println("ID\tNAME");
        for (Restaurant r : restaurants)
            System.out.println(r.getId() + "\t" + r);
    }

    int selectRestaurant(int id) // return 1 on success, 0 if doesn,t exist
    {
        for (Restaurant r : restaurants) {
            if (r.getId() == id) {
                activeRestaurant = r;
                return 1;
            }
        }

        return 0;
    }

    Restaurant getActiveRestaurant() {
        return activeRestaurant;
    }

    void addRestaurant(Restaurant r)
    {
        if(!restaurants.contains(r))
            restaurants.add(r);
    }
    void addFood(){

//		selected.addFood();
    }
    void editFoodtype(ArrayList<FoodType> f) throws Restaurant.FoodTypeUnchangable {
        activeRestaurant.editFoodtype(f);
    }
    void addFoodtype(ArrayList<FoodType> f) throws Restaurant.FoodTypeUnchangable {
        activeRestaurant.addFoodtype(f);
    }
    ArrayList<Food> getSelectedMenu(){
        return activeRestaurant.menu;
    }
    void selectFood(int index) throws Food.InvalidFoodID {             //searches in the global list, unique IDs ...
        activeFood = Food.getFoodbyId(index);
    }
    //    void editActiveFood(Food f) throws Food.FoodHasActiveOrder, Food.InvalidFoodID {
//        if (!activeRestaurant.foodHasActiveOrder(activeFood.getId())){
//            activeFood.copyWithKeepingIdentity(f);
//        }
//        throw new Food.FoodHasActiveOrder();
//    }
//    void addFood(Food f){      // Id will be given somewhere else
//        activeRestaurant.addFood(f);
//        activeFood = f;
//    }
    void deleteFood(){
        activeRestaurant.removeFood(activeFood);
    }
    void activateFood(){
        activeFood.activate();
    }
    void deactivateFood() throws Food.InvalidFoodID, Food.FoodHasActiveOrder {
        if(!activeRestaurant.foodHasActiveOrder(activeFood.getId()))activeFood.deactivate();
        else {
            throw new Food.FoodHasActiveOrder();
        }
    }
    void setDiscount(double discount, long time) throws Food.InvalidDiscountException {
        activeFood.setDiscount(discount, time);
    }
    void addResponse(int commentID, String message) throws Food.CommentIdInvalid {
        activeFood.getCommentById(commentID).addReply(message,this);
    }
    void editResponse(int commentID,String newmessage) throws Food.CommentIdInvalid {
        Comment c = activeFood.getCommentById(commentID).getReply();
        if(c==null){
            activeFood.getCommentById(commentID).addReply(newmessage,this);
        }
        else{
            c.editmessage(newmessage);
        }
    }
    ArrayList<Order> getActiveOrders(){
        return activeRestaurant.getActiveOrders();
    }
    ArrayList<Order> getAllOrders(){
        return activeRestaurant.getOrderList();
    }
}
