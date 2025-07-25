package src.common;

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
        if (port_num < MIN_PORT_NUMBER || port_num > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Error :: Invalid port given :: " + port_num);
        } else {
            return true;
        }
    }

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("linux") || os.contains("mac")) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                for (int i = 0; i < 51; i++) {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            printErrorMessage("Failed to clear the console");
        }
    }

    public static int parseStringToInt(String text) {
        int num = -1;

        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            num = -1;
        }

        return num;
    }

    public static void printErrorMessage(String msg) {
        System.out.println("Error :: " + msg);
    }
}
