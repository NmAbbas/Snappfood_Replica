package Snapp;
import java.util.*;
import Snapp.Order.OrderDoesntExistEXception;


public class Restaurant
{
    /* static Instances for managing restaurants */
    private static int nextID = 0;
    static ArrayList<Restaurant> restaurantList = new ArrayList<>();
    /* static methods for managing restaurants */
    public static Restaurant createRestaurant(String name, FoodType foodtype, Admin owner,int loc)
    {
        Restaurant r = new Restaurant(name, nextID++, foodtype, owner,loc);
        restaurantList.add(r);
        owner.addRestaurant(r);
        return r;
    }

    public static ArrayList<Restaurant> getRestaurantsOfType(FoodType foodType)
    {
        HashSet<Restaurant> list = new HashSet<>();

        for (Restaurant r:restaurantList)
        {
            for (FoodType f: r.getFoodtype())
            {
                if(foodType == f)
                    list.add(r);
            }
        }

        return new ArrayList<>(list.stream().toList());
    }

    public static void sort(List<Restaurant> restaurants) {
        Collections.sort(restaurants, new restaurantComparator());
    }

    public static ArrayList<Restaurant> getRestaurantList()
    {
        return restaurantList;
    }

    public static Restaurant getRestaurantByID(int id)
    {
        for (Restaurant r: restaurantList)
        {
            if(r.getId()==id)
                return r;
        }
        return null;
    }
    public static ArrayList<Restaurant> searchRestaurants(String searchString)
    {
        ArrayList<Restaurant> matchings = new ArrayList<>();
        for (Restaurant r: restaurantList)
        {
            if(r.getName().toLowerCase().contains(searchString.toLowerCase()))
                matchings.add(r);
        }

        return matchings;
    }
    public static void LinkBS(ArrayList<Restaurant> r){ //called after filling all

        for(Restaurant rest:r){
            nextID=Math.max(nextID,rest.id);
        }
    }

    private int id;
    private String name;
    ArrayList<Food> menu = new ArrayList<>();
    ArrayList<Comment> commentSection = new ArrayList<>();
    private HashSet<FoodType> foodtype = new HashSet<>();	// this is the restaurant type but the foodtype enum is used for it
    private Admin owner;
    int ownerid;
    int location;

    public ArrayList<Rating> getRatings()
    {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings)
    {
        this.ratings = ratings;
    }

    ArrayList<Rating> ratings = new ArrayList<>();

    public Rating avgRating() throws NoRatingRestaurantException
    {
        if(ratings.size() == 0)
            throw new NoRatingRestaurantException();
        return Rating.avgRatingList(ratings);
    }

    public class NoRatingRestaurantException extends Exception
    {
        NoRatingRestaurantException()
        {
            super("No rating exists for this restaurant");
        }
    }

    public void setFoodtype(HashSet<FoodType> foodtype)
    {
        this.foodtype = foodtype;
    }

    private String imageURL = "/images/hamburger.png";
    private ArrayList<Order> orderList = new ArrayList<>();

    public static int getNextID() {
        return nextID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public ArrayList<Comment> getCommentSection() {
        return commentSection;
    }

    public HashSet<FoodType> getFoodtype() {
        return foodtype;
    }

    public Admin getOwner() {
        return owner;
    }

    /* local methods for a single restaurant */
    Restaurant(String name, int id, FoodType foodtype, Admin owner, int loc)
    {
        this.id = id;
        this.name = name;
        this.foodtype.add(foodtype);
        this.owner = owner;
        this.location =loc;
    }

    // <location variable>


    public void setOwner(Admin owner) {
        this.owner = owner;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    ArrayList<Order> getOrderList() {
        return orderList;
    }

    void addFood(Food food) {
        menu.add(food);
    }

    void removeFood(Food food) {
        menu.remove(food);
        Food.deleteFood(food);
    }


    public void setMenu(ArrayList<Food> menu)
    {
        this.menu = menu;
    }

    void removeFoodById(int fid) {
        // find the food using the Food class id search
        // and get the reference to the food object corresponing
        // to the id and then pass it to removeFood()
    }

    public static class restaurantComparator
            implements Comparator<Restaurant> {
        @Override
        public int compare(Restaurant lhs, Restaurant rhs) {
            if (lhs.getName().compareTo(rhs.getName()) > 0)
                return 1;
            if (rhs.getName().compareTo(lhs.getName()) > 0)
                return -1;
            if (lhs.getId() > rhs.getId())
                return 1;
            if (rhs.getId() > lhs.getId())
                return -1;

            return 0;

        }
    }

    public void handleOrder(Order order) {
        orderList.add(order);
    }

    public Order getActiveOrderbyId(int id) throws OrderDoesntExistEXception
    {
        for(Order o: getActiveOrders())
            if(o.getId() == id)
                return o;
        throw new OrderDoesntExistEXception();
    }

    public void cook(Order order) {
        order.setCookingStartTime(System.currentTimeMillis());
        order.cook();
    }

    public void teleportDeliver(Order order) { // if things go wrong change this to deliver and eliminate delivery class
        order.setOrderState(OrderState.SENT);
        order.getCostomer().receiveOrder(order);
    }

    public void deliver(Order order)
    {
        order.setOrderState(OrderState.READY_TO_BE_DELIVERED);
        Delivery.undeliveredList.add(order);
    }

    int getLocation(){
        return this.location;
    }
    void setLocation(int loc){
        this.location=loc;
    }
    void editFoodtype(ArrayList<FoodType> f) throws FoodTypeUnchangable {           //clears the previous foodtypes
        if(this.hasActiveOrder()){
            throw new FoodTypeUnchangable();
        }
        this.foodtype.clear();
        this.foodtype.addAll(f);
    }
    void addFoodtype(ArrayList<FoodType> f) throws FoodTypeUnchangable {           //just adds to the previous foodtypes
        for(Order o : orderList){
            if(o.getOrderState()==OrderState.GETTING_READY || o.getOrderState()==OrderState.PENDING){
                throw new FoodTypeUnchangable();
            }
        }
        this.foodtype.addAll(f);
    }
    ArrayList<Order> getActiveOrders(){
        ArrayList<Order> a = new ArrayList<>();
        for(Order o : this.orderList){
            if(o.orderIsActive()){
                a.add(o);
            }
        }
        return a;
    }
    void addOrder(Order o){
        this.orderList.add(o);
    }


    static public class FoodTypeUnchangable extends Exception {     //check if restaurant has an active order
        FoodTypeUnchangable() {
            super("[Error] Can't chnage foodtype while there is an active order!");
        }
    }
    boolean hasActiveOrder(){
        for(Order o : orderList){
            if(o.getOrderState()==OrderState.GETTING_READY || o.getOrderState()==OrderState.PENDING){
                return true;
            }
        }
        return false;
    }
    boolean foodHasActiveOrder(int index) throws Food.InvalidFoodID {  //check if a particular food has an active order
        for(Order o : orderList){
            if(o.getOrderState()==OrderState.GETTING_READY || o.getOrderState()==OrderState.PENDING){
                if(o.getFoods().contains(Food.getFoodbyId(index))) return true;
            }
        }
        return false;
    }

    ArrayList<Food> searchMenu(String searchString)
    {
        ArrayList<Food> matching = new ArrayList<>();
        for (Food f : menu)
        {
            if(f.getName().toLowerCase().contains(searchString.toLowerCase()))
                matching.add(f);
        }
        return matching;
    }

    Food selectFood(int id) throws FoodDoesntExistException
    {
        for (Food f: menu)
        {
            if(f.getId() == id)
                return f;
        }
        throw new FoodDoesntExistException();
    }

    private class FoodDoesntExistException extends Exception
    {
        FoodDoesntExistException()
        {
            super("[Error] this food doesn't exist in this restaurant!");
        }
    }



    void loadComments(){
        if(this.commentSection.size()==0){
            for(Comment c : Comment.commentList){
                if(c.restaurant.getId()==this.id && c.food==null) this.commentSection.add(c);
            }
        }
    }

}
