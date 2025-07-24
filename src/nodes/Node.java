package src.nodes;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.UUID;
import src.common.*;

public abstract class Node {

    protected InetAddress node_ip_address;
    protected int node_port;

    protected String node_type;

    protected final UUID node_uid;
    protected DatagramSocket socket;

    protected Thread network_listener;

    public Node(int port_num) throws IOException {
        this.node_port = port_num;
        this.node_ip_address = getLocalHostIP();

        this.node_uid = UUID.randomUUID();
        this.socket = new DatagramSocket(port_num);
    }

    public void start() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean has_quit = false;

        startListening();

        UserInterface.printWelcomeMessage(node_port, node_ip_address);

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
                    pingNode("127.0.0.1", 8081);
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
        sc.close();
    }

    public void startListening() {
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
