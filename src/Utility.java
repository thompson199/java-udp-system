package src;

public class Utility {

    public Utility() {}

    private static final int MIN_PORT_NUMBER = 8080;
    private static final int MAX_PORT_NUMBER = 8099;

    public static final String BASE_NODE = "base";
    public static final String SUPER_NODE = "super";

    /**
     * This method checks if the specified port is available for use
     * @param port_num
     * @return boolean - indicates availability of port
     */
    public static boolean isPortAvailable(int port_num) {
        if (port_num < MIN_PORT_NUMBER || port_num > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Error :: Invalid port given :: " + port_num);
        } else {
            return true;
        }
    }
}
