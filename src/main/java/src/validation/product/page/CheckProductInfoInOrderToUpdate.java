package src.validation.product.page;

import src.model.entities.ProdEntities.Product;
import src.validation.components.CheckProducts;

import javax.swing.*;
import java.awt.*;

public class CheckProductInfoInOrderToUpdate {

    public static boolean validateProduct (Component parent, Product product) {

        CheckProducts checkProducts = new CheckProducts();

        if (!checkProducts.test(product)) {

            JOptionPane.showMessageDialog(parent, "Enter a valid product!");

            return false;

        }

        return true;

    }

}
