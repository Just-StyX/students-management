package jsl.groups.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Jdbc connection.
 * @author github.com/Just-StyX
 */
public class JDBCConnection {


    /**
     * Gets connection.
     *
     * @return the connection to the database
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
