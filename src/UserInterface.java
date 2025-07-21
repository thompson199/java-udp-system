package src;

import java.net.InetAddress;

public class UserInterface {

    private static final String[] MAIN_MENU_OPTIONS = {
        "Ping Other Node",
        "Print Node Info",
        "Quit App",
    };

    public UserInterface() {}

    private static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            int pos = i + 1;
            System.out.print(pos + "." + options[i] + "\n");
        }
    }

    public static void printWelcomeMessage(int port, InetAddress ip) {
        System.out.println("Welcome to the Java UDP App!");
        System.out.println("Node started on port " + port + " with IP of " + ip);
    }

    public static void printMainMenuOptions() {
        System.out.println("Select an action by entering the corresponding number:");
        printOptions(MAIN_MENU_OPTIONS);
        System.out.print("Choice: ");
    }
}
