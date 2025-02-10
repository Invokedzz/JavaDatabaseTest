package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.view.validations.user.register.CheckIfEmailAlreadyExists;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfEmailAlreadyExistsTest {

    private static String email;

    private Component parent;

    @Test
    @DisplayName("Case where stored email is the same as the email on the text field")
    void validCase () {

        email = "email1";

        List <String> notEmptyDB = new ArrayList<>();

        notEmptyDB.add("email1");

        notEmptyDB.add("email2");

        assertFalse(CheckIfEmailAlreadyExists.verifyEmailExistence(parent, email, notEmptyDB));

    }

    @Test
    @DisplayName("Case where the db is totally empty")
    void invalidCase () {

        email = "email2";

        List <String> emptyDB = new ArrayList<>();

        assertTrue(CheckIfEmailAlreadyExists.verifyEmailExistence(parent, email, emptyDB));

    }

}