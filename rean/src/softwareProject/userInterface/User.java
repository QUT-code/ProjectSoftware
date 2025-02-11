package softwareProject.userInterface;

import java.util.Scanner;
import java.io.Console;

public class User {
    private String userName;
    private int userAge;
    private String userEmail;
    private int userPhoneNumber;
    private String address;
    private char[] password;

    public User(String userName, int userAge, String userEmail, int userPhoneNumber, String address, char[] password) {
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.address = address;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public char[] getPassword() {
        return password;
    }

    // Commented out displayUserDetails method for later use
    /*
    public void displayUserDetails() {
        System.out.println("\nUser Details:");
        System.out.println("Username: " + userName);
        System.out.println("Age: " + userAge);
        System.out.println("Email: " + userEmail);
        System.out.println("Phone Number: " + userPhoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Password: ********");
    }
    */

    public void clearPassword() {
        if (password != null) {
            java.util.Arrays.fill(password, ' ');
        }
    }

    public static User createUser(Scanner scan, Console console) {
        System.out.println("Enter your username: ");
        String userName = scan.nextLine();

        System.out.println("Enter your age: ");
        int userAge = scan.nextInt();
        scan.nextLine(); 

        System.out.println("Enter your email address: ");
        String userEmail = scan.nextLine();

        System.out.println("Enter your phone number: ");
        int userPhoneNumber = scan.nextInt();
        scan.nextLine(); 

        System.out.println("Enter your address: ");
        String address = scan.nextLine();

        char[] password;
        if (console == null) {
            System.out.println("Console is not available. Password input will not be hidden.");
            System.out.println("Enter your password: ");
            password = scan.nextLine().toCharArray();
        } else {
            password = console.readPassword("Enter your password: ");
        }

        // Return a new User object with the entered information
        return new User(userName, userAge, userEmail, userPhoneNumber, address, password);
    }
}
