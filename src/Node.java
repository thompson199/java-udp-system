package src;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Node {

    private static String node_type;
    private static int node_port;
    private static InetAddress node_ip_address;
    private static boolean is_super_node;

    public Node(String type, int port_num) {
        node_type = type;
        node_port = port_num;
        is_super_node = (node_type.equalsIgnoreCase(Utility.SUPER_NODE));
        node_ip_address = getLocalHostIP();
    }

    public void startupNode() {
        String type = "Type: " + node_type;
        String port = "\nNum: " + node_port;
        String sup = "\nIs Super: " + is_super_node;
        String ip = "\nIP Address: " + node_ip_address;

        System.out.println(type + port + sup + ip);
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
