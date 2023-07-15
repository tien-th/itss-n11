package utils.connection;
import java.sql.*;
public class DbConnection {
    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        // connect to postgresql
        Class.forName("org.postgresql.Driver");
        String connectionURL = "jdbc:postgresql://localhost:5432/pet_management";
        String username = "postgres";
        String password = "itss";
        return DriverManager.getConnection(connectionURL, username, password);
    }
    public static void main(String[] args) {
        try {
            Connection con = openConnection();
            System.out.println("Connected to database successfully");
            String sql = "SELECT * FROM public.user u";
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
//            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            printResultSet(rs);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // to fix bug connect db
    public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        System.out.println("#: " + columnCount);
        // Print column names
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i));
            if (i != columnCount) {
                System.out.print(", ");
            }
        }
        System.out.println();

        // Print rows
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i));
                if (i != columnCount) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
