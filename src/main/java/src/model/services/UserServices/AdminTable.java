package src.model.services.UserServices;

import src.model.services.DatabaseGeneralContract;

import src.model.entities.UserEntities.Admin;

import src.db.DB;

import src.db.DbException;

import src.security.PassHash;

import java.sql.*;

import java.util.Scanner;

public class AdminTable implements DatabaseGeneralContract, UserContract {

    Scanner sc = new Scanner(System.in);

    private Admin admin;

    public AdminTable () {}

    public AdminTable (Admin admin) {

        this.admin = admin;

    }

    @Override
    public void insert () {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "INSERT INTO \"User\".\"Admin\"" +
                            " (name, password, typeuser)" +
                            " VALUES (?,?,?)"
            );

            statement.setString(1, admin.getName() + " " + admin.getLastName());

            String hashPassword = PassHash.generateHash(admin.getPassword());

            statement.setString(2, hashPassword);

            statement.setString(3, admin.getTypeUser().name());

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
              "SELECT * FROM \"User\".\"Admin\""
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
                    "DELETE FROM \"User\".\"Admin\" WHERE id = ?"
            );

            statement.setInt(1, sc.nextInt());

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

        Connection connection = null;

        PreparedStatement statement = null;

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
    public void updateName() {

    }

}
