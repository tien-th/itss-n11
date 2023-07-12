package quanly;


import connection.DbConnection;
import entity.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserManageController {
    public ArrayList<User> userList = new ArrayList<User>();

    public void getUserList() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM public.user Where role = 0";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String username = rs.getString("username"); // username
            String password = rs.getString("pass"); // password
            String email = rs.getString("email"); // username
            String name = rs.getString("hoten"); // username
            String birthday = String.valueOf(rs.getDate("birthday")); // name
            int role = rs.getInt("role");
            String gender = rs.getString("gender");
            User p = new User(username, password, email, name, birthday, gender, role);
            userList.add(p);
        }
    }

    public void deleteUser(User user) {

        String sql = "DELETE FROM public.user WHERE username = ? AND role = 0 OR role = 2";
        try {
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // add user to database in userManageController
    public void addUser(User user) throws IOException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.user (username, pass, email, hoten, birthday, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getName());
            // birthday in database: date but birthday in user: string
            LocalDate birthday = LocalDate.parse(user.getBirthday());
            ps.setDate(5, java.sql.Date.valueOf(birthday));
            ps.setString(6, user.getGender());
            ps.setInt(7, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }

    }

