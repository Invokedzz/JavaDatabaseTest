package src.validation.user.forms;

import src.model.entities.UserEntities.User;
import src.security.PassHash;

import javax.swing.*;
import java.awt.*;

public class ComparePasswordsInOrderToUpdate {

    public static boolean comparePasswords (Component parent, String oldPassword, User customer) {

        if (!PassHash.checkChosenHash(oldPassword, customer.getPassword())) {

            JOptionPane.showMessageDialog(parent, "Wrong password! Try again!");

            return false;

        }

        return true;

    }

}
