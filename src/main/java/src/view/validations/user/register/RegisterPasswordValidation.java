package src.view.validations.user.register;

import src.security.PassInput;

import javax.swing.*;
import java.awt.*;

public class RegisterPasswordValidation {

    public static boolean invalidPasswordMatchMessage (Component parent, JPasswordField passwordField, JPasswordField repeatPasswordField) {

        if (!new String(passwordField.getPassword()).equals(new String(repeatPasswordField.getPassword()))) {

            JOptionPane.showMessageDialog(parent,
                    "Passwords do NOT match!");

            return false;
        }

        return true;

    }

    public static boolean invalidPasswordMessage (Component parent, JPasswordField passwordField) {

        if (!PassInput.verifyPasswordText(new String(passwordField.getPassword()))) {

            JOptionPane.showMessageDialog(parent,
                    "Your password must contain letters and numbers!");

            return false;
        }

        return true;

    }

}
