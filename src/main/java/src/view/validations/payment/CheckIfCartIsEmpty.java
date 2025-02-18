package src.view.validations.payment;

import src.model.entities.ProdEntities.Product;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class CheckIfCartIsEmpty {

    public static boolean isCartEmpty (Component parent, List <Product> productsInsideTheCart) {

        if (!productsInsideTheCart.isEmpty()) return true;

        JOptionPane.showMessageDialog(parent, "Your cart is empty! Try adding some products!");

        return false;

    }

}
