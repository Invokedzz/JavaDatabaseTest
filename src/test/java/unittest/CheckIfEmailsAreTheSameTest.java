package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.view.validations.user.page.CheckIfEmailsAreTheSame;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfEmailsAreTheSameTest {

    private static String oldEmail, newEmail;

    private Component parent;

    @Test
    @DisplayName("invalid email match")
    void invalidEmailMatch () {

        oldEmail = "mrfish902@gmail.com";

        newEmail = "mrfish902@gmail.com";

        assertFalse(CheckIfEmailsAreTheSame.areTheyTheSame(parent, oldEmail, newEmail));

    }

    @Test
    @DisplayName("Invalid email match")
    void validEmailMatch () {

        oldEmail = "mralligator902@gmail.com";

        newEmail = "mralligator903@gmail.com";

        assertTrue(CheckIfEmailsAreTheSame.areTheyTheSame(parent, oldEmail, newEmail));

    }

    @Test
    @DisplayName("Verify Email Auth (invalid case)")
    void invalidEmailAuthCase () {

        oldEmail = "";

        newEmail = "";

        assertFalse(CheckIfEmailsAreTheSame.areTheyTheSame(parent, oldEmail, newEmail));

    }


}