package Snapp;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.EnumSet;

public class User extends Account
{

    /* static variables */

    /* static methods */

    /* instance variables */
    private int currency = 1000;
    private ArrayList<Restaurant> previousRestaurants = new ArrayList<>();
     ArrayList<Order> receivedOrders = new ArrayList<>();
    private Restaurant activeRestaurant = null;
    private Food activeFood = null;
    private ArrayList<DiscountCard> discountCards = new ArrayList<>();
    private Cart cart;
    private Order activeOrder = null;
    /* instance methods */
    protected User(String name, String pass, int id) throws InvalidUsernameException, InvalidPasswordException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        super(name, pass, id);
    }
    public User() {

    }
    public static User createUser(String name, String pass)
            throws InvalidUsernameException, InvalidPasswordException, NoSuchAlgorithmException, InvalidKeySpecException, UsernameTakenException
    {
        try
        {
            findAccount(name);
        }
        catch (Exception e)
        {
            User user = new User(name, pass, nextID++);
            AccountList.add(user);
            return user;
        }
        throw new UsernameTakenException();
    }

    public ArrayList<DiscountCard> getDiscountCards()
    {
        return discountCards;
    }

    public void setDiscountCards(ArrayList<DiscountCard> discountCards)
    {
        this.discountCards = discountCards;
    }

    public static User getActiveUser()
    {
        return (User) activeUser;
    }

    public Restaurant getActiveRestaurant()
    {
        return activeRestaurant;
    }

    public void setActiveRestaurant(Restaurant activeRestaurant)
    {
        this.activeRestaurant = activeRestaurant;
    }

    public Food getActiveFood()
    {
        return activeFood;
    }

    public void setActiveFood(Food activeFood)
    {
        this.activeFood = activeFood;
    }

    public int getCurrency()
    {
        return currency;
    }

    public void setCurrency(int currency)
    {
        this.currency = currency;
    }
    public void addCurrency(int c)
    {
        if(c < 0)
            this.currency += c;
    }
    static User getUserByID(int id){
        for(Account a:Account.AccountList){
            if(a.id==id && !a.isadmin){
                return (User)a;
            }
        }
        return null;
    }
    public static class InvalidCurrencyException extends Exception
    {
        InvalidCurrencyException()
        {
            super("you cannot charge negative currency!");
        }
    }

    public ArrayList<Restaurant> getPreviousRestaurants()
    {
        return previousRestaurants;
    }

    public void setPreviousRestaurants(ArrayList<Restaurant> previousRestaurants)
    {
        this.previousRestaurants = previousRestaurants;
    }

    public ArrayList<Order> getReceivedOrders()
    {
        return receivedOrders;
    }

    public void setReceivedOrders(ArrayList<Order> receivedOrders)
    {
        this.receivedOrders = receivedOrders;
    }

    boolean hasActiveOrder()
    {
        return (activeOrder == null) ? false : true;
    }

    void setActiveOrder(Order o)
    {
        activeOrder = o;
    }

    void deleteActiveOrder()
    {
        activeOrder = null;
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    void receiveOrder(Order order)
    {
        order.setOrderState(OrderState.RECEIVED);
        receivedOrders.add(order);
        deleteActiveOrder();
        notifications.add("your order has been delivered to you!");
    }

    public Order getReceivedOrderbyId(int id) throws Order.OrderDoesntExistEXception
    {
        for(Order o:receivedOrders)
            if(o.getId() == id)
                return o;
        throw new Order.OrderDoesntExistEXception();
    }

    public Order getActiveOrder()
    {
        return activeOrder;
    }

    void pay(int price) throws DiscountCardDoesntExist
    {
        if (currency < price)
            throw new DiscountCardDoesntExist();
        currency -= price;
    }

    int selectRestaurant(int id) // return 1 on success, 0 if doesn,t exist
    {
        for (Restaurant r : Restaurant.getRestaurantList()) {
            if (r.getId() == id) {
                activeRestaurant = r;
                return 1;
            }
        }

        return 0;
    }

    void selectFood(int index) throws Food.InvalidFoodID {             //searches in the global list, unique IDs ...
        activeFood = Food.getFoodbyId(index);
    }

    boolean hasEverOrderedFood(Food food)
    {
        for (Order order: receivedOrders)
            for (Food f:order.getOrderedFoods())
                if (f.getId() == food.getId())
                    return true;
        return false;
    }

    void addRatingToRestaurant(Restaurant restaurant, double rating) throws YouHaveAlreadyRated
    {
        for (Rating r: restaurant.getRatings())
        {
            if(r.getUser() == this)
                throw new YouHaveAlreadyRated();
        }

        restaurant.getRatings().add(new Rating(rating, this));
    }

    void addRatingToFood(Food food, double rating) throws YouHaveAlreadyRated
    {
        for (Rating r: food.getRatings())
        {
            if(r.getUser() == this)
                throw new YouHaveAlreadyRated();
        }

        food.getRatings().add(new Rating(rating, this));
    }

    static public class YouHaveAlreadyRated extends Exception
    {
        public YouHaveAlreadyRated()
        {
            super("you have already rated this!");
        }
    }

    public ArrayList<Restaurant> kNearestRestaurants(int k)
    {
        ArrayList<Restaurant> near = new ArrayList<>();
        Restaurant.getRestaurantList().sort((Restaurant r1, Restaurant r2)-> {
            int t1, t2;
            try
            {
                t1 = Map.ETA(location, r1.location);
            }
            catch (Exception e)
            {
                return -1;
            }
            try
            {
                t2 = Map.ETA(location, r1.location);
            }
            catch (Exception e)
            {
                return 1;
            }

            if(t1 >= t2)
            {
                return 1;
            }
            else
                return -1;
        });

        for (int i = Restaurant.getRestaurantList().size(), j = 0; i >= 0 && j< k; i--, j++)
        {
            near.add(Restaurant.getRestaurantList().get(i));
        }
        return near;
    }

    static public class CurrencyNotEnoughException extends Exception
    {
        CurrencyNotEnoughException()
        {
            super("[Error] sorry you don't have enough currency, please charge your account!");
        }
    }

    public DiscountCard getDiscountCardById(int id) throws DiscountCardDoesntExist
    {
        for (DiscountCard dis: discountCards)
        {
            if(dis.getId() == id)
                return dis;
        }
        throw new DiscountCardDoesntExist();
    }

    static public class DiscountCardDoesntExist extends Exception
    {
        DiscountCardDoesntExist()
        {
            super("[Error] discount card with selected id doesn't exist or you don't own it!");
        }
    }

    public FoodType recommend() throws InsufficientDataForReceomendationException
    {
        if(receivedOrders.isEmpty())
            throw new InsufficientDataForReceomendationException();
        EnumSet<FoodType> enumSet = EnumSet.allOf(FoodType.class);
        int[] usageArray = new int[enumSet.toArray().length];
        java.util.Arrays.fill(usageArray, 0);
        for (Order o:receivedOrders)
            for(FoodType f : o.getRecipient().getFoodtype())
                usageArray[f.ordinal()]++;


        int maxUsage = 0;
        FoodType recommendedEnum = null;
        for (FoodType enumValue : enumSet) {
            int usage = usageArray[enumValue.ordinal()];
            if (usage > maxUsage) {
                maxUsage = usage;
                recommendedEnum = enumValue;
            }
        }
        return recommendedEnum;
    }

    public class InsufficientDataForReceomendationException extends Exception
    {
        InsufficientDataForReceomendationException()
        {
            super("you haven't ordered anything yet, how can i recommend something!");
        }
    }
}

