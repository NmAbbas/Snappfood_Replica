package Snapp;

import Snapp.Food.FoodIsDeactiveException;
import Snapp.User.CurrencyNotEnoughException;

import java.util.ArrayList;

class Cart 
{
    private Restaurant recipient;
    private User costomer;
    private ArrayList<Food> foods = new ArrayList<>();

    int price() 
    {
        int price = 0;
        for (Food f : foods)
            price += f.getPrice();
        return price;
    }

    public Restaurant getRecipient() 
    {
        return recipient;
    }

    public void setRecipient(Restaurant recipient) 
    {
        this.recipient = recipient;
    }

    public User getCostomer() 
    {
        return costomer;
    }

    public void setCostomer(User costomer) 
    {
        this.costomer = costomer;
    }

    Cart(Restaurant recipient, User costomer)
    {
        this.recipient = recipient;
        this.costomer = costomer;
        foods = new ArrayList<>();
    }
    public void addFood(Food food) throws FoodIsDeactiveException
    {
        if (!food.isActive())
            throw new FoodIsDeactiveException(food);

        foods.add(food);
    }

    public int remvoeFood(Food f) 
    {
        if (foods.contains(f))
            return 0;

        foods.remove(f);
        return 1;
    }

    public ArrayList<Food> getFoods()
    {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods)
    {
        this.foods = foods;
    }

    public void buy() throws CartContainsDeactiveFoodsException, CurrencyNotEnoughException
    {
        ArrayList<Food> inactive = new ArrayList<>();
        for (Food f : foods)
            if (!f.isActive())
                inactive.add(f);

        if (!inactive.isEmpty())
            throw new CartContainsDeactiveFoodsException(inactive);

        costomer.pay(price());
        Order o = Order.createOrder(costomer, foods, recipient);
        costomer.setActiveOrder(o);
    }



    static public class CartContainsDeactiveFoodsException extends Exception 
    {
        static String createMsg(ArrayList<Food> inactiveFoods)
        {
            String msg = "[Error] Sorry your cart contains:\n";
            for (Food f : inactiveFoods)
                msg += f.getId()+ "\t" + f.getName() + "\n";
            msg += "which are unavailable now!";

            return msg;
        }

        ArrayList<Food> inactiveFoods;

        CartContainsDeactiveFoodsException(ArrayList<Food> inactiveFoods)
       	{
            super(createMsg(inactiveFoods));
            this.inactiveFoods = inactiveFoods;

        }
    }
    
}
