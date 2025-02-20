package src.view.validations.payment;

import javax.swing.*;
import java.awt.*;

public class CheckQtySelectedByUser {

    public static boolean isQtyValidByAnyChance (Component parent, int chosenQty) {

        if (chosenQty <= 0) {

            JOptionPane.showMessageDialog(parent, "Enter a valid quantity!");

            return false;

        }

        return true;

    }

}
