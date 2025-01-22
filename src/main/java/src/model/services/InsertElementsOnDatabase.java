package src.model.services;

import src.db.DB;

import src.db.DbException;

import src.model.entities.Product;

import java.sql.*;

public class InsertElementsOnDatabase implements InsertContract {

    @Override
    public void insert (Product product) {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO public.\"Product\" "
                            + "(name, price) "
                            + "VALUES (?, ?)" );

            statement.setString(1, product.getName());

            statement.setDouble(2, product.getPrice());

            int rows = statement.executeUpdate();

            System.out.printf("Rows Affected: %s", rows);

        } catch ( SQLException exception ) {

            throw new DbException( exception.getMessage() );

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }


}
