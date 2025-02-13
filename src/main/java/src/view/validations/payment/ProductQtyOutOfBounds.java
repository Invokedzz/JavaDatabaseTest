package src.view.validations.payment;

import javax.swing.*;
import java.awt.*;

public class ProductQtyOutOfBounds {

    public static boolean checkProductQty (Component parent, Integer quantity) {

        if (quantity == 0) {

            JOptionPane.showMessageDialog(parent, "This product is out of stock. Try another one!");

            return false;

        }

        return true;

    }

}
