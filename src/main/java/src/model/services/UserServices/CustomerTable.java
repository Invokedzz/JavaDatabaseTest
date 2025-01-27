package src.model.services.UserServices;

import src.model.entities.UserEntities.Customer;

import src.model.enums.TypeUser;
import src.model.services.DatabaseGeneralContract;

import src.db.DB;

import src.db.DbException;
import src.security.PassHash;

import java.sql.*;

import java.util.Scanner;

public class CustomerTable implements DatabaseGeneralContract, UserContract {

    Scanner sc = new Scanner(System.in);

    private Customer customer;

    public CustomerTable () {}

    public CustomerTable (Customer customer) {

        this.customer = customer;

    }

    @Override
    public void insert () {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"Customer\" " +
                            "(name, password, typeuser)" +
                            " VALUES (?, ?, ?)"
            );

            statement.setString(1, customer.getName() + " " + customer.getLastName());

            String hashedPassword = PassHash.generateHash(customer.getPassword());

            statement.setString(2, hashedPassword);

            statement.setString(3, TypeUser.CUSTOMER.name());

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

    @Override
    public void display () {

        Connection connection;

        PreparedStatement statement;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "SELECT * FROM \"User\".\"Customer\""
            );

            ResultSet set = statement.executeQuery();

            while (set.next()) {

                String name = set.getString("name");

                String type = set.getString("typeuser");

                System.out.println("NAME: " + name + " TYPE: " + type);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void deleteComponent () {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "DELETE FROM \"User\".\"Customer\" WHERE id = ?"
            );

            statement.setInt(1, sc.nextInt());

            statement.executeUpdate();

            System.out.println("Customer deleted from the system!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }


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

    @Override
    public void updateName() {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "UPDATE \"User\".\"Customer\" set name = ? WHERE id = ?"
            );

            statement.setString(1, "Asuka");

            statement.setInt(2, sc.nextInt());

            statement.executeUpdate();

            System.out.println("The element was updated successfully!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

}
