package unittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.validation.user.register.RegisterEmailValidation;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
class RegisterEmailValidationTest {

    private Component parent;

    private JTextField email;

    @Test
    @DisplayName("Testing a valid email")
    void testValidEmail () {

        email = new JTextField("MrDuck@gmail.com",15);

        assertTrue(RegisterEmailValidation.invalidEmailMessage(parent, email));

    }

    @Test
    @DisplayName("Testing an invalid email")
    void testInvalidEmailMessage () {

        email = new JTextField("", 15);

        assertFalse(RegisterEmailValidation.invalidEmailMessage(parent, email));

    }

}