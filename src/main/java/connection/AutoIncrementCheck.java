package connection;

import java.sql.*;

public class AutoIncrementCheck {
    public static void main(String[] args) {
        String tableName = "pet";
        String columnName = "pet_id";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pet_management", "postgres", "itss")) {
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet = metadata.getColumns(null, null, tableName, columnName);

            if (resultSet.next()) {
                String isAutoIncrement = resultSet.getString("IS_AUTOINCREMENT");
                if ("YES".equals(isAutoIncrement)) {
                    System.out.println("The primary key is auto-incrementing.");
                } else {
                    System.out.println("The primary key is not auto-incrementing.");
                }
            } else {
                System.out.println("The table or column does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
