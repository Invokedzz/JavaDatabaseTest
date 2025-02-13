package src.model.services.UserServices;

import src.model.entities.UserEntities.Address;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.model.services.DatabaseGeneralContract;
import src.db.*;
import src.security.PassHash;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerTable implements DatabaseGeneralContract, UserContract, CustomerContract {

    private Customer customer;

    private Address address;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet set;

    public CustomerTable() {
    }

    public CustomerTable(Customer customer, Address address) {

        this.customer = customer;

        this.address = address;

    }

    @Override
    public void insert() {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"Customer\" " +
                            "(name, email, password, typeuser, cep, neighbourhood, complement, housenumber, city)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, customer.getName());

            String hashedPassword = PassHash.generateHash(customer.getPassword());

            statement.setString(2, customer.getEmail());

            statement.setString(3, hashedPassword);

            statement.setString(4, TypeUser.CUSTOMER.name());

            statement.setString(5, address.getCEP());

            statement.setString(6, address.getLabel());

            statement.setString(7, address.getComplement());

            statement.setString(8, address.getNumber());

            statement.setString(9, address.getCity());

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void deleteComponent(Connection connection, Integer id) {

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
    public Customer obtainUserProperties(Connection connection, Integer id) {

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
    public Integer obtainUserId(Connection connect, String email) {

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
    public String getStoredPassword(Connection connect, String email) {

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

    public void updateUser(Connection connection, String name, String email, String password, Integer id) {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"User\".\"Customer\" " +
                            "SET name = ?, email = ?, password = ?" +
                            " WHERE id = ?"
            );

            statement.setString(1, name);

            statement.setString(2, email);

            statement.setString(3, password);

            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public List<String> obtainStoredEmails(Connection connection) {

        List<String> emails = new ArrayList<>();

        try {

            statement = connection.prepareStatement(
                    "SELECT email FROM \"User\".\"Customer\""
            );

            set = statement.executeQuery();

            while (set.next()) {

                emails.add(set.getString("email"));

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return emails;

    }

    @Override
    public List <String> obtainStoredCpf(Connection connection) {

        List<String> storedCpf = new ArrayList<>();

        try {

            statement = connection.prepareStatement(
                    "SELECT cep FROM \"User\".\"Customer\""
            );

            set = statement.executeQuery();

            while (set.next()) {

                storedCpf.add(set.getString("cep"));

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return storedCpf;

    }

    @Override
    public Address obtainAddressProperties(Connection connection, Integer userId) {

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"User\".\"Customer\" WHERE id = ?"
            );

            statement.setInt(1, userId);

            set = statement.executeQuery();

            if (set.next()) {

                String cep = set.getString("cep");

                String housenumber = set.getString("housenumber");

                String neighbourhood = set.getString("neighbourhood");

                String complement = set.getString("complement");

                String city = set.getString("city");

                return new Address(cep, housenumber, complement, neighbourhood, city);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }

}
