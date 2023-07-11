package Snapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.security.spec.KeySpec;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;


public class Account {
    /* static variables made for managing all accounts: */
    static ArrayList<Account> AccountList = new ArrayList<>();
    static Account activeUser;
    static int nextID = 0;
    /* local variables for a specific user: */
    protected int id;
    protected String name;
    protected byte[] hashedPassword;
    protected byte[] salt;
    protected ArrayList<String> notifications = new ArrayList<>();
    protected boolean isadmin = false;
    protected boolean isDelivery = false;
    protected int location;

    /* account functions: */
    protected Account(String name, String pass, int id)
            throws InvalidUsernameException, InvalidPasswordException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (usernameIsInvalid(name))
            throw new InvalidUsernameException();
        if (passwordIsInvalid(pass))
            throw new InvalidPasswordException();
        this.name = name;
        this.id = id;
        generateSalt();
        hashedPassword = hashPassword(pass);
    }

    /* static functions for managing all acounts */
    static Account createAccount(String name, String pass)
            throws InvalidUsernameException, InvalidPasswordException,
            NoSuchAlgorithmException, InvalidKeySpecException
    {
        Account acc = new Account(name, pass, nextID++);
        AccountList.add(acc);
        return acc;
    }

    public static Account login(String name, String pass) throws IncorrectPasswordException, NoSuchAlgorithmException,
            InvalidKeySpecException, UsernameNotExists, UserAlreadySignedin
    {
        if (activeUser != null) throw new UserAlreadySignedin();
        Account acc = findAccount(name);
        if (Arrays.equals(acc.hashedPassword, acc.hashPassword(pass)))
            return acc;

        throw new IncorrectPasswordException(); // user exists but the password is incorrect
    }

    static void logout()
    {
        activeUser = null;
    }

    static Account findAccount(String name) throws UsernameNotExists {

        for (Account acc : AccountList)
            if (acc.name.equals(name))
                return acc;
        throw new UsernameNotExists();
    }

    static boolean usernameIsInvalid(String name)
    {
        if (!name.matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,20}[a-zA-Z0-9]$"))
            return true;
        return false;
        /*
         * Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
         * Username allowed of the dot (.), underscore (_), and hyphen (-).
         * The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
         * The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
         * The number of characters must be between 3 to 20.
         */
    }

    static boolean passwordIsInvalid(String pass)
    {
        if (!pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))
            return true;
        return false;
        /* Password must contain at least one digit [0-9].
         * Password must contain at least one lowercase Latin character [a-z].
         * Password must contain at least one uppercase Latin character [A-Z].
         * Password must contain at least one special character like ! @ # & ( ).
         * Password must contain a length of at least 8 characters and a maximum of 90 characters.
         */
    }

    public static Account getActiveUser()
    {
        return activeUser;
    }

    public static void setActiveUser(Account activeUser)
    {
        Account.activeUser = activeUser;
    }

    private void generateSalt()
    {
        SecureRandom random = new SecureRandom();
        salt = new byte[16];
        random.nextBytes(salt);
    }

    private byte[] hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }

    void setadmin()
    {
        this.isadmin = true;
    }

    void admintry() throws UserNotAdmin
    {
        if (!this.isadmin) throw new UserNotAdmin();
    }

    void deliverytry() throws UserNotDelivery
    {
        if (!this.isDelivery) throw new UserNotDelivery();
    }

    public int getId() {
        return id;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public boolean isIsadmin() {
        return isadmin;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public int getLocation()
    {
        return location;
    }

    public void setLocation(int location)
    {
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /* Exceptions */
    static public class InvalidUsernameException extends Exception
    {
        InvalidUsernameException()
        {
            super("[Error] Invalid username!");
        }
    }

    static public class InvalidPasswordException extends Exception {
        InvalidPasswordException() {
            super("[Error] Invalid password!");
        }
    }

    static public class IncorrectPasswordException extends Exception {
        IncorrectPasswordException() {
            super("[Error] Incorrect password!");
        }
    }

    static public class UsernameNotExists extends Exception {
        UsernameNotExists() {
            super("[Error] Username doesn't exist!");
        }
    }

    static public class UserAlreadySignedin extends Exception {
        UserAlreadySignedin() {
            super("[Error] User already signed in, logout first!");
        }
    }

    static public class UserNotSignedin extends Exception {
        UserNotSignedin() {
            super("[Error] User not signed in, login first!");
        }
    }

    static public class UserNotAdmin extends Exception {
        UserNotAdmin() {
            super("[Error] User is normal, not an admin!");
        }
    }

    static public class UserNotDelivery extends Exception {
        UserNotDelivery() {
            super("[Error] User is normal, not an admin!");
        }
    }
}
