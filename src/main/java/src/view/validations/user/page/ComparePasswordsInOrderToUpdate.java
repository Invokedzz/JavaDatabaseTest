package src.view.validations.user.page;

import src.model.entities.UserEntities.Customer;
import src.security.PassHash;

import javax.swing.*;
import java.awt.*;

public class ComparePasswordsInOrderToUpdate {

    public static boolean comparePasswords (Component parent, String oldPassword, Customer customer) {

        if (!PassHash.checkChosenHash(oldPassword, customer.getPassword())) {

            JOptionPane.showMessageDialog(parent, "Wrong password! Try again!");

            return false;

        }

        JOptionPane.showMessageDialog(parent, "Success!");

        return true;

    }

}
