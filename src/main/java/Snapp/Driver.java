package Snapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {





    static Scanner scanner;
    static boolean running;

    Driver() {
        scanner = new Scanner(System.in);
        running = true;
    }

    static void userlogin(String username, String pass) {
        Account a;
        try {
            a = Account.login(username, pass);
            Account.activeUser = a;
            System.out.println("USER LOGIN SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("wow");
        }

    }

    static void adminlogin(String username, String pass) {
        Account a;
        try {
            a = Account.login(username, pass);
            a.admintry();
            Account.activeUser = a;
            System.out.println("ADMIN LOGIN SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void adminnew(String username, String pass) {
        Account a;
        try {
            Admin.createAccount(username, pass);
            System.out.println("ADMIN CREATION SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void usernew(String username, String pass) {
        Account a;
        try {
            User.createAccount(username, pass);
            System.out.println("USER CREATION SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void deliverynew(String username, String pass) {
        Account a;
        try {
            Delivery.createAccount(username, pass);
            System.out.println("DELIVERY CREATION SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void deliverylogin(String username, String pass) {
        Account a;
        try {
            a = Account.login(username, pass);
            a.admintry();
            Account.activeUser = a;
            System.out.println("DELIVERY LOGIN SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // user methods
    static void printRestaurantList(ArrayList<Restaurant> restaurants)
    {
        System.out.println("restaurants list:");
        System.out.println("ID\t\tRESTAURANT");
        for(Restaurant r : restaurants)
        {
            System.out.println(r.getId() + "\t\t" + r.getName());
        }
    }

    static void printAllRestaurants()
    {
        printRestaurantList(Restaurant.getRestaurantList());
    }

    static int userRestaurantSelect(int id)
    {
        return User.getActiveUser().selectRestaurant(id);
    }

    // admin methods
    static void printAdminRestaurants(){
        try {
            Account.activeUser.admintry();
        } catch (Account.UserNotAdmin e) {
            System.out.println(e.getMessage());
        }
        System.out.println("ID\t\tRESTAURANT");
        for(Restaurant r: (Admin.getActiveUser().getRestaurants())){
            System.out.println(r.getId() + "\t\t" + r.getName());
        }
    }
    static void editType(String s){
        try {
            ArrayList<FoodType> fff = new ArrayList<>();
            fff.add(FoodType.parse(s));
            Admin.getActiveUser().editFoodtype(fff);
            System.out.println("FOODTYPE EDIT SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void addType(String s){
        try {
            ArrayList<FoodType> fff = new ArrayList<>();
            fff.add(FoodType.parse(s));
            Admin.getActiveUser().addFoodtype(fff);
            System.out.println("FOODTYPE ADDITION SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void editFood(String foodID,String parameter,String value){
        try {
            Admin.getActiveUser().selectFood(Integer.parseInt(foodID));
            Food base = Admin.getActiveUser().getActiveFood() ;
            switch (parameter){
                case "name" -> base.setName(value);
                case "price" -> base.setPrice(Double.parseDouble(value));
                // ....
            }
            System.out.println("FOOD EDIT SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void addFood(String name, String price,String type,String cookingtime){
        try {
            Restaurant r = Admin.getActiveUser().getActiveRestaurant();
            Admin a = Admin.getActiveUser();
            Food.createFood(name,Double.parseDouble(price),FoodType.parse(type),Long.parseLong(cookingtime),r);
            System.out.println("FOOD ADDITION SUCCESS");
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void deleteFood(String id){
        try {
            Restaurant r = Admin.getActiveUser().getActiveRestaurant();
            Admin a = Admin.getActiveUser();
            a.selectFood(Integer.parseInt(id));
            a.deleteFood();
            System.out.println("FOOD DELETION SUCCESS");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void activateFood(String id){
        try {
            Restaurant r = Admin.getActiveUser().getActiveRestaurant();
            Admin a = Admin.getActiveUser();
            a.selectFood(Integer.parseInt(id));
            a.activateFood();
            System.out.println("FOOD ACTIVATION SUCCESS");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void deactivateFood(String id){
        try {
            Restaurant r = Admin.getActiveUser().getActiveRestaurant();
            Admin a = Admin.getActiveUser();
            a.selectFood(Integer.parseInt(id));
            a.deactivateFood();
            System.out.println("FOOD DEACTIVATION SUCCESS");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void discountFood(String id,String discount,String time){
        try {
            Restaurant r = Admin.getActiveUser().getActiveRestaurant();
            Admin a = Admin.getActiveUser();
            a.selectFood(Integer.parseInt(id));
            a.setDiscount(Double.parseDouble(discount), Long.parseLong(time));
            System.out.println("FOOD DISCOUNT SUCCESS");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void selectFood(String id){
        try {
            Admin.getActiveUser().selectFood(Integer.parseInt(id));
            System.out.println("FOOD SELECTION SUCCESS");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void addReply(String id,String message){
        try {
            Restaurant r = Admin.getActiveUser().getActiveRestaurant();
            Admin a = Admin.getActiveUser();
            a.addResponse(Integer.parseInt(id),message);
            System.out.println("REPLY ADDITION SUCCESS");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void editReply(String id,String message){
        try {
            Restaurant r = Admin.getActiveUser().getActiveRestaurant();
            Admin a = Admin.getActiveUser();
            a.editResponse(Integer.parseInt(id),message);
            System.out.println("REPLY EDIT SUCCESS");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void printOrders(ArrayList<Order> orders)
    {
        System.out.println("menu:");
        System.out.println("ID\t\tNAME\t\tRESTAURANT\t\tDISCOUNT\t\t");
        for(Order o : orders)
        {
            System.out.println(o.getId() + "\t\t" + o.getCostomer().getName() + "\t\t" + o.getRecipient());
            for(Food f : o.getFoods()){
                System.out.println("\t\tID\t\tFOOD\t\tPRICE\t\tDISCOUNT\t\t");
                System.out.println("\t\t" +f.getId() + "\t\t" + f.getName() + "\t\t" + f.getPrice()+ "\t\t" +f.getDiscount());
            }
            System.out.println("=================================================");
        }
    }

    static void printOrdersLocation(ArrayList<Order> orders)
    {
        System.out.println("menu:");
        System.out.println("ID\t\tSTART\t\tDEST");
        for(Order o : orders)
        {
            System.out.println(o.getId() + "\t\t" + o.getRecipient().getLocation() + "\t\t" + o.getCostomer().getLocation());
            System.out.println("=================================================");
        }
    }

    // food methods
    static void printMenu(ArrayList<Food> menu)
    {
        System.out.println("menu:");
        System.out.println("ID\t\tFOOD\t\tPRICE\t\tDISCOUNT\t\t");
        for(Food f : menu)
        {
            System.out.println(f.getId() + "\t\t" + f.getName() + "\t\t" + f.getPrice()+ "\t\t" +f.getDiscount());
        }
    }


    // comment methods
    static void printCommentList(ArrayList<Comment> comments)
    {
        System.out.println("Comments: ");
        if(comments.isEmpty())
        {
            System.out.println("no one has mwrote a comment! you can write one with <add new comment {...} command>");
            return;
        }
        System.out.println("ID\t\tUSER:\tMESSAGE");

        for (Comment c : comments)
        {
            hierarchicalCommentPrint(c, 0);
        }
    }

    static void hierarchicalCommentPrint(Comment c, int n)
    {
        for (int i = 0; i < n; i++)
            System.out.print("\t");
        System.out.println(c.toString());
        if(c.getReply() == null)
            return;
        hierarchicalCommentPrint(c.getReply(), ++n);
    }

    // cart
    static void printCart(Cart cart)
    {
        System.out.println("RESTAURANT: " + cart.getRecipient());
        System.out.println("COSTOMER: " + cart.getCostomer());
        System.out.println("SELECTED FOODS:");
        if (cart.getFoods().isEmpty())
        {
            System.out.println("no foods have been selected!");
            return;
        }
        System.out.println("ID\t\tFOOD");
        for (Food f:cart.getFoods())
            System.out.println(f.getId() + "\t\t" + f.getName());
    }






    static void phase1drive(){
        // example creation:
        try
        {
            User u1 = User.createUser("sandy", "P@ss12345678");
            User u2 = User.createUser("mylegfish", "P@ss12345678");
            User u3 = User.createUser("patrick", "P@ss12345678");

            Admin a1 = Admin.createAccount("Mr.Krabs", "P@ss12345678");
            Admin a2 = Admin.createAccount("Plankton", "P@ss12345678");

            Delivery d1 = Delivery.createAccount("spongebob", "P@ss12345678");

            Restaurant r1 = Restaurant.createRestaurant("Krusty Krab", FoodType.FRIED, a1, 0);
            Restaurant r2 = Restaurant.createRestaurant("Chum Bucket", FoodType.FRIED, a2, 1);

            Food f1 = Food.createFood("Krabby Patty", 10, FoodType.FRIED, 10000, r1);
            u1.selectRestaurant(1);

            Comment.createComment("it tastes like shit", u1, null, f1);
            Comment.createComment("i like it", u2, null, f1);
            Comment.createComment("uhhhhhhhhhhhh!", u3, null, f1);

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        ////////
        //////
        /////
        ///
        //

        while (running)
        {
            String inp = scanner.nextLine();
            String[] parts = inp.split("\\s+");

            // user handling :
            if (Account.activeUser == null)
            {
                if (inp.matches("^\\s*login user.*"))
                {
                    userlogin(parts[2], parts[3]);
                } else if (inp.matches("^\\s*login admin.*"))
                {
                    adminlogin(parts[2], parts[3]);
                } else if (inp.matches("^\\s*add user.*"))
                {
                    usernew(parts[2], parts[3]);
                } else if (inp.matches("^\\s*add admin.*"))
                {
                    adminnew(parts[2], parts[3]);
                } else if (inp.matches("^\\s*logout.*"))
                {
                    Account.logout();
                } else if (inp.matches("^\\s*add delivery.*"))
                {
                    deliverynew(parts[2], parts[3]);
                } else if (inp.matches("^\\s*login delivery.*"))
                {
                    deliverylogin(parts[2], parts[3]);
                }
            }

            // admin panel :
            else if(Account.activeUser.isadmin){
                if (inp.matches("^\\s*list restaurants.*")) {
                    printAdminRestaurants();
                }
                else if(inp.matches("^\\s*select restaurant.*")) {
                    Admin.getActiveUser().selectRestaurant(Integer.parseInt(parts[2]));
                    System.out.println("RESTAURANT SELECTION SUCCESS");
                }
                if(Admin.getActiveUser().getActiveRestaurant()!=null){
                    Restaurant activeRestaurant = Admin.getActiveUser().getActiveRestaurant();
                    if (inp.matches("^\\s*show location.*")) {
                        System.out.println("location is : " +activeRestaurant.getLocation());
                    }
                    else if(inp.matches("^\\s*set location.*")) {
                        activeRestaurant.setLocation(Integer.parseInt(parts[2]));
                        System.out.println("RESTAURANT LOCATION SET SUCCESS");
                    }
                    else if(inp.matches("^\\s*show foodtype.*")) {
                        System.out.println("Food type is : " + activeRestaurant.getFoodtype().toString());
                    }
                    else if(inp.matches("^\\s*edit foodtype.*")) {
                        if(activeRestaurant.hasActiveOrder()){
                            System.out.println("there is an active order, try again later");
                        }
                        else {
                            System.out.println("are you sure (y/n)?");
                            String pewpew = scanner.nextLine().trim();
                            if (pewpew.equals("y")) editType(parts[2]);
                        }
                    }
                    else if(inp.matches("^\\s*add foodtype.*")) {
                        if(activeRestaurant.hasActiveOrder()){
                            System.out.println("there is an active order, try again later");
                        }
                        else {
                            System.out.println("are you sure (y/n)?");
                            String pewpew = scanner.nextLine().trim();
                            if (pewpew.equals("y")) addType(parts[2]);
                        }
                    }
                    else if(inp.matches("^\\s*show menu.*")) {
                        printMenu(activeRestaurant.getMenu());
                    }
                    else if(inp.matches("^\\s*edit food.*")) {
                        editFood(parts[2],parts[3],parts[4]);
                    }
                    else if(inp.matches("^\\s*add food.*")) {
                        addFood(parts[2],parts[3],parts[4],parts[5]);
                    }
                    else if(inp.matches("^\\s*delete food.*")) {
                        deleteFood(parts[2]);
                    }
                    else if(inp.matches("^\\s*activate food.*")) {
                        activateFood(parts[2]);
                    }
                    else if(inp.matches("^\\s*deactivate food.*")) {
                        deactivateFood(parts[2]);
                    }
                    else if(inp.matches("^\\s*discount food.*")) {
                        discountFood(parts[2],parts[3],parts[4]);
                    }
                    else if(inp.matches("^\\s*select food.*")) {
                        selectFood(parts[2]);
                    }
                    if(Admin.getActiveUser().getActiveFood()!=null)
                    {
                        if (inp.matches("^\\s*display ratings.*")) {
                            System.out.println("Rating : " + Rating.overallRating(Admin.getActiveUser().getActiveFood().ratings));
                        }
                        else if (inp.matches("^\\s*display comments.*")) {
                            printCommentList(Admin.getActiveUser().getActiveFood().getComments());
                        }
                        else if (inp.matches("^\\s*add new response.*")) {
                            addReply(parts[3], parts[4]);
                        }
                    }
                    if(inp.matches("^\\s*display comments.*")&& Admin.getActiveUser().getActiveFood()==null) {
                        printCommentList(Admin.getActiveUser().getActiveFood().getComments());
                    }
                    else if(inp.matches("^\\s*edit response.*")) {
                        editReply(parts[2],parts[3]);
                    }
                    else if(inp.matches("^\\s*display open orders.*")) {
                        printOrders(Admin.getActiveUser().getActiveOrders());
                    }

                    // EDIT ORDERS ??


                    else if(inp.matches("^\\s*show order history.*")) {
                        printOrders(Admin.getActiveUser().getAllOrders());
                    } else if (inp.matches("\\s*cook\\s+order\\s+\\d+\\s*"))
                    {
                        try{
                        Admin.getActiveUser().getActiveRestaurant().cook(Admin.getActiveUser().getActiveRestaurant().getActiveOrderbyId(Integer.parseInt(parts[2])));
                            System.out.println("the order will be cook in "  +
                                    Admin.getActiveUser().getActiveRestaurant().getActiveOrderbyId(Integer.parseInt(parts[2])).getCookingTime()/(60 * 10000)
                                    + " Minutes and sent sir!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }
            }

            else if (Account.activeUser.isDelivery)
            {
                if(inp.matches("^\\s*show\\s+orders\\s*"))
                {
                    printOrdersLocation(Delivery.undeliveredList);
                } else if (inp.matches("^\\s*select\\s+order\\s+\\d+\\s*"))
                {
                    try
                    {
                        Delivery.getActiveUser().setSelectedOrder(Delivery.getUndeliveredOrderById(Integer.parseInt(parts[2])));
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }


            // normal user panel :
            else if(!Account.activeUser.isadmin && !Account.activeUser.isDelivery)
            {
                    if (inp.matches("^\\s*search\\s+restaurants.*"))
                    {
                        printRestaurantList(Restaurant.searchRestaurants(inp.split("restaurants+")[1].trim()));
                    } else if (inp.matches("^\\s*select\\s+restaurant\\s+\\d+\\s*") )
                    {
                        if(userRestaurantSelect(Integer.parseInt(parts[2])) == 1)
                        {
                            User.getActiveUser().setCart(new Cart(User.getActiveUser().getActiveRestaurant(), User.getActiveUser()));
                            System.out.println("restaurant selected successfully");
                            printMenu(User.getActiveUser().getActiveRestaurant().getMenu());
                        }
                        else
                            System.out.println("[Error] no restaurant exists with given ID!");
                    } else if (inp.matches("^\\s*search\\s+food.*"))
                    {
                        if(User.getActiveUser().getActiveRestaurant() == null)
                            System.out.println("[Error]you should select a restaurant first!");
                        else
                        {
                            printMenu(User.getActiveUser().getActiveRestaurant().searchMenu(inp.split("food")[1].trim()));
                        }

                    } else if (inp.matches("^\\s*select\\s+food\\s+\\d+\\s*"))
                    {
                        if(User.getActiveUser().getActiveRestaurant() == null)
                            System.out.println("[Error]you should select a restaurant first!");
                        else
                        {
                            try {
                                User.getActiveUser().setActiveFood(User.getActiveUser().getActiveRestaurant().selectFood(Integer.parseInt(parts[2])));
                                System.out.println("food selected successfully");
                            }
                            catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } else if (inp.matches("^\\s*display\\s+comments\\s*"))
                    {
                        if(User.getActiveUser().getActiveRestaurant() == null)
                            System.out.println("[Error]you should select a restaurant first!");
                        else if(User.getActiveUser().getActiveFood() == null)
                            System.out.println("[Error]you should select a food first!");
                        else
                        {
                            printCommentList(User.getActiveUser().getActiveFood().getComments());
                        }
                    } else if (inp.matches("^\\s*add\\s+new\\s+comment.*"))
                    {
                        if(User.getActiveUser().getActiveRestaurant() == null)
                            System.out.println("[Error]you should select a restaurant first!");
                        else if(User.getActiveUser().getActiveFood() == null)
                        {
                            Comment.createComment(inp.split("comment")[1].trim(), User.getActiveUser(), User.getActiveUser().getActiveRestaurant(), null);
                            System.out.println("comment created successfully");
                        }
                        else if (User.getActiveUser().hasEverOrderedFood(User.getActiveUser().getActiveFood()))
                        {
                            Comment.createComment(inp.split("comment")[1].trim(), User.getActiveUser(), null, User.getActiveUser().getActiveFood());
                            System.out.println("comment created successfully");
                        }
                        else
                            System.out.println("[Error]you can't comment on a food you have not ordered!");
                    } else if (inp.matches("^\\s*edit\\s+comment\\s+\\d+\\s+.*"))
                    {
                        try
                        {
                            Comment comment = Comment.getCommentById(Integer.parseInt(parts[2]));
                            if(User.getActiveUser() == comment.getCommenter())
                            {
                                comment.editmessage(inp.split("comment\\s+\\d+\\s+")[1].trim());
                                System.out.println("comment edited successfully");
                            }
                            else
                                System.out.println("[Error]this comment isn't your to edit!");
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }

                    } else if (inp.matches("^\\s*add\\s+this\\s+food\\s+to\\s+cart\\s*")
                            && User.getActiveUser().getActiveFood() != null)
                    {
                        try
                        {
                            User.getActiveUser().getCart().addFood(User.getActiveUser().getActiveFood());
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    } else if (inp.matches("^\\s*deselect\\s+restaurant\\s*"))
                    {
                        User.getActiveUser().setActiveRestaurant(null);
                        User.getActiveUser().setActiveFood(null);
                        User.getActiveUser().setCart(null);
                        System.out.println("DONE");
                    } else if (inp.matches("^\\s*deselect\\s+food\\s*"))
                    {
                        User.getActiveUser().setActiveFood(null);
                        System.out.println("DONE");
                    } else if (inp.matches("^\\s*confirm\\s+order\\s*") && User.getActiveUser().getActiveRestaurant() != null)
                    {
                        if(User.getActiveUser().hasActiveOrder())
                        {
                            System.out.println("[Error]you already have an arriving order!");
                        } else if (User.getActiveUser().getCart().getFoods().isEmpty())
                        {
                            System.out.println("[Error] your cart should at least contain one item!");
                        } else
                        {
                            try
                            {
                                User.getActiveUser().getCart().buy();
                                System.out.println("order confirmed succcessfully");
                            }
                            catch (Exception e)
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                    } else if (inp.matches("^\\s*show estimated delivery timer\\s*"))
                    {
                        if(!User.getActiveUser().hasActiveOrder())
                        {
                            System.out.println("you don't have any order to be delivered!");
                        }
                        else {
                            System.out.println("your food will be delivered in " + User.getActiveUser().getActiveOrder());
                        }
                    }
            }
            // map and path :


            // delivery :


            // recommendation :


            // promotions :

            // testing area(should be deleted in final)

        }
    }

}
