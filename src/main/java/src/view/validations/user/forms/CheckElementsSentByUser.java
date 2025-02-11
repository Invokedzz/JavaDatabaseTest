package src.view.validations.user.forms;

import src.model.entities.UserEntities.User;
import src.validation.CheckElementsInOrderToUpdate;

import javax.swing.*;
import java.awt.*;

public class CheckElementsSentByUser {

    public static boolean verifyElements (Component parent, CheckElementsInOrderToUpdate elements, User user) {

        if (!elements.test(user)) {

            JOptionPane.showMessageDialog(parent, "Something went wrong! Check your inputs!");

            return false;

        }

        return true;

    }

}
