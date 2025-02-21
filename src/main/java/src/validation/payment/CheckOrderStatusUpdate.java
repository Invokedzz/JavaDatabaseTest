package src.validation.payment;

import src.validation.components.CheckPurchasesStatus;

import javax.swing.*;
import java.awt.*;

public class CheckOrderStatusUpdate {

    public static boolean orderUpdate (Component parent, String status) {

        CheckPurchasesStatus purchasesStatus = new CheckPurchasesStatus();

        if (!purchasesStatus.test(status)) {

            JOptionPane.showMessageDialog(parent, "Enter a valid order status!");

            return false;

        }

        return true;

    }

}
