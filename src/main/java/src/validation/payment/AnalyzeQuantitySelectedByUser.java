package src.validation.payment;

import javax.swing.*;
import java.awt.*;

public class AnalyzeQuantitySelectedByUser {

    public static boolean isThisQuantityCorrect (Component parent, Integer quantity, Integer selectedQuantity) {

        if (selectedQuantity > quantity) {

            JOptionPane.showMessageDialog(parent, "Select a valid quantity, dude!");

            return false;

        }

        return true;

    }

}
