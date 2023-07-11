package Snapp;

import java.util.ArrayList;

public class Comment {

    /* static vars */
    static ArrayList<Comment> commentList = new ArrayList<>();

    public Comment() {
        
    }

    /* static methods */
    static Comment getCommentById(int id) throws CommentDoesntExistException
    {
        for(Comment c : commentList)
        {
            if(c.getID() == id)
                return c;
        }
        throw new CommentDoesntExistException();
    }

    static void createComment(String message, User commenter, Restaurant restaurant, Food food)
    {
        Comment c = new Comment(message, commenter, restaurant, food);
        food.comments.add(c);
        commentList.add(c);
    }

    static public class CommentDoesntExistException extends Exception {
        CommentDoesntExistException() {
            super("[Error] comment doesn't exist!");
        }
    }


    static int nextID=0;
    int ID;
    private String message;
    private Comment reply ;
    static Comment activeComment;
    private Comment upper; // this only exists if the comment is a reply
    private Account commenter;
    Restaurant restaurant = null;
    Food food = null;

    public Comment(String message, Account commenter,Restaurant restaurant, Food food) {
        this.ID=nextID++;
        this.message = message;
        this.commenter = commenter;
        this.upper = null;
        this.restaurant = restaurant;
        this.food=food;
        if(restaurant != null)
            restaurant.getCommentSection().add(this);

        if(food != null)
            food.getComments().add(this);

    }

    private Comment(String message, Comment upper, Account commenter,Restaurant restaurant, Food food) {
        this.ID=nextID++;
        this.message = message;
        this.upper = upper;
        this.commenter = commenter;
        this.restaurant = restaurant;
        this.food = food;
        if(restaurant != null)
            restaurant.getCommentSection().add(this);
        if(food != null)
            food.getComments().add(this);
        commentList.add(this);
    }


    void addReply(String comment, Account c) {
        this.reply = new Comment(comment, this, c,this.restaurant, this.food);
    }
    void editmessage(String message){
        this.message=message;
    }
    Comment getReply(){
        return this.reply;
    }

    void removeReply() {
        this.reply=null;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setReply(Comment reply)
    {
        this.reply = reply;
    }

    public static Comment getActiveComment()
    {
        return activeComment;
    }

    public static void setActiveComment(Comment activeComment)
    {
        Comment.activeComment = activeComment;
    }

    public Comment getUpper()
    {
        return upper;
    }

    public void setUpper(Comment upper)
    {
        this.upper = upper;
    }

    public Account getCommenter()
    {
        return commenter;
    }

    public void setCommenter(Account commenter)
    {
        this.commenter = commenter;
    }

    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }

    public Food getFood()
    {
        return food;
    }

    public void setFood(Food food)
    {
        this.food = food;
    }

    @Override
    public String toString()
    {
        return ID + "\t\t" + commenter.getName()
                + ":\t"+ message;
    }
}
