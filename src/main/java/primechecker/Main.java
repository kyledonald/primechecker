package primechecker;

import java.util.Scanner;

/**
 * Main class to start the program.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface(scanner);
        ui.start();

        scanner.close();
        System.out.println("Thank you for using the Prime Checker!");
    }
}
