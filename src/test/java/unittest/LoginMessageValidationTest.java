package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.security.PassHash;
import src.view.validations.user.login.LoginMessageValidation;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginMessageValidationTest {

    // Component parent, String email, String password, String storedPass

    private Component parent;

    private static String email;

    private static String password;

    private static String storedPassword;

    @Test
    @DisplayName("Testing valid components")
    void validComponents () {

        email = "paul@gmail.com";

        password = "castlevania123";

        storedPassword = PassHash.generateHash(password);

        assertTrue(LoginMessageValidation.invalidLoginMessage(parent, email,
                password, storedPassword));

    }

    @Test
    @DisplayName("Testing an invalid email")
    void invalidEmail () {

        email = "";

        password = "castlevania123";

        storedPassword = PassHash.generateHash(password);

        assertFalse(LoginMessageValidation.invalidLoginMessage(parent, email,
                password, storedPassword));

    }

    @Test
    @DisplayName("Testing passwords that don't match")
    void passwordsThatDontMatch () {

        email = "paul@gmail.com";

        password = "castlevania123";

        storedPassword = PassHash.generateHash(password);

        assertFalse(LoginMessageValidation.invalidLoginMessage(parent, email,
                "ILoveDino123", storedPassword));

    }

}