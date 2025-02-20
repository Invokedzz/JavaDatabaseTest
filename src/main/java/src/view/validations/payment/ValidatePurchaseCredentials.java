package src.view.validations.payment;

import src.model.entities.ProdEntities.Purchases;
import src.validation.CheckPurchases;

import javax.swing.*;
import java.awt.*;

public class ValidatePurchaseCredentials {

    public static boolean areTheCredentialsValid (Component parent, Purchases purchases) {

        CheckPurchases checkPurchases = new CheckPurchases();

        if (!checkPurchases.test(purchases)) {

            JOptionPane.showMessageDialog(parent, "Something went wrong with your purchase! Try again!");

            return false;

        }

        return true;

    }

}
