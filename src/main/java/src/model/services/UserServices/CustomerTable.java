package src.model.services.UserServices;

import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.DatabaseGeneralContract;
import src.db.*;
import src.security.PassHash;
import java.sql.*;

public class CustomerTable implements DatabaseGeneralContract, UserContract {

    private Customer customer;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet set;

    public CustomerTable () {}

    public CustomerTable (Customer customer) {

        this.customer = customer;

    }

    @Override
    public void insert () {

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"Customer\" " +
                            "(name, email, password, typeuser)" +
                            " VALUES (?, ?, ?, ?)"
            );

            statement.setString(1, customer.getName() + " " + customer.getLastName());

            String hashedPassword = PassHash.generateHash(customer.getPassword());

            statement.setString(2, customer.getEmail());

            statement.setString(3, hashedPassword);

            statement.setString(4, TypeUser.CUSTOMER.name());

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

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "SELECT * FROM \"User\".\"Customer\""
            );

            set = statement.executeQuery();

            while (set.next()) {

                String name = set.getString("name");

                String email = set.getString("email");

                String type = set.getString("typeuser");

                System.out.println("NAME: " + name + " TYPE: " + type + " EMAIL: " + email);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void deleteComponent (Integer id) {

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "DELETE FROM \"User\".\"Customer\" WHERE id = ?"
            );

            statement.setInt(1, id);

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

        connection = null;

        statement = null;

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
    public void updateName (String name, Integer id) {

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "UPDATE \"User\".\"Customer\" set name = ? WHERE id = ?"
            );

            statement.setString(1, name);

            statement.setInt(2, id);

            statement.executeUpdate();

            System.out.println("The element was updated successfully!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

    @Override
    public String getStoredPassword (String email) {

        connection = null;

        statement = null;

        String storedPass = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "SELECT password FROM \"User\".\"Customer\" WHERE email = ?"
            );

            statement.setString(1, email);

            set = statement.executeQuery();

            if (set.next()) storedPass = set.getString("password");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return storedPass;

    }

    @Override
    public boolean checkUserById (Integer id) {

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM \"User\".\"Customer\" WHERE id = ?"
            );

            statement.setInt(1, id);

            set = statement.executeQuery();

            if (set.next()) {

                int count = set.getInt(1);

                return count > 0;

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeStatements(statement);

            DB.closeConnections(connection);

        }

        return false;

    }

}
