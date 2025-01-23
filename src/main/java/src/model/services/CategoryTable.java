package src.model.services;

import src.model.entities.Category;
import src.model.entities.Product;

import src.db.DB;

import src.db.DbException;

import java.sql.*;

public class CategoryTable implements DatabaseContract {

    @Override
    public void insert (Product product) {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "INSERT INTO public.\"category\" " +
                            "(name, tier) "
                            + "VALUES (?, ?)"

            );

            statement.setString(1, product.getCategory().getName());

            statement.setInt(2, product.getCategory().getTier());

            int rows = statement.executeUpdate();

            System.out.println("Rows affected: " + rows);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

    @Override
    public void display () {

    }

}
