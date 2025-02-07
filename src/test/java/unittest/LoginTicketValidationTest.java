package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import src.view.validations.user.login.LoginTicketValidation;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginTicketValidationTest {

    private static Component parent;

    private static String sentTicket;

    private static String actualTicket;

    @Test
    @DisplayName("Invalid result")
    void invalidTicketMatching () {

        sentTicket = "castlevania123";

        actualTicket = "123castlevania";

        assertFalse(LoginTicketValidation.checkSentTicket(parent, sentTicket, actualTicket));

    }

    @Test
    @DisplayName("Valid result")
    void validTicketMatching () {

        sentTicket = "castlevania123";

        actualTicket = "castlevania123";

        assertTrue(LoginTicketValidation.checkSentTicket(parent, sentTicket, actualTicket));

    }

}