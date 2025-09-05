package common;

import java.net.InetAddress;
import java.util.LinkedList;

public class UserInterface {

    private static final int MAX_WIDTH = 82;

    private static final String HORIZ_LINE = ("=").repeat(MAX_WIDTH);
    private static final String VERT_LINE = "||";
    private static final String INDENT = repeatSpaces(12);

    private static final String UDP_SYSTEM_TITLE =
        "██╗   ██╗██████╗ ██████╗     ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗\n" +
        "██║   ██║██╔══██╗██╔══██╗    ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║\n" +
        "██║   ██║██║  ██║██████╔╝    ███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║\n" +
        "██║   ██║██║  ██║██╔═══╝     ╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║\n" +
        "╚██████╔╝██████╔╝██║         ███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║\n" +
        " ╚═════╝ ╚═════╝ ╚═╝         ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝";

    private static final String[] MAIN_MENU_OPTIONS = {
        "Ping Other Node",
        "View Message History",
        "Print Node Info",
        "Quit App",
    };

    /**
     * Default constructor for UserInterface.
     */
    public UserInterface() {}

    /**
     * Creates a string containing the specified number of space characters.
     *
     * @param count The number of spaces to repeat
     * @return A string containing the specified number of spaces
     */
    private static String repeatSpaces(int count) {
        return (" ").repeat(count);
    }

    /**
     * Prints a line of text centered within the defined maximum width boundaries.
     * The text is padded with spaces on both sides and enclosed with vertical line borders.
     *
     * @param content The text content to be printed in the center of the line
     */
    private static void printLineToCentre(String content) {
        int content_len = content.length();
        int total_padding = (content_len < MAX_WIDTH) ? MAX_WIDTH - content_len : 0;

        int count = (total_padding / 2) - VERT_LINE.length();
        String left_side = VERT_LINE + repeatSpaces(count);

        if (content_len % 2 != 0) {
            count += 1;
        }

        String right_side = repeatSpaces(count) + VERT_LINE;

        System.out.println(left_side + content + right_side);
    }

    /**
     * Prints a line of text aligned to the left with proper indentation and padding.
     * The text is indented from the left border and padded to maintain consistent line width.
     *
     * @param content The text content to be printed on the left side with indentation
     */
    private static void printLineToLeft(String content) {
        int content_len = content.length();

        // Right padding size = Max width - (Size of vertical borders, indentation and actual content)
        int padding_size = MAX_WIDTH - ((VERT_LINE.length() * 2) + INDENT.length() + content_len);
        String right_side = repeatSpaces(padding_size) + VERT_LINE;

        System.out.println(VERT_LINE + INDENT + content + right_side);
    }

    /**
     * Prints a numbered list of menu options.
     * Each option is displayed with a number prefix (starting from 1) and left-aligned formatting.
     *
     * @param options An array of strings representing the menu options to display
     */
    private static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            int pos = i + 1;
            printLineToLeft(pos + "." + options[i]);
        }
    }

    /**
     * Prints the items contained within the provided LinkedList.
     * If the list is empty, simply prints a line with no content.
     *
     * @param list - A LinkedList of Strings that will be printed
     */
    public static void printList(LinkedList<String> list) {
        System.out.println(HORIZ_LINE);

        // If the list is empty, just print line with no content
        // This creates a separation between the horizontal (=) lines above/below
        if (list.size() == 0) {
            printLineToCentre("");
            return;
        }

        for (String str : list) {
            printLineToLeft(str);
        }
    }

    /**
     * Prints the main title banner for the UDP System application.
     * Displays the ASCII art title surrounded by horizontal border lines.
     */
    public static void printTitle() {
        System.out.println(HORIZ_LINE);
        System.out.println(UDP_SYSTEM_TITLE);
        System.out.println(HORIZ_LINE);
    }

    /**
     * Prints a formatted heading with optional bottom border line.
     * The heading text is centered and surrounded by horizontal lines.
     *
     * @param content The heading text to display
     * @param print_bottom_line Whether to print a horizontal line below the heading
     */
    public static void printHeading(String content, boolean print_bottom_line) {
        System.out.println(HORIZ_LINE);
        printLineToCentre(content);

        if (print_bottom_line) {
            System.out.println(HORIZ_LINE);
        }
    }

    /**
     * Prints a welcome message displaying the application name and node connection details.
     * Shows the port number and IP address the node is running on.
     *
     * @param port The port number the node is listening on
     * @param ip The IP address of the node
     */
    public static void printWelcomeMessage(int port, InetAddress ip) {
        printLineToCentre("Welcome to the Java UDP App!");
        printLineToCentre("Node started on port " + port + " with IP of " + ip);
    }

    /**
     * Displays the main menu with available options and prompts for user input.
     * Shows numbered menu options and waits for user selection.
     */
    public static void printMainMenuOptions() {
        System.out.println(HORIZ_LINE);
        printLineToCentre("Select an action by entering the corresponding number");

        System.out.println(HORIZ_LINE);
        printOptions(MAIN_MENU_OPTIONS);

        System.out.println(HORIZ_LINE);
        System.out.print("Choice: ");
    }

    /**
     * Displays detailed information about the current node.
     * Shows the node type, port number, and IP address in a formatted layout.
     *
     * @param type The type/role of the node (e.g., "Client", "Server")
     * @param port The port number the node is using
     * @param ip The IP address of the node
     */
    public static void printNodeInfo(String type, int port, InetAddress ip) {
        System.out.println(HORIZ_LINE);
        printLineToCentre("Node Type: " + type);
        printLineToCentre("Port Number: " + port);
        printLineToCentre("IP Address: " + ip);
    }
}
