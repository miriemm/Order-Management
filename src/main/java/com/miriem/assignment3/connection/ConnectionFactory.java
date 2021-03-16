package com.miriem.assignment3.connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles connection with the database
 */
public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sql";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Loads the mysql jdbc driver. The driver is loaded only once (Singleton pattern)
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates a connection to the specified database
     *
     * @return database connection
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ERROR to open");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Calls create connection on the singleton factory
     * @return database connection
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Closes a connection to the database
     *
     * @param connection database connection
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ERROR to close connection");
            }
        }
    }

    /**
     * Closes a statement
     * @param statement statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ERROR TO CLOSE STATEMENT");
            }
        }
    }

    /**
     * Closes a result set
     *
     * @param resultSet result set
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ERROR TO CLSOE RESULTSET");
            }
        }
    }

}

