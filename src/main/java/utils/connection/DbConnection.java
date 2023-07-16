package utils.connection;

import java.sql.*;

public class DbConnection {
    private static Connection connection = null;

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        // connect to postgresql
        Class.forName("org.postgresql.Driver");
        String connectionURL = "jdbc:postgresql://localhost:5432/pet_management";
        String username = "postgres";
        String password = "itss";
        connection = DriverManager.getConnection(connectionURL, username, password);
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = openConnection();
            System.out.println("Connected to database successfully");
            String sql = "SELECT * FROM public.user u";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            printResultSet(rs);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                closeConnection();
                System.out.println("Connection closed successfully");
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    public static void printResultSet(ResultSet rs) throws SQLException {
        // Your existing code here...
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
            }
            System.out.println("");
        }
    }
}