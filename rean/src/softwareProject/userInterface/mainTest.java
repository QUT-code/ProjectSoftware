package softwareProject.userInterface;
import java.io.Console;
import java.util.Scanner;

public class mainTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        loginSystem login = new loginSystem();
        login.login();
        User user = User.createUser(scanner, console);

        // user.displayUserDetails();

        user.clearPassword();
    }
}
