package src.validation.product.page;

import src.security.VerifyNumericalInputs;

import javax.swing.*;
import java.awt.*;

public class CheckNumericalInput {

    public static boolean isThisValueNumerical (Component parent, String value) {

        if (!VerifyNumericalInputs.numericalInput(value)) {

            JOptionPane.showMessageDialog(parent, "Enter a proper numerical value!");

            return false;

        }

        return true;

    }

}
