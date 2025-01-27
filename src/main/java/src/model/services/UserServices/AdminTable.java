package src.model.services.UserServices;

import src.model.services.DatabaseContract;

import src.model.entities.UserEntities.Admin;

import src.db.DB;

import src.db.DbException;

import java.sql.*;

import java.util.Scanner;

public class AdminTable implements DatabaseContract {

    Scanner sc = new Scanner(System.in);

    private Admin admin;

    public AdminTable () {}

    public AdminTable (Admin admin) {

        this.admin = admin;

    }

    @Override
    public void insert () {

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


}
