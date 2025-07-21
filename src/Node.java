package src;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Node {

    private static String node_type;
    private static int node_port;
    private static boolean is_super_node;

    private static InetAddress node_ip_address;
    private static UUID node_uid;

    public Node(String type, int port_num) {
        node_type = type;
        node_port = port_num;
        is_super_node = (node_type.equalsIgnoreCase(Utility.SUPER_NODE));

        node_ip_address = getLocalHostIP();
        node_uid = UUID.randomUUID();
    }

    public void startupNode() {
        Scanner sc = new Scanner(System.in);
        boolean has_quit = false;

        System.out.println("Welcome to the Java UDP App!");
        while (!has_quit) {
            UserInterface.printMainMenuOptions();
            String input = sc.nextLine();

            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Utility.printErrorMessage("Invalid number entered");
                continue;
            }

            Utility.clearConsole();

            switch (choice) {
                case 1:
                    System.out.println("This functionality is still WIP");
                    break;
                case 2:
                    printNodeInfo();
                    break;
                case 3:
                    System.out.println("Quitting app");
                    has_quit = true;
                    break;
                default:
                    Utility.printErrorMessage("Number out of range for presented options");
                    break;
            }
        }

        sc.close();
    }

    private static void printNodeInfo() {
        String type = "Node Type: " + node_type;
        String port = "\nPort Number: " + node_port;
        String sup = "\nIs Super: " + is_super_node;
        String ip = "\nIP Address: " + node_ip_address;

        System.out.println(type + port + sup + ip + "\n");
    }

    private static InetAddress getLocalHostIP() {
        InetAddress ip = null;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("8.8.8.8", 53), 5000);
            ip = socket.getLocalAddress();
        } catch (IOException e) {
            try {
                ip = InetAddress.getLocalHost();
            } catch (IOException err) {
                System.out.println("Error :: Failed to get local host ip addresss");
            }
        }

        return ip;
    }
}
