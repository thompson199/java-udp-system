package common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilityTest {

    @Test
    /**
     * This tests the isPortAvailable method for when it's provided with a valid port.
     */
    private void givenValidPort_testIsPortAvailable() {
        assertTrue(Utility.isPortAvailable(8080));
    }

    @Test
    /**
     * This tests the isPortAvailable method for when it's provided with invalid ports.
     */
    private void givenInvalidPort_testIsPortAvailable() {
        int p1 = -1;
        int p2 = 10_000;

        assertFalse(Utility.isPortAvailable(p1));
        assertFalse(Utility.isPortAvailable(p2));
    }

    @Test
    /**
     * This tests the isValidIP method for when it's provided with a valid IP address.
     */
    private void givenValidIP_testIsValidIP() {
        assertTrue(Utility.isValidIP("127.0.0.1"));
    }

    @Test
    /**
     * This tests the isValidIP method for when it's provided with invalid IP addresses.
     */
    private void givenInvalidIP_testIsValidIP() {
        String ip1 = "127.0.0.x";
        String ip2 = "127";
        String ip3 = "x";

        assertFalse(Utility.isValidIP(ip1));
        assertFalse(Utility.isValidIP(ip2));
        assertFalse(Utility.isValidIP(ip3));
    }

    @Test
    /**
     * This tests the parseStringToInt method for when it's provided with a valid string.
     */
    private void givenValidString_testParseStringToInt() {
        assertEquals(Utility.parseStringToInt("10"), 10);
    }

    @Test
    /**
     * This tests the parseStringToInt method for when it's provided with invalid strings.
     */
    private void givenInvalidString_testParseStringToInt() {
        String s1 = "x";
        String s2 = "?";
        String s3 = "null";
        String s4 = "1x1";

        String[] strings = { s1, s2, s3, s4 };
        for (String s : strings) {
            assertEquals(Utility.parseStringToInt(s), -1);
        }
    }
}
