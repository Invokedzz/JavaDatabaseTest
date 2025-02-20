package src.model.services.ProdServices;

import src.model.entities.ProdEntities.Product;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public interface ProductContract {

    void updateProduct (Connection connection, String name, Double price, Integer quantity, Integer productId);

    List <Product> displayProducts (Connection connection);

    Product obtainProductProperties (Connection connection, Integer productId);

    Integer obtainProductId (Connection connection, String productName);

    List <Product> searchForProducts (Connection connection, String product);

    void updateProductQuantity (Connection connection, Integer quantity, Integer productId);

    ImageIcon obtainProductImg (Connection connection, Integer productId);

}
