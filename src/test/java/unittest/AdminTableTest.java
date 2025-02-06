package unittest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.db.DB;
import src.db.DbException;
import src.model.entities.UserEntities.Admin;
import src.model.enums.TypeUser;
import src.validation.CheckAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdminTableTest {

    private static Admin admin;

    private static CheckAdmin checkAdmin;

    private static Connection connection;

    private static PreparedStatement statement;

    private static ResultSet set;

    // super(name, lastName, email, password, typeUser);

    @BeforeEach
    void setUp() {

        admin = new Admin("paulwrath@gmail.com",
                "I'mTheBest", TypeUser.ADMIN, "");

        checkAdmin = new CheckAdmin();

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
                    "INSERT INTO \"User\".\"AdminTest\" " +
                            "(name, email, password, typeuser)" +
                            " VALUES (?,?,?,?)"
            );

            statement.setString(1, admin.getName());

            statement.setString(2, admin.getEmail());

            statement.setString(3, admin.getPassword());

            statement.setString(4, admin.getTypeUser().name());

            if (checkAdmin.test(admin)) {

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
                    "SELECT * FROM \"User\".\"AdminTest\""
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
                    "UPDATE \"User\".\"AdminTest\" set name = ? WHERE id = ?"
            );

            statement.setString(1, "John Piper");

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
                    "SELECT password FROM \"User\".\"AdminTest\" WHERE email = ?"
            );

            statement.setString(1, "paulwrath@gmail.com");

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
                    "SELECT COUNT (*) FROM \"User\".\"AdminTest\" WHERE id = ?"
            );

            statement.setInt(1, 1);

            set = statement.executeQuery();

            assertTrue(set.next());

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }
}