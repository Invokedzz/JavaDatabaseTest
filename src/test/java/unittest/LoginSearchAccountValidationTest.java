package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.view.validations.user.login.LoginSearchAccountValidation;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginSearchAccountValidationTest {

    private Component parent;

    private static Integer userId;

    @Test
    @DisplayName("Testing a valid account (that really exists)")
    void validAccountCheck () {

        userId = 3;

        assertTrue(LoginSearchAccountValidation.messageIfAccountWasNotFound(parent, userId));

    }

    @Test
    @DisplayName("Testing an invalid account")
    void invalidAccountCheck () {

        userId = null;

        assertFalse(LoginSearchAccountValidation.messageIfAccountWasNotFound(parent, userId));

    }

}