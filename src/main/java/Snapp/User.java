package Snapp;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class User extends Account
{

    /* static variables */

    /* static methods */

    /* instance variables */
    private int currency = 1000;
    private ArrayList<Restaurant> previousRestaurants = new ArrayList<>();
    private ArrayList<Order> receivedOrders = new ArrayList<>();
    private Restaurant activeRestaurant = null;
    private Food activeFood = null;
    private Cart cart;
    private Order activeOrder = null;
    /* instance methods */
    protected User(String name, String pass, int id) throws InvalidUsernameException, InvalidPasswordException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        super(name, pass, id);
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

    void pay(int price) throws CurrencyNotEnoughException
    {
        if (currency < price)
            throw new CurrencyNotEnoughException();
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


    static public class CurrencyNotEnoughException extends Exception
    {
        CurrencyNotEnoughException()
        {
            super("[Error] sorry you don't have enough currency, please charge your account!");
        }
    }


}

