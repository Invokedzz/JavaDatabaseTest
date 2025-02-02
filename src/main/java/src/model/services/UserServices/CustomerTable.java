package src.model.services.UserServices;

import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.DatabaseGeneralContract;
import src.db.*;
import src.security.PassHash;
import java.sql.*;

public class CustomerTable implements DatabaseGeneralContract, UserContract {

    private Customer customer;

    private Address address;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet set;

    public CustomerTable () {}

    public CustomerTable (Customer customer, Address address) {

        this.customer = customer;

        this.address = address;

    }

    @Override
    public void insert () {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"Customer\" " +
                            "(name, email, password, typeuser, cep, housenumber, neighbourhood, complement, city)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, customer.getName());

            String hashedPassword = PassHash.generateHash(customer.getPassword());

            statement.setString(2, customer.getEmail());

            statement.setString(3, hashedPassword);

            statement.setString(4, TypeUser.CUSTOMER.name());

            statement.setString(5, address.getCEP());

            statement.setString(6, address.getNumber());

            statement.setString(7, address.getLabel());

            statement.setString(8, address.getComplement());

            statement.setString(9, address.getCity());

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void display () {

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
    public void deleteComponent (Connection connection, Integer id) {

        try {

            statement = connection.prepareStatement(
                    "DELETE FROM \"User\".\"Customer\" WHERE id = ?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Customer deleted from the system!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void deleteAll () {

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
    public Customer obtainUserProperties (Connection connection, Integer id) {

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"User\".\"Customer\" WHERE id = ?"
            );

            statement.setInt(1, id);

            set = statement.executeQuery();

            if (set.next()) {

                String name = set.getString("name");

                String email = set.getString("email");

                String password = set.getString("password");

                return new Customer(name, email, password, TypeUser.CUSTOMER);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }

    @Override
    public Integer obtainUserId (Connection connect, String email) {

        try {

            statement = connect.prepareStatement(
                    "SELECT id FROM \"User\".\"Customer\" WHERE email = ?"
            );

            statement.setString(1, email);

            set = statement.executeQuery();

            if (set.next()) return set.getInt(1);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }

    @Override
    public String getStoredPassword (Connection connect, String email) {

        String storedPass = null;

        try {

            statement = connect.prepareStatement(
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
