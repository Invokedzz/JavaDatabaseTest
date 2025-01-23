package src.model.services;

import src.model.entities.ProdEntities.Category;

import src.db.DB;

import src.db.DbException;

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

                    "INSERT INTO public.\"Category\" "
                            + "(name, tier) "
                            + "VALUES (?, ?)"

            );

            statement.setString(1, category.getName());

            statement.setInt(2, category.getTier());

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

    }

}
