package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.security.PassInput;

import static org.junit.jupiter.api.Assertions.*;

class PassInputTest {

    // a valid password must contain only letters and numbers!

    private static String randomPassword;

    @Test
    @DisplayName("Testing a valid password sent by the user")
    void validPassword () {

        randomPassword = "Beloveds123";

        assertTrue(PassInput.verifyPasswordText(randomPassword));

    }

    @Test
    @DisplayName("Testing a password that contains only letters")
    void invalidPasswordThatContainsOnlyLetters () {

        randomPassword = "LuanGameplay";

        assertFalse(PassInput.verifyPasswordText(randomPassword));

    }

    @Test
    @DisplayName("Testing a password that contains only numbers")
    void invalidPasswordThatContainsOnlyNumbers () {

        randomPassword = "123456789";

        assertFalse(PassInput.verifyPasswordText(randomPassword));

    }

}