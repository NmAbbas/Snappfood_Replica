package Snapp;

import Snapp.Map.NoPathException;
import Snapp.Order.OrderDoesntExistEXception;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Delivery extends Account
{
    /* static variables */
    static final int GRAPH_TIME_COEFFICIENT = 10;
    static ArrayList<Order> undeliveredList = new ArrayList<>();
    /* static methods */
    static Order getUndeliveredOrderById(int id) throws OrderDoesntExistEXception
    {
        for (Order o: undeliveredList)
            if(o.getId() == id)
                return o;
        throw new OrderDoesntExistEXception();
    }

    public static Delivery createAccount(String name, String pass)
            throws InvalidUsernameException, InvalidPasswordException,
            NoSuchAlgorithmException, InvalidKeySpecException
    {
        Delivery acc = new Delivery(name, pass, nextID++);
        AccountList.add(acc);
        return acc;
    }


    /* instance variables */
    private Order selectedOrder = null;
    private Timer timer = new Timer();
    private int nextNode;
    private int destination;
    /* instance methods */

    protected Delivery(String name, String pass, int id) throws InvalidUsernameException, InvalidPasswordException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        super(name, pass, id);
        isDelivery = true;
    }

    public Order getSelectedOrder()
    {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) throws YouAreNotFreeException, NoPathException
    {
        if(isFree())
            this.selectedOrder = selectedOrder;
        else
            throw new YouAreNotFreeException();
        try
        {
            Map.path(location, selectedOrder.getRecipient().getLocation());
            Map.path(selectedOrder.getCostomer().getLocation(), selectedOrder.getRecipient().getLocation());
        }
        catch (NoPathException e)
        {
            this.selectedOrder = null;
            throw e;
        }

    }

    public Timer getTimer()
    {
        return timer;
    }

    public void setTimer(Timer timer)
    {
        this.timer = timer;
    }

    public int getNextNode()
    {
        return nextNode;
    }

    public void setNextNode(int nextNode)
    {
        this.nextNode = nextNode;
    }

    public int getDestination()
    {
        return destination;
    }

    public void setDestination(int destination)
    {
        this.destination = destination;
    }

    long calcArrivalTime(int start, int end) throws NoPathException
    {
        return GRAPH_TIME_COEFFICIENT * Map.ETA(start, end);
    }

    void calcNextNode() throws NoPathException
    {
        if(location == destination)
            nextNode = location;
        ArrayList<Integer> path = null;
        try
        {
            path = Map.path(location, destination);
            nextNode = path.get(1);
        } catch (NoPathException e)
        {
            imFree();
            throw new NoPathException();
        }
    }

    int goToNext() throws NoPathException
    {
        calcNextNode();
        if(location == destination)
            return 1;       // WE HAVE ARRIVED MY FRIEND!
        location = nextNode;
        calcNextNode();
        return 0;           // WE SHOULD STILL CONTINUE
    }

    ArrayList<Integer> showPath() throws YouAreFreeException, NoPathException
    {
        if(selectedOrder == null)
            throw new YouAreFreeException();
        return Map.path(location, destination);
    }

    public static Delivery getActiveUser()
    {
        return (Delivery) activeUser;
    }

    void imFree()
    {
        selectedOrder = null;
    }

    public boolean isFree()
    {
        if(selectedOrder == null)
            return true;
        return false;
    }

    class Advance extends TimerTask
    {
        public Delivery delivery;

        Advance(Delivery delivery)
        {
            super();
            this.delivery = delivery;
        }

        @Override
        public void run() throws RuntimeException
        {
            if(delivery.getSelectedOrder().getOrderState() == OrderState.READY_TO_BE_DELIVERED)
            {
                delivery.destination = delivery.getSelectedOrder().getRecipient().getLocation();
                try
                {
                    if(delivery.goToNext() == 0)
                        advance();
                    else
                        delivery.getSelectedOrder().setOrderState(OrderState.SENT);
                }

                catch (NoPathException e)
                {
                    imFree();
                    throw new RuntimeException(e);
                }
            }

            if(delivery.getSelectedOrder().getOrderState() == OrderState.SENT)
            {
                delivery.destination = delivery.getSelectedOrder().getCostomer().getLocation();
                try
                {
                    if(delivery.goToNext() == 0)
                        advance();
                    else
                    {
                        delivery.getSelectedOrder().setOrderState(OrderState.RECEIVED);
                        delivery.getSelectedOrder().getCostomer().receiveOrder(delivery.getSelectedOrder());
                        delivery.imFree();
                        if(Account.getActiveUser().getId() == getId())
                            System.out.println("you delivered the food succesfully!");
                    }

                } catch (NoPathException e)
                {
                    imFree();
                    throw new RuntimeException(e);
                }
            }
        }
    }



    void advance() throws NoPathException
    {
        calcNextNode();
        try
        {
            timer.schedule(new Advance(this), calcArrivalTime(location, destination));
        }

        catch (NoPathException e)
        {
            imFree();
            throw new RuntimeException(e);
        }
    }

    String go() throws NoPathException
    {
        try
        {
            ArrayList<Integer> path1 = Map.path(location, selectedOrder.getRecipient().getLocation());
            ArrayList<Integer> path2 = Map.path(selectedOrder.getRecipient().getLocation(), selectedOrder.getCostomer().getLocation());
            timer.schedule(new Give(this), calcArrivalTime(location, selectedOrder.getRecipient().getLocation()) + calcArrivalTime(selectedOrder.getRecipient().getLocation(), selectedOrder.getCostomer().getLocation()));
            return path1.toString() + "\t" + path2.toString();
        }

        catch (NoPathException e)
        {
            imFree();
            throw new RuntimeException(e);
        }
    }
    class Give extends TimerTask
    {
        Delivery delivery;
        Give(Delivery delivery)
        {
            super();
            this.delivery = delivery;
        }

        @Override
        public void run() throws RuntimeException
        {
            delivery.getSelectedOrder().setOrderState(OrderState.RECEIVED);
            delivery.getSelectedOrder().getCostomer().receiveOrder(delivery.getSelectedOrder());
            delivery.imFree();
            if(Account.getActiveUser().getId() == getId())
                System.out.println("you delivered the food succesfully!");
        }
    }

    public static class YouAreNotFreeException extends Exception
    {
        YouAreNotFreeException()
        {
            super("[Error]Sorry you should deliver the food you have first!");
        }
    }

    public static class YouAreFreeException extends Exception
    {
        YouAreFreeException()
        {
            super("[Error]Sorry you don't have anything to deliver!");
        }
    }

    public static class SuccessfullDeliveryException extends Exception
    {
        SuccessfullDeliveryException()
        {
            super("you delivered the food successfully!");
        }
    }
}
