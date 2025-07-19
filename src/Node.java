package src;

public class Node {

    private static String node_type;
    private static int port_num;
    private static boolean is_super;

    public Node(String node_type_arg, int port_num_arg) {
        node_type = node_type_arg;
        port_num = port_num_arg;
        is_super = (node_type.equalsIgnoreCase(Utility.SUPER_NODE));
    }

    public void startupNode() {
        System.out.println("Type:" + node_type + "\nNum:" + port_num + "\nIs Super:" + is_super);
    }
}
