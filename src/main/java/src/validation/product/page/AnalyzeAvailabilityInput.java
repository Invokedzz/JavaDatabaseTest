package src.validation.product.page;

import src.model.enums.ProductAvailability;

import javax.swing.*;
import java.awt.*;

public class AnalyzeAvailabilityInput {

    public static boolean lookForAvailabilityText (Component parent, String text) {

        if (text.equalsIgnoreCase(ProductAvailability.IN_STOCK.name()) ||
                text.equalsIgnoreCase(ProductAvailability.OUT_OF_STOCK.name())) return true;

        JOptionPane.showMessageDialog(parent, "Enter a valid STOCK value!");

        return false;

    }

}
