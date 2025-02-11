package src.view.validations.product.page;

import javax.swing.*;
import java.awt.*;

public class CheckIfProductsHaveTheSameName {

    public static boolean verifyProductsName (Component parent, String firstName, String secondName) {

        if (firstName.equals(secondName)) {

            JOptionPane.showMessageDialog(parent, "This product already exists!");

            return false;

        }

        return true;

    }

}
