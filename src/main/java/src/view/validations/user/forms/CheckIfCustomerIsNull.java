package src.view.validations.user.forms;

import src.model.entities.UserEntities.User;

import javax.swing.*;
import java.awt.*;

public class CheckIfCustomerIsNull {

    public static void isThisCustomerInsideTheSystem (Component parent, User user) {

        if (user == null) {

            JOptionPane.showMessageDialog(parent, "Something went wrong!");

            System.exit(0);

        }

    }

}
