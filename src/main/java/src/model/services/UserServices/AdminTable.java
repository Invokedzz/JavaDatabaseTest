package src.model.services.UserServices;

import src.model.services.DatabaseGeneralContract;
import src.model.entities.UserEntities.Admin;
import src.db.*;
import src.security.PassHash;
import java.sql.*;

public class AdminTable implements DatabaseGeneralContract, UserContract {

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

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"Admin\"" +
                            " (name, email, password, typeuser)" +
                            " VALUES (?,?,?,?)"
            );

            statement.setString(1, admin.getName() + " " + admin.getLastName());

            String hashPassword = PassHash.generateHash(admin.getPassword());

            statement.setString(2, admin.getEmail());

            statement.setString(3, hashPassword);

            statement.setString(4, admin.getTypeUser().name());

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
              "SELECT * FROM \"User\".\"Admin\""
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
                    "DELETE FROM \"User\".\"Admin\" WHERE id = ?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Element deleted successfully!");

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
                "DELETE FROM \"User\".\"Admin\""
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
    public void updateName(String name, Integer id) {

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "UPDATE \"User\".\"Admin\" set name = ? WHERE id = ?"
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

    @Override
    public boolean checkUserById(Integer id) {

        connection = null;

        statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "SELECT COUNT (*) FROM \"User\".\"Admin\" WHERE id = ?"
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

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

        return false;

    }

}
