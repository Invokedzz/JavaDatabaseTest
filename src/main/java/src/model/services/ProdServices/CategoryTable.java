package src.model.services.ProdServices;

import src.model.entities.ProdEntities.Category;

import src.db.DB;

import src.db.DbException;

import src.model.services.DatabaseContract;

import java.sql.*;

public class CategoryTable implements DatabaseContract {

    private Category category;

    public CategoryTable () {}

    public CategoryTable (Category category) {

        this.category = category;

    }

    // Name: Electronics, tier: 1
    //  Name: Tools, tier: 2
    // Name: Clothes, tier: 3

    @Override
    public void insert () {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "INSERT INTO \"Stock\".\"Category\" "
                            + "(tier, typeproduct) "
                            + "VALUES (?, ?)"

            );

            statement.setString(2, category.getType().name());

            statement.setInt(1, category.getTier());

            int rows = statement.executeUpdate();

            System.out.println("Rows affected: " + rows);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

    @Override
    public void display () {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "SELECT * FROM \"Stock\".\"Category\""

            );

            ResultSet set = statement.executeQuery();

            while (set.next()) {

                int tier = set.getInt("tier");

                String type = set.getString("typeproduct");

                System.out.println(type + " " + tier);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeStatements(statement);

            DB.closeConnections(connection);

        }

    }

    @Override
    public void deleteElement() {

    }

    // WARNING: this function deletes the ENTIRE table
    @Override
    public void deleteAll() {

        Connection connection = null;

        PreparedStatement statement = null;

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "DELETE FROM \"Stock\".\"Category\""
            );

            System.out.println("All the categories were deleted successfully!");

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeStatements(statement);

            DB.closeConnections(connection);

        }

    }

}
