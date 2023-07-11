package Snapp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class Food {
    /* static vars */
    static int nextID = 0;
    static ArrayList<Food> foodList = new ArrayList<>();

    /* static methods */
    public static Food createFood(String name, double price, FoodType foodtype, long cookingTime, Restaurant owner)
            throws InvalidPriceException {
        Food food = new Food(nextID++, name, price, foodtype, cookingTime, owner);
        foodList.add(food);
        return food;
    }

    public static int deleteFood(Food f) {
        if (foodList.contains(f)) {
            foodList.remove(f);
            return 1;
        }

        return 0;
    }

    static Food getFoodbyName(String s) throws InvalidFoodID {
        for (Food f : foodList)
            if (s.equals(f.name))
                return f;

        throw new InvalidFoodID();
    }

    static Food getFoodbyId(int id) throws InvalidFoodID {
        for (Food f : foodList)
            if (f.id == id)
                return f;
        throw new InvalidFoodID();
    }

    /* local vars */
    private int id;
    private String name;
    private double price;
    private FoodType foodtype;
    private boolean activation = true;
    private double discount = 0.0;
    private long cookingTime;
    int ownerid;
    private Restaurant owner;
    ArrayList<Comment> comments;
    ArrayList<Rating> ratings;
//    Time discount

    /* local methods */
    Food(int id, String name, double price, FoodType foodtype, long cookingTime, Restaurant owner)
            throws InvalidPriceException {
        this.id = id;                                   //internal copies which i made(Abbas) use -1 id
        this.name = name;
        this.setPrice(price);
        this.foodtype = foodtype;
        this.cookingTime = cookingTime;
        this.owner = owner;
        this.comments=new ArrayList<>();
        owner.addFood(this);
    }
    void LinkBS(ArrayList<Food> foods){         //called after filling all
        for(Food f:foods){
            nextID=Math.max(nextID,f.getId());
            if(f.getOwner()==null){
                f.owner=Restaurant.getRestaurantByID(f.ownerid);
            }
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws InvalidPriceException {
        if (price >= 0)
            this.price = price;
        else
            throw new InvalidPriceException();
    }

    public FoodType getFoodType() {
        return foodtype;
    }

    public void setFoodType(FoodType foodtype) {
        this.foodtype = foodtype;
    }

    public void activate() {
        activation = true;
    }

    public void deactivate() {
        activation = false;
    }

    public boolean isActive() {
        return activation;
    }

    public class CancelDiscount extends TimerTask
    {
        Food food;
        CancelDiscount(Food food)
        {
            super();
            this.food = food;
        }
        @Override
        public void run()
        {
            food.discount = 0;
        }
    }
    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount, long dTime) throws InvalidDiscountException {
        if (discount > 50 || discount < 0)
            throw new InvalidDiscountException();
        Timer timer = new Timer();
        this.discount = discount;
        timer.schedule(new CancelDiscount(this), dTime);
    }

    public Restaurant getOwner() {
        return owner;
    }

    public void setCookingTime(long cookingTime) {
        this.cookingTime = cookingTime;
    }

    public long getCookingTime() {
        return cookingTime;
    }

    public ArrayList<Comment> getComments()
    {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments)
    {
        this.comments = comments;
    }

    void copyWithKeepingIdentity(Food f){          //copies everything except id
        this.name=f.name;
        this.cookingTime=f.cookingTime;
        this.owner = f.owner;
        this.foodtype=f.foodtype;
        this.activation = f.activation;
        this.discount=f.discount;
        this.price = f.price;
    }
    Comment getCommentById(int id) throws CommentIdInvalid {
        for(Comment c:comments){
            if(c.ID==id) return c;
        }
        throw new CommentIdInvalid();
    }


    // exception class

    static public class InvalidFoodID extends Exception {
        InvalidFoodID() {
            super("[Error] Food with selected ID doesn't exist!");
        }
    }
    static public class InvalidPriceException extends Exception {
        InvalidPriceException() {
            super("[Error] Invalid price value!");
        }
    }

    static public class InvalidDiscountException extends Exception {
        InvalidDiscountException() {
            super("[Error] Invalid discount value!");
        }
    }
    static public class FoodAlreadyDiscounted extends Exception {
        FoodAlreadyDiscounted() {
            super("[Error] Invalid discount value!");
        }
    }

    static public class FoodHasActiveOrder extends Exception {
        FoodHasActiveOrder() {
            super("[Error] Food has active order, cant edit properties!");
        }
    }
    static public class CommentIdInvalid extends Exception {
        CommentIdInvalid() {
            super("[Error] Comment ID is invalid!");
        }
    }


    static public class FoodIsDeactiveException extends Exception
    {
        Food inactiveFood;

        FoodIsDeactiveException(Food inactiveFood)
        {
            super("[Error] Sorry the food:\n" + inactiveFood.id + "\t" + inactiveFood.name + "\n"  + "is inactive!");
            this.inactiveFood = inactiveFood;
        }
    }
    void loadComments(){
        if(this.comments.size()==0){
            for(Comment c : Comment.commentList){
                if(c.restaurant==null && c.food.getId()==this.id) this.comments.add(c);
            }
        }
    }
}
