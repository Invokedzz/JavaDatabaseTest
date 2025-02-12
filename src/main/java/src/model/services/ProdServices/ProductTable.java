package src.model.services.ProdServices;

import src.db.*;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.model.services.DatabaseGeneralContract;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTable implements DatabaseGeneralContract, ProductContract {

    private Product product;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet set;

    public ProductTable () {}

    public ProductTable (Product product) {

        this.product = product;

    }

    // Product: name, price, id_category
    // id_category: 1 -> Electronics
    // 2 -> Tools, 3 -> Clothes

    @Override
    public void insert () {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "INSERT INTO \"Stock\".\"Product\" "
                            + "(name, price, quantity, availability, id_category) "
                            + "VALUES (?, ?, ?, ?, ?)"

            );

            statement.setString(1, product.getName());

            statement.setDouble(2, Double.parseDouble(product.getPrice()));

            statement.setInt(3, Integer.parseInt(product.getQuantity()));

            statement.setString(4, product.getAvailability().name());

            statement.setInt(5, product.getCategory().getId());

            statement.executeUpdate();

        } catch ( SQLException exception ) {

            throw new DbException( exception.getMessage() );

        }

    }

    @Override
    public void display() {

    }

    @Override
    public List <Product> displayProducts (Connection connection) {

        List <Product> products = new ArrayList<>();

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "SELECT * FROM \"Stock\".\"Product\""

            );

            set = statement.executeQuery();

            while (set.next()) {

                String name = set.getString("name");

                String price = set.getString("price");

                String quantity = set.getString("quantity");

                ProductAvailability availability = ProductAvailability.valueOf(set.getString("availability"));

                products.add(new Product(name, price, quantity, availability));

            }

        } catch (SQLException exception) {

            System.out.println(exception.getMessage());

        }

        return products;

    }

    @Override
    public void deleteComponent (Connection connection, Integer id) {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "DELETE FROM \"Stock\".\"Product\" WHERE id = ?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    // WARNING: this function deletes the ENTIRE table
    @Override
    public void deleteAll () {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "DELETE FROM \"Stock\".\"Product\""
            );

            statement.executeUpdate();

            System.out.println("All the products were successfully deleted!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeStatements(statement);

            DB.closeConnections(connection);

        }

    }

    @Override
    public void updateProduct (Connection connection, String name, Double price, Integer quantity, Integer productId) {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"Stock\".\"Product\" SET name = ?, price = ?, quantity = ? WHERE id = ?"
            );

            statement.setString(1, name);

            statement.setDouble(2, price);

            statement.setInt(3, quantity);

            statement.setInt(4, productId);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public Product obtainProductProperties(Connection connection, Integer productId) {

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"Stock\".\"Product\" WHERE id = ?"
            );

            statement.setInt(1, productId);

            ResultSet set = statement.executeQuery();

            if (set.next()) {

                String name = set.getString("name");

                String price = set.getString("price");

                String quantity = set.getString("quantity");

                ProductAvailability availability = ProductAvailability.valueOf(set.getString("availability"));

                return new Product(name, price, quantity, availability);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }

    @Override
    public Integer obtainProductId(Connection connection, String productName) {

        try {

            statement = connection.prepareStatement(
                    "SELECT id FROM \"Stock\".\"Product\" WHERE name = ?"
            );

            statement.setString(1, productName);

            set = statement.executeQuery();

            if (set.next()) return set.getInt("id");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }

    @Override
    public List <Product> searchForProducts(Connection connection, String product) {

        List <Product> queryProducts = new ArrayList<>();

        try {

            statement = connection.prepareStatement("SELECT * FROM \"Stock\".\"Product\" WHERE name ILIKE ?");

            statement.setString(1, "%" + product + "%");

            set = statement.executeQuery();

            while (set.next()) {

                String name = set.getString("name");

                String price = set.getString("price");

                String quantity = set.getString("quantity");

                ProductAvailability availability = ProductAvailability.valueOf(set.getString("availability"));

                Product productFound = new Product(name, price, quantity, availability);

                queryProducts.add(productFound);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return queryProducts;

    }

}
