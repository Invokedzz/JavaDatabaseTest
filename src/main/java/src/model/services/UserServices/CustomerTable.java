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

        connection = null;

        statement = null;

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
    public void checkUserById (Integer id) {

        connection = null;

    }

}
