package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.security.PassHash;

import static org.junit.jupiter.api.Assertions.*;

class PassHashTest {

    private static String password;

    private static String hashedPassword;

    @Test
    @DisplayName("Testing a password that matches the hash")
    void testValidPasswordMatching () {

        password = "password";

        hashedPassword = PassHash.generateHash(password);

        assertTrue(PassHash.checkChosenHash(password, hashedPassword));

    }

    @Test
    @DisplayName("Testing a password that doesn't match the hash")
    void testInvalidPasswordMatching () {

        password = "RandomRandomRandom";

        hashedPassword = PassHash.generateHash(password + "123");

        assertFalse(PassHash.checkChosenHash(password, hashedPassword));

    }

}