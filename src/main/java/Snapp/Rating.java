package Snapp;

import java.util.ArrayList;

public class Rating {
    private double rating;
    private User rater;

    Rating(double rating, User rater) {
        this.rating = rating;
        this.rater = rater;
    }

    public static Rating avgRatingList(ArrayList<Rating> ratings)
    {
        Rating avg = new Rating(0, null);
        for (Rating r: ratings)
        {
            avg.rating += r.rating;
        }

        avg.rating /= ratings.size();

        return avg;
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

    double getRating() {
        return rating;
    }
}
