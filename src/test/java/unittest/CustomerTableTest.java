package unittest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.db.DB;
import src.db.DbException;
import src.model.entities.UserEntities.Customer;
import src.model.enums.TypeUser;
import src.validation.components.CheckCustomers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTableTest {

    private static Connection connection;

    private static PreparedStatement statement;

    private static ResultSet set;

    private static Customer customer;

    private static CheckCustomers checkCustomers;

    @BeforeEach
    void setUp() {

        customer = new Customer("John Greed", "johngreed901@gmail.com",
                "ILoveMoney", TypeUser.CUSTOMER);

        checkCustomers = new CheckCustomers();

        connection = DB.getConnection();

    }

    @AfterAll
    static void tearDown() {

        DB.closeConnections(connection);

        DB.closeStatements(statement);

        DB.closeResult(set);

    }

    @Test
    void insert() {

        try {

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"CustomerTest\" " +
                            "(name, email, password, typeuser)" +
                            " VALUES (?,?,?,?)"
            );

            statement.setString(1, customer.getName());

            statement.setString(2, customer.getEmail());

            statement.setString(3, customer.getPassword());

            statement.setString(4, customer.getTypeUser().name());

            if (checkCustomers.test(customer)) {

                int rowsAffected = statement.executeUpdate();

                assertEquals(1, rowsAffected);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    void display() {

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"User\".\"CustomerTest\""
            );

            set = statement.executeQuery();

            assertTrue(set.next());

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    void updateName() {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"User\".\"CustomerTest\" set name = ? WHERE id = ?"
            );

            statement.setString(1, "Luke Hate");

            statement.setInt(2, 1);

            int rowsAffected = statement.executeUpdate();

            assertEquals(1, rowsAffected);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    void getStoredPassword() {

        try {

            statement = connection.prepareStatement(
                    "SELECT password FROM \"User\".\"CustomerTest\" WHERE email = ?"
            );

            statement.setString(1, "johngreed901@gmail.com");

            set = statement.executeQuery();

            assertTrue(set.next());

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    void checkUserById() {

        try {

            statement = connection.prepareStatement(
                    "SELECT COUNT (*) FROM \"User\".\"CustomerTest\" WHERE id = ?"
            );

            statement.setInt(1, 1);

            set = statement.executeQuery();

            assertTrue(set.next());

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

}