package src.validation.user.login;

import src.security.LoginServ;

import javax.swing.*;
import java.awt.*;

public class LoginMessageValidation {

    private static boolean isLoginValid(String email, String password, String storedPassword) {

        return LoginServ.isLoginValid(email, password, storedPassword);

    }

    public static boolean invalidLoginMessage (Component parent, String email, String password, String storedPass) {

        if (!isLoginValid(email, password, storedPass)) {

            JOptionPane.showMessageDialog(parent, "Something went wrong. Please, try again!");

            return false;

        }

        return true;

    }

}
