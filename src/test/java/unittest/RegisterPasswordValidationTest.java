package unittest;

import org.junit.jupiter.api.Test;
import src.validation.user.register.RegisterPasswordValidation;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RegisterPasswordValidationTest {

    private Component parent;

    private JPasswordField passwordField, repeatPasswordField;

    @Test
    void validPasswordMatch () {

        passwordField = new JPasswordField("castlevania123", 15);

        repeatPasswordField = new JPasswordField("castlevania123", 15);

        assertTrue(RegisterPasswordValidation.invalidPasswordMatchMessage(parent, passwordField, repeatPasswordField));

    }

    @Test
    void invalidPasswordMatchMessage () {

        passwordField = new JPasswordField("castlevania123", 15);

        repeatPasswordField = new JPasswordField("castlevania1234", 15);

        assertFalse(RegisterPasswordValidation.invalidPasswordMatchMessage(parent, passwordField, repeatPasswordField));

    }

    @Test
    void validPassword () {

        passwordField = new JPasswordField("castlevania123", 15);

        assertTrue(RegisterPasswordValidation.invalidPasswordMessage(parent, passwordField));

    }

    @Test
    void invalidPasswordMessage () {

        passwordField = new JPasswordField("castlevania", 15);

        assertFalse(RegisterPasswordValidation.invalidPasswordMessage(parent, passwordField));

    }
}