package Snapp;
enum FoodType {
    FRIED,
    IRANIAN,
    Unknown,
    ITALIAN;
    // etc

    public static FoodType parse(String s) throws UnknownType {
        switch (s){
            case "FRIED" -> {
                return FRIED;
            }
            case "IRANIAN" -> {
                return IRANIAN;
            }
            case "ITALIAN" -> {
                return ITALIAN;
            }
        }
        throw new UnknownType();
    }
    static public class UnknownType extends Exception {     //check if restaurant has an active order
        UnknownType() {
            super("[Error] Check type, its not defined!");
        }
    }
}
