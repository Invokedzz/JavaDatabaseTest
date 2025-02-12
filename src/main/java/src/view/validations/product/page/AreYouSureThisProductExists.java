package src.view.validations.product.page;

import src.model.entities.ProdEntities.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AreYouSureThisProductExists {

    public static boolean searchForProductInAList (Component parent, List <Product> productList) {

        if (productList.isEmpty()) {

            JOptionPane.showMessageDialog(parent, "Product not found!");

            return false;

        }

        return true;

    }

}
