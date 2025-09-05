package common;

public class Utility {

    private static final int MIN_PORT_NUMBER = 8080;
    private static final int MAX_PORT_NUMBER = 8099;

    public static final int FIVE_SECOND_TIMEOUT = 5000;

    public static final String BASE_NODE = "base";
    public static final String SUPER_NODE = "super";

    public Utility() {}

    /**
     * This method checks if the specified port is available for use
     * @param port_num
     * @return boolean - indicates availability of port
     */
    public static boolean isPortAvailable(int port_num) {
        return (MIN_PORT_NUMBER < port_num && port_num < MAX_PORT_NUMBER);
    }

    /**
     * This method takes the provided IP address, and verifies its format.
     * Returns true or false to indicate validity of IP address.
     *
     * @param ip - string representing an IP address
     * @return boolean indicating whether provided IP address is valid or not
     */
    public static boolean isValidIP(String ip) {
        String[] parts = ip.split("\\.");
        boolean valid_ip = false;

        if (parts.length != 4) {
            return false;
        }

        for (String s : parts) {
            try {
                int octet = Integer.parseInt(s);

                if (0 <= octet && octet <= 255) {
                    valid_ip = true;
                    continue;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return valid_ip;
    }

    /**
     * Clears the console screen based on the operating system.
     * Uses appropriate system commands for Linux/Mac (clear) or Windows (cls).
     * If the OS is not recognized or clearing fails, prints 50 blank lines as fallback.
     */
    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("linux") || os.contains("mac")) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            printErrorMessage("Failed to clear the console");
        }
    }

    /**
     * Parses a string to an integer with error handling.
     *
     * @param text the string to be parsed as an integer
     * @return the parsed integer value, or -1 if parsing fails
     */
    public static int parseStringToInt(String text) {
        int num = -1;

        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            num = -1;
        }

        return num;
    }

    /**
     * Prints an error message to the console with "Error ::" prefix.
     *
     * @param msg the error message to display
     */
    public static void printErrorMessage(String msg) {
        System.out.println("Error :: " + msg);
    }
}
