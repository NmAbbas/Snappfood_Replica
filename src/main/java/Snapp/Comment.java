package Snapp;

import java.util.ArrayList;

public class Comment {

    /* static vars */
    static ArrayList<Comment> commentList = new ArrayList<>();

    public Comment() {
        
    }

    Comment checkIfHasCommentedInList(ArrayList<Comment> comments, int userID)
    {
        for (Comment c : comments)
            if(c.getCommenter().getId() == userID)
                return c;
        return null;
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

    public static void createComment(String message, User commenter, Restaurant restaurant, Food food) throws HasCommentedBeforeException
    {
        Comment c = new Comment(message, commenter, restaurant, food);
//        food.comments.add(c);
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

    public Comment(String message, Account commenter,Restaurant restaurant, Food food) throws HasCommentedBeforeException{
        this.ID=nextID++;
        this.message = message;
        this.commenter = commenter;
        this.upper = null;
        this.restaurant = restaurant;
        this.food=food;
        if(restaurant != null)
        {
            checkIfHasCommentedInList(restaurant.getCommentSection(), commenter.getId());
            restaurant.getCommentSection().add(this);
        }

        if(food != null)
        {
            if(checkIfHasCommentedInList(food.getComments(), commenter.getId()) != null)
                throw new HasCommentedBeforeException();
            food.getComments().add(this);
        }

    }

    private Comment(String message, Comment upper, Account commenter,Restaurant restaurant, Food food) throws HasCommentedBeforeException{
        this.ID=nextID++;
        this.message = message;
        this.upper = upper;
        this.commenter = commenter;
        this.restaurant = restaurant;
        this.food = food;
        if(restaurant != null)
        {
            checkIfHasCommentedInList(restaurant.getCommentSection(), commenter.getId());
            restaurant.getCommentSection().add(this);
        }
        if(food != null)
        {
            if(checkIfHasCommentedInList(food.getComments(), commenter.getId()) != null)
                throw new HasCommentedBeforeException();
            food.getComments().add(this);
        }
        commentList.add(this);
    }

    public class HasCommentedBeforeException extends Exception
    {
        HasCommentedBeforeException()
        {
            super("[Error] you have already made a comment!");
        }
    }


    void addReply(String comment, Account c)  throws HasCommentedBeforeException {
        this.reply = new Comment(comment, this, c,this.restaurant, this.food);
    }
    void editmessage(String message){
        this.message=message;
    }
    Comment getReply(){
        return this.reply;
    }
    public static Comment checkIfHasCommentedInList(ArrayList<Comment> comments, int userID)
    {
        for (Comment c : comments)
            if(c.getCommenter().getId() == userID)
                return c;
        return null;
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
