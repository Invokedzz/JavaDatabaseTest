package src.view.validations.product.page;

import src.model.entities.ProdEntities.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CheckIfProductIsInsideTheCart {

    public static boolean lookInsideTheCart (Component parent, List <Product> cart, Product randomProduct) {

        for (Product product : cart) {

            if (product.getName().equals(randomProduct.getName())) {

                JOptionPane.showMessageDialog(parent, "This product is already in the cart!");

                return false;

            }

        }

        return true;

    }

}
