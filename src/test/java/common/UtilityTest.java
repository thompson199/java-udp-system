package common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilityTest {

    @Test
    private void testIsPortAvailable() {
        boolean res = Utility.isPortAvailable(8080);

        assertTrue(res);
    }
}
