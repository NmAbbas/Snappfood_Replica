package Snapp;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static Snapp.OrderState.PENDING;

public class Order {
    /* static varaibles */
    static int nextId = 0;
    /* instance variables */
    private int id;
    private User costomer;
    private Restaurant recipient;
    private long cookingTime;
    private OrderState orderState;
    private Timer timer = new Timer();

    public void setId(int id)
    {
        this.id = id;
    }

    public Timer getTimer()
    {
        return timer;
    }

    public void setTimer(Timer timer)
    {
        this.timer = timer;
    }

    public long getCookingStartTime()
    {
        return cookingStartTime;
    }

    public void setCookingStartTime(long cookingStartTime)
    {
        this.cookingStartTime = cookingStartTime;
    }

    private long cookingStartTime;
    private ArrayList<Food> orderedFoods = new ArrayList<>();
    private Order(int id, User costomer, ArrayList<Food> orderedFoods,
                  Restaurant recipient) {
        this.id = id;
        this.costomer = costomer;
        this.orderedFoods = orderedFoods;
        this.recipient = recipient;
        calcCookingTime();
        orderState = PENDING;
    }

    /* static methods */
    public static Order createOrder(User costomer, ArrayList<Food> orderedFoods,
                                   Restaurant recipient) {
        Order order = new Order(nextId++, costomer, orderedFoods, recipient);
        order.recipient.handleOrder(order);
        recipient.addOrder(order);
        return order;
    }

    /* instance methods */
    public int getId() {
        return id;
    }

    public User getCostomer() {
        return costomer;
    }

    public void setCostomer(User costomer) {
        this.costomer = costomer;
    }

    public Restaurant getRecipient() {
        return recipient;
    }

    public void setRecipient(Restaurant recipient) {
        this.recipient = recipient;
    }

    public long getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(long cookingTime) {
        this.cookingTime = cookingTime;
    }

    public OrderState getOrderState()
    {
        return orderState;
    }

    public void setOrderState(OrderState orderState)
    {
        this.orderState = orderState;
    }

    public ArrayList<Food> getOrderedFoods() {
        return orderedFoods;
    }

    public void setOrderedFoods(ArrayList<Food> orderedFoods) {
        this.orderedFoods = orderedFoods;
    }

    public void calcCookingTime() {
        long maxT = 0;
        for (Food f : orderedFoods) {
            if (f.getCookingTime() > maxT)
                maxT = f.getCookingTime();
        }

        cookingTime = maxT;
    }

    public void cook() {
        timer.schedule(new Cook(this), cookingTime);
    }

    ArrayList<Food> getFoods(){
        return this.orderedFoods;
    }

    boolean orderIsActive(){
        return (this.orderState== PENDING  );
    }

    long estimateTimer() throws Map.NoPathException//is in miliseconds
    {
        return cookingTime + estimatedDeliveryTime() - passedTime();
    }

    long estimatedDeliveryTime() throws Map.NoPathException
    {
        return (Map.ETA(costomer.getLocation(), recipient.getLocation()) + 4) * Delivery.GRAPH_TIME_COEFFICIENT;
    }

    long passedTime()
    {
        return System.currentTimeMillis() - cookingStartTime;
    }

    public static class OrderDoesntExistEXception extends Exception
    {
        OrderDoesntExistEXception()
        {
            super("[Error] the order doesn't exist in this restaurant!");
        }
    }

    public class Cook extends TimerTask {
        Order order;

        Cook(Order order) {
            this.order = order;
        }

        public void run() {
            order.recipient.deliver(order);
        }
    }

}
