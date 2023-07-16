import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.connection.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDbConnection {

    @Test
    public void testOpenConnection() {
        try {
            Connection connection = DbConnection.openConnection();
            Assertions.assertNotNull(connection);
            Assertions.assertFalse(connection.isClosed());
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            Assertions.fail("Failed to open database connection: " + e.getMessage());
        }
    }
}
