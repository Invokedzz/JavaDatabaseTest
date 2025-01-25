package src.model.services.ProdServices;

import src.db.DB;

import src.db.DbException;

import src.model.entities.ProdEntities.Product;

import src.model.services.DatabaseContract;

import java.sql.*;

public class ProductTable implements DatabaseContract {

    private Product product;

    public ProductTable () {}

    public ProductTable (Product product) {

        this.product = product;

    }

    // Product: name, price, id_category
    // id_category: 1 -> Electronics
    // 2 -> Tools, 3 -> Clothes

    @Override
    public void insert () {

        Connection connection;

        PreparedStatement statement;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "INSERT INTO \"Stock\".\"Product\" "
                            + "(name, price, quantity, availability, id_category) "
                            + "VALUES (?, ?, ?, ?, ?)"

            );

            statement.setString(1, product.getName());

            statement.setDouble(2, product.getPrice());

            statement.setInt(3, product.getQuantity());

            statement.setString(4, product.getAvailability().name());

            statement.setInt(5, product.getCategory().getId());

            int rows = statement.executeUpdate();

            System.out.printf("Rows Affected: %s", rows);

        } catch ( SQLException exception ) {

            throw new DbException( exception.getMessage() );

        }

    }

    @Override
    public void display () {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "SELECT * FROM \"Stock\".\"Product\""

            );

            ResultSet set = statement.executeQuery();

            while (set.next()) {

                String name = set.getString("name");

                double price = set.getDouble("price");

                int id_category = set.getInt("id_category");

                System.out.println(name + " " + price + " " + id_category);

            }

        } catch (SQLException exception) {

            System.out.println(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

}
