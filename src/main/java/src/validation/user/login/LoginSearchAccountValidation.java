package src.validation.user.login;

import javax.swing.*;
import java.awt.*;

public class LoginSearchAccountValidation {

    public static boolean messageIfAccountWasNotFound (Component parent, Integer userId) {

        if (userId == null) {

            JOptionPane.showMessageDialog(parent, "Account not found!");

            return false;

        }

        return true;

    }

}
