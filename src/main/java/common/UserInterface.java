package common;

import java.net.InetAddress;

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
        "Print Node Info",
        "Quit App",
    };

    public UserInterface() {}

    private static String repeatSpaces(int count) {
        return (" ").repeat(count);
    }

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

    private static void printLineToLeft(String content) {
        int content_len = content.length();

        // Right padding size = Max width - (Size of vertical borders, indentation and actual content)
        int padding_size = MAX_WIDTH - ((VERT_LINE.length() * 2) + INDENT.length() + content_len);
        String right_side = repeatSpaces(padding_size) + VERT_LINE;

        System.out.println(VERT_LINE + INDENT + content + right_side);
    }

    private static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            int pos = i + 1;
            printLineToLeft(pos + "." + options[i]);
        }
    }

    public static void printTitle() {
        System.out.println(HORIZ_LINE);
        System.out.println(UDP_SYSTEM_TITLE);
        System.out.println(HORIZ_LINE);
    }

    public static void printHeading(String content, boolean print_bottom_line) {
        System.out.println(HORIZ_LINE);
        printLineToCentre(content);

        if (print_bottom_line) {
            System.out.println(HORIZ_LINE);
        }
    }

    public static void printWelcomeMessage(int port, InetAddress ip) {
        printLineToCentre("Welcome to the Java UDP App!");
        printLineToCentre("Node started on port " + port + " with IP of " + ip);
    }

    public static void printMainMenuOptions() {
        System.out.println(HORIZ_LINE);
        printLineToCentre("Select an action by entering the corresponding number");

        System.out.println(HORIZ_LINE);
        printOptions(MAIN_MENU_OPTIONS);

        System.out.println(HORIZ_LINE);
        System.out.print("Choice: ");
    }

    public static void printNodeInfo(String type, int port, InetAddress ip) {
        System.out.println(HORIZ_LINE);
        printLineToCentre("Node Type: " + type);
        printLineToCentre("Port Number: " + port);
        printLineToCentre("IP Address: " + ip);
    }
}
