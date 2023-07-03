package quanly;


import connection.DbConnection;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManageController {
    public ArrayList<User> userList = new ArrayList<User>();

    public void getUserList() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM public.user";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String username = rs.getString("username"); // username
            String password = rs.getString("pass"); // password
            String email = rs.getString("email"); // username
            String name = rs.getString("hoten"); // username
            String birthday = rs.getDate("birthday").toString(); // name
            int role = rs.getInt("role") ;
            String gender = rs.getString("gender");
            User p = new User(username, password,email ,name, birthday, gender ,role);
            userList.add(p);
        }
    }

    public void deleteUser(User user) {
        String sql = "DELETE FROM public.user WHERE username = ?";
        try {
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
