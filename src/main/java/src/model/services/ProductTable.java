package src.model.services;

import src.db.DB;

import src.db.DbException;

import src.model.entities.Product;

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

                    "INSERT INTO public.\"Product\" "
                            + "(name, price, id_category) "
                            + "VALUES (?, ?, ?)"

            );

            statement.setString(1, product.getName());

            statement.setDouble(2, product.getPrice());

            statement.setInt(3, product.getCategory().getId());

            int rows = statement.executeUpdate();

            System.out.printf("Rows Affected: %s", rows);

        } catch ( SQLException exception ) {

            throw new DbException( exception.getMessage() );

        }

    }

    @Override
    public void display () {

    }

}
