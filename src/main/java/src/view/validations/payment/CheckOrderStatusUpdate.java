package src.view.validations.payment;

import src.model.entities.ProdEntities.Purchases;
import src.validation.CheckPurchasesStatus;

import javax.swing.*;
import java.awt.*;

public class CheckOrderStatusUpdate {

    public static boolean orderUpdate (Component parent, Purchases purchases) {

        CheckPurchasesStatus purchasesStatus = new CheckPurchasesStatus();

        if (!purchasesStatus.test(purchases)) {

            JOptionPane.showMessageDialog(parent, "Enter a valid order status!");

            return false;

        }

        return true;

    }

}
