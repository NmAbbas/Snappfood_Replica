package Snapp;

import java.util.ArrayList;

public class DiscountCard
{
    public static ArrayList<DiscountCard> discountCardsList = new ArrayList<>();
    public static int nextId = 0;
    private int id;
    private User owner;
    private double discount;

    static int giveDiscountCardToUser(User owner, double paidCash)
    {
        int disSize = ((int)paidCash - 100) / 10;
        if(disSize > 80)
            disSize = 80;
        if(disSize <= 0)
            return 0;
        DiscountCard disc = new DiscountCard(nextId++, owner, ((double)disSize) / 100.0);
        discountCardsList.add(disc);
        owner.getDiscountCards().add(disc);
        return disSize;
    }

    public DiscountCard(int id, User owner, double discount)
    {
        this.id = id;
        this.owner = owner;
        this.discount = discount;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
