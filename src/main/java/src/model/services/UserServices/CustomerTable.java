package src.model.services.UserServices;

import src.model.entities.UserEntities.Customer;

import src.model.services.DatabaseContract;

import src.model.entities.UserEntities.Admin;

import src.db.DB;

import src.db.DbException;

import java.sql.*;

import java.util.Scanner;

public class CustomerTable implements DatabaseContract {

    private Customer customer;

    public CustomerTable () {}

    public CustomerTable (Customer customer) {

        this.customer = customer;

    }

    @Override
    public void insert () {

    }

    @Override
    public void display () {

    }

    @Override
    public void deleteComponent () {

    }

    @Override
    public void deleteAll () {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "DELETE FROM \"User\".\"Customer\""
            );

            statement.executeUpdate();

            System.out.println("All the tables were deleted successfully!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }


}
