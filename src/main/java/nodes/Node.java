package nodes;

import common.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.UUID;

public abstract class Node {

    protected InetAddress node_ip_address;
    protected int node_port;

    protected String node_type;

    protected final UUID node_uid;
    protected DatagramSocket socket;

    protected Thread network_listener;
    protected Scanner sc;

    public Node(int port_num) throws IOException {
        this.node_port = port_num;
        this.node_ip_address = getLocalHostIP();

        this.node_uid = UUID.randomUUID();
        this.socket = new DatagramSocket(port_num);
    }

    public void start() throws IOException {
        this.sc = new Scanner(System.in);
        boolean has_quit = false;

        startNetworkListener();

        UserInterface.printWelcomeMessage(node_port, node_ip_address);

        while (!has_quit) {
            UserInterface.printMainMenuOptions();
            String input = this.sc.nextLine();

            int choice = Utility.parseStringToInt(input);

            if (choice == -1) {
                Utility.printErrorMessage("Invalid number entered");
                continue;
            }

            Utility.clearConsole();

            switch (choice) {
                case 1:
                    handlePingNode();
                    break;
                case 2:
                    printNodeInfo();
                    break;
                case 3:
                    has_quit = true;
                    stopNode();
                    break;
                default:
                    Utility.printErrorMessage("Number out of range for presented options");
                    break;
            }
        }

        this.network_listener.interrupt();
        this.sc.close();
    }

    protected void startNetworkListener() {
        this.network_listener = new Thread(() -> {
            byte[] buffer = new byte[1024];

            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                String senderIP = packet.getAddress().getHostAddress();
                int senderPort = packet.getPort();

                System.out.println(
                    "Received: " + receivedMessage + " from " + senderIP + ":" + senderPort
                );
            } catch (IOException e) {
                Utility.printErrorMessage("Problem receiving message");
            }
        });

        this.network_listener.setDaemon(true);
        this.network_listener.start();
    }

    protected String[] askUserForNodeInfo() {
        System.out.print("Enter an IP address: ");
        String ip = this.sc.nextLine();

        System.out.print("Enter a port number: ");
        String port = this.sc.nextLine();

        String[] node_info = { ip, port };

        return node_info;
    }

    /**
     * This method handles the functionality for pinging a node based on user input
     * of IP address and port number.
     *
     * @param None
     * @return None
     */
    protected void handlePingNode() {
        boolean valid_node_info = false;
        String target_ip = null;
        int target_port = -1;

        while (!valid_node_info) {
            System.out.println("Enter the information for the node that should be pinged.");
            String[] node_info = askUserForNodeInfo();

            // Evaluate IP address
            target_ip = node_info[0];
            boolean valid_ip = Utility.isValidIP(target_ip);

            // Evaluate port number
            target_port = Utility.parseStringToInt(node_info[1]);
            int length = node_info[1].length();
            boolean valid_port = (target_port != -1 && length == 4);

            // Print out corresponding messages if any of the provided node info is invalid
            if (!valid_ip && !valid_port) {
                Utility.clearConsole();
                Utility.printErrorMessage("Both IP address and port number were invalid");
            } else if (!valid_ip) {
                Utility.clearConsole();
                Utility.printErrorMessage("Invalid IP address entered :: format = xxx.xxx.xxx.xxx");
            } else if (!valid_port) {
                Utility.clearConsole();
                Utility.printErrorMessage("Invalid port number entered");
            }

            valid_node_info = (valid_ip && valid_port) ? true : false;
        }

        Utility.clearConsole();
        System.out.println("Sending a PING to " + target_ip + " on port " + target_port);
        pingNode(target_ip, target_port);
    }

    protected void pingNode(String target_ip, int target_port) {
        try {
            byte[] buffer = ("PING").getBytes();
            InetAddress address = InetAddress.getByName(target_ip);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, target_port);
            this.socket.send(packet);
        } catch (IOException e) {
            Utility.printErrorMessage("Failed to ping node on " + target_ip + ":" + target_port);
        }
    }

    protected void printNodeInfo() {
        String type = "Node Type: " + this.node_type;
        String port = "\nPort Number: " + this.node_port;
        String ip = "\nIP Address: " + this.node_ip_address;

        System.out.println(type + port + ip + "\n");
    }

    protected void stopNode() {
        if (this.socket != null && !socket.isClosed()) {
            System.out.println("Stopping Node...");
            this.socket.close();
        }
        System.out.println("Quitting app...");
    }

    protected InetAddress getLocalHostIP() {
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
