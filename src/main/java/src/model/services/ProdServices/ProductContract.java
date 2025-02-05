package src.model.services.ProdServices;

import src.model.entities.ProdEntities.Product;

import java.sql.Connection;

public interface ProductContract {

    void updateProduct (Connection connection, String name, Double price, Integer quantity, Integer productId);

    Product obtainProductProperties (Connection connection, Integer productId);

}
