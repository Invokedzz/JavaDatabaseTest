package src.db;

import java.io.FileInputStream;

import java.io.IOException;

import java.sql.*;

import java.util.Properties;

public class DB {

    private static Connection connection = null;

    public static Connection getConnection () {

        try {

            if ( connection == null ) {

                Properties properties = loadProperties();

                String url = properties.getProperty("dburl");

                connection = DriverManager.getConnection(url, properties);

            }

        } catch ( SQLException exception ) {

            throw new DbException( exception.getMessage() );

        }

        return connection;

    }

    private static Properties loadProperties () {

        try ( FileInputStream fileInputStream = new FileInputStream("db.properties") ) {

            Properties properties = new Properties();

            properties.load( fileInputStream );

            return properties;

        } catch ( IOException exception ) {

            throw new DbException( exception.getMessage() );

        }

    }

    public static void closeStatements (Statement statement) {

        try {

            if (statement != null) statement.close();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    public static void closeConnections (Connection connection) {

        try {

            if (connection != null) connection.close();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    public static void closeResult (ResultSet resultSet) {

        try {

            if (resultSet != null) resultSet.close();

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

}
