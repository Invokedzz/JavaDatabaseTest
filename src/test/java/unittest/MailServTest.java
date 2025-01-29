package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.security.MailServ;

import static org.junit.jupiter.api.Assertions.*;

class MailServTest {

    @Test
    @DisplayName("Checking a valid email")
    void testValidEmail () {

        String validEmail = "mrbeans903@gmail.com";

        assertTrue(MailServ.checkMail(validEmail));

    }

    @Test
    @DisplayName("Checking a random string")
    void testARandomString () {

        String randomString = "Gan Is the best character In Smash Bros";

        assertFalse(MailServ.checkMail(randomString));

    }

    @Test
    @DisplayName("Checking an invalid email")
    void testInvalidEmail () {

        String invalidEmail = "PaulWalkMountain@.com";

        assertFalse(MailServ.checkMail(invalidEmail));

    }

}