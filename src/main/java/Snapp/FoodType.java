package Snapp;
public enum FoodType {
    FRIED,
    IRANIAN,
    CAFE,
    SUPER_MARKET,
    ITALIAN,
    Unknown,
    ;
    // etc

    public static FoodType parse(String s) throws UnknownType {
        switch (s){
            case "FRIED" -> {
                return FRIED;
            }
            case "IRANIAN" -> {
                return IRANIAN;
            }
            case "CAFE" -> {
                return CAFE;
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