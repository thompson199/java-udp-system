package src;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.UUID;

public class Node {

    private InetAddress node_ip_address;
    private int node_port;

    private String node_type;
    private boolean is_super_node;

    private UUID node_uid;

    public Node(String type, int port_num) throws IOException {
        this.node_ip_address = getLocalHostIP();
        this.node_port = port_num;

        this.node_type = type;
        this.is_super_node = (node_type.equalsIgnoreCase(Utility.SUPER_NODE));

        this.node_uid = UUID.randomUUID();
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

    private void printNodeInfo() {
        String type = "Node Type: " + this.node_type;
        String port = "\nPort Number: " + this.node_port;
        String sup = "\nIs Super: " + this.is_super_node;
        String ip = "\nIP Address: " + this.node_ip_address;

        System.out.println(type + port + sup + ip + "\n");
    }

    private InetAddress getLocalHostIP() {
        InetAddress ip = null;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("8.8.8.8", 53), Utility.FIVE_SECOND_TIMEOUT);
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
