package src.model.services.ProdServices;

import src.model.entities.ProdEntities.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductContract {

    void updateProduct (Connection connection, String name, String price, String quantity, Integer productId);

    List <Product> displayProducts (Connection connection);

    Product obtainProductProperties (Connection connection, Integer productId);

    Integer obtainProductId (Connection connection, String productName);

}
