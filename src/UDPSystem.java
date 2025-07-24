package src;

import java.io.IOException;
import src.common.*;
import src.nodes.*;

public class UDPSystem {

    public static void main(String[] args) {
        // Handle arguments
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
            Utility.printErrorMessage("Port " + port_num + " is not available.");
            return;
        }

        // Create node
        try {
            if (node_type.equalsIgnoreCase(Utility.BASE_NODE)) {
                BaseNode node = new BaseNode(node_type, port_num);
                node.start();
            } else if (node_type.equalsIgnoreCase(Utility.SUPER_NODE)) {
                SuperNode node = new SuperNode(node_type, port_num);
                node.start();
            } else {
                error();
            }
        } catch (IOException e) {
            Utility.printErrorMessage("Failed to create " + node_type + "node on port " + port_num);
        }
    }

    private static void error() {
        Utility.printErrorMessage("Usage :: java [base|super] [port]");
    }
}
