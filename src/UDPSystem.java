package src;

public class UDPSystem {

    public static void main(String[] args) {
        if (args.length < 2) {
            error();
            return;
        }

        String node_type = args[0].toLowerCase();
        String raw_port_num = args[1];

        int port_num = 0;
        try {
            port_num = Integer.parseInt(raw_port_num);
        } catch (Error NumberFormatException) {
            error();
        }

        if (!Utility.isPortAvailable(port_num)) {
            System.out.println("Specified port (" + port_num + ") is not available.");
            return;
        }

        if (
            node_type.equalsIgnoreCase(Utility.BASE_NODE) ||
            node_type.equalsIgnoreCase(Utility.SUPER_NODE)
        ) {
            Node node = new Node(node_type, port_num);
            node.startupNode();
        } else {
            error();
            return;
        }
    }

    private static void error() {
        System.out.println("Error :: Usage :: java [base|super] [port]");
    }
}
