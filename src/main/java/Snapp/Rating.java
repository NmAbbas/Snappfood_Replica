package Snapp;

import java.util.ArrayList;

public class Rating {
    private int rating;
    private User rater;

    Rating(int rating, User rater) {
        this.rating = rating;
        this.rater = rater;
    }

    User getUser() {
        return rater;
    }
    static double overallRating(ArrayList<Rating> r){
        int res=0,total=0;
        for(Rating mew:r){
            res+=mew.rating;
            ++total;
        }
        return (double) res /total;
    }

    int getRating() {
        return rating;
    }
}
