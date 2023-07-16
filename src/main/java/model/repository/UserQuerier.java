package model.repository;


import model.entity.User;
import utils.connection.DbConnection;

import java.sql.*;

public class UserQuerier {

    // DB connection
    public static User getUser(String username, String password) throws SQLException, ClassNotFoundException{

        String sql = "SELECT * FROM public.user u WHERE u.username = ?";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        User u = null ;

        if(rs.next()){
            if (password.equals(rs.getString("pass"))){
                System.out.println("Login successfully");
                int role = rs.getInt("role");
                String email = rs.getString("email");
                String name = rs.getString("hoten");
                String birthday = String.valueOf(rs.getDate("birthday"));
                String gender = rs.getString("gender");
                u = new User(username, password, email, name, birthday, gender, role);
                return u  ;
            }
        }

        System.out.println("From DB: Login failed");
        return null ;
    }

    public static boolean checkUserName(String username) throws SQLException, ClassNotFoundException {
      // username valid: length(username)> 0 and not exits in database
        String sql = "SELECT * FROM public.user u WHERE u.username = ?";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return true ;
        }
        return false ;

    }

    public static boolean saveUserToDb(User u) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.user values (?, ?, ?, ?, ?, ?,?)";
        try (
                Connection con = DbConnection.openConnection();
                PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getEmail());
//            stmt.setString(4, u.getBirthday());
            stmt.setString(4, u.getName());
//            System.out.println(u.getBirthday());
            stmt.setDate(5, Date.valueOf(u.getBirthday()));
            stmt.setString(6, u.getGender());
            stmt.setInt(7, u.getRole());

            stmt.executeUpdate();
            // TODO: alert user that register successfully
            System.out.println("Đăng ký thành công");
//            showAlert("Success.");
        }catch (SQLException e){
            // TODO: Prompt user to login failed
            e.printStackTrace();
            return false ;
//            showAlert("Failed!");
        }
        return true;
    }

    // TODO: consider to move this method to a common class

}
