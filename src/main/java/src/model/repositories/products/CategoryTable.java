package src.model.repositories.products;

import src.model.entities.ProdEntities.Category;
import src.db.*;
import src.model.repositories.DatabaseGeneralContract;
import java.sql.*;

public class CategoryTable implements DatabaseGeneralContract {

    private Category category;

    private Connection connection;

    private PreparedStatement statement;

    public CategoryTable () {}

    public CategoryTable (Category category) {

        this.category = category;

    }

    // Name: Electronics, tier: 1
    //  Name: Tools, tier: 2
    // Name: Clothes, tier: 3

    @Override
    public void insert () {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(

                    "INSERT INTO \"Stock\".\"Category\" "
                            + "(tier, typeproduct) "
                            + "VALUES (?, ?)"

            );

            statement.setString(2, category.getType().name());

            statement.setInt(1, category.getTier());

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeConnections(connection);

            DB.closeStatements(statement);

        }

    }

    @Override
    public void deleteComponent (Connection connection, Integer id) {

        try {

            connection = DB.getConnection();

            statement = connection.prepareStatement(
                    "DELETE FROM \"Stock\".\"Category\" WHERE id = ?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        } finally {

            DB.closeStatements(statement);

            DB.closeConnections(connection);

        }

    }

}
