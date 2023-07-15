package Snapp.Controller;

import java.util.Random;

public class Cap {

    static String[] files = {"cc1.jpg","cc2.jpg","cc3.jpg"};
    static String[] contents = {"RBSKW", "JA3V8" , "YU4RT"};
    public static String[] getrandomacp(){
        Random rand = new Random();
        int c = rand.nextInt(2);
        return new String[]{files[c], contents[c]};
    }
}
