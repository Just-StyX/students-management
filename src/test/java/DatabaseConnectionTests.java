import jsl.groups.db.JDBCConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConnectionTests {
    Connection connection = null;

    @BeforeEach
    public void setUp() {
        connection = JDBCConnection.getConnection();
    }

    @Test
    @DisplayName("Test if jdbc is connected to database and closed afterward")
    public void testConnected() {
        assertAll(
                () -> assertTrue(connection.isValid(5)),
                () -> {
                    connection.close();
                    assertTrue(connection.isClosed());
                }
        );
    }
}
