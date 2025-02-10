package src.model.services.UserServices;

import src.model.services.DatabaseGeneralContract;
import src.model.entities.UserEntities.Admin;
import src.db.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminTable implements DatabaseGeneralContract, UserContract, AdminContract {

    private Admin admin;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet set;

    public AdminTable () {}

    public AdminTable (Admin admin) {

        this.admin = admin;

    }

    @Override
    public void insert () {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"Admin\"" +
                            " (email, ticket, password, typeuser)" +
                            " VALUES (?,?,?,?)"
            );

            statement.setString(1, admin.getEmail());

            statement.setString(2, admin.getTicket());

            statement.setString(3, admin.getPassword());

            statement.setString(4, admin.getTypeUser().name());

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
              "SELECT * FROM \"User\".\"Admin\""
            );

            set = statement.executeQuery();

            while (set.next()) {

                String ticket = set.getString("ticket");

                String email = set.getString("email");

                String type = set.getString("typeuser");

                System.out.println("NAME: " + ticket + " TYPE: " + type + " EMAIL: " + email);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void deleteComponent (Connection connection, Integer id) {

        try {

            statement = connection.prepareStatement(
                    "DELETE FROM \"User\".\"Admin\" WHERE id = ?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Element deleted successfully!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void deleteAll () {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                "DELETE FROM \"User\".\"Admin\""
            );

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

    @Override
    public Admin obtainUserProperties (Connection connection, Integer id) {

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"User\".\"Admin\" WHERE id = ?"
            );

            statement.setInt(1, id);

            set = statement.executeQuery();

            if (set.next()) {

                String email = set.getString("email");

                String ticket = set.getString("ticket");

                String password = set.getString("password");

                return new Admin(email, ticket, password);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }


    @Override
    public Integer obtainUserId(Connection connection, String email) {

        try {

            statement = connection.prepareStatement(
                    "SELECT id FROM \"User\".\"Admin\" WHERE email = ?"
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
                    "SELECT password FROM \"User\".\"Admin\" WHERE email = ?"
            );

            statement.setString(1, email);

            set = statement.executeQuery();

            if (set.next()) storedPass = set.getString("password");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return storedPass;

    }

    public void updateUser (Connection connection, String name, String email, String password, Integer id) {

    }

    @Override
    public List <String> obtainStoredEmails(Connection connection) {

        List <String> emails = new ArrayList<>();

        try {

            statement = connection.prepareStatement(
                    "SELECT email FROM \"User\".\"Admin\""
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
    public String getStoredTicket(Connection connection, Integer userId) {

        try {

            statement = connection.prepareStatement(
                    "SELECT ticket FROM \"User\".\"Admin\" WHERE id = ?"
            );

            statement.setInt(1, userId);

            set = statement.executeQuery();

            if (set.next()) return set.getString("ticket");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

        return null;

    }

    @Override
    public void updateEmail(Connection connection, String email, Integer userId) {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"User\".\"Admin\" SET email = ? WHERE id = ?"
            );

            statement.setString(1, email);

            statement.setInt(2, userId);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Override
    public void updateTicket(Connection connection, String ticket, Integer userId) {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"User\".\"Admin\" SET ticket = ? WHERE id = ?"
            );

            statement.setString(1, ticket);

            statement.setInt(2, userId);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

}
