package repository;

import utils.connection.DbConnection;
import controller.user.UserFuncBase;
import entity.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class DangnhapController extends UserFuncBase {

    // DB utils.connection
    public User getUser(String username, String password) throws SQLException, ClassNotFoundException{

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
        System.out.println("Login failed");
        return null ;
    }

    public boolean checkUserName(String username) throws SQLException, ClassNotFoundException {
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




    public User checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        if (checkUserName(username)) {
             User u = getUser(username, password);
            if (u != null ) {
                return u;
            }
        }
        return null;
    }

    public boolean saveUserToDb(User u) throws SQLException, ClassNotFoundException {
        String username = u.getUsername();
        boolean usernameExist = this.checkUserName(username);
        if (usernameExist) {
            System.out.println("Username đã tồn tại");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username already exist");
            alert.setContentText("Please try again");
            alert.showAndWait();
            return false;
        }


        // check username valid with regex
        String regex = "^[a-zA-Z0-9]{6,}$";
        if (!username.matches(regex)) {
            System.out.println("Username không hợp lệ");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username invalid");
            alert.setContentText("Please try again");
            alert.showAndWait();
            return false;
        }
        // Insert user to db
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
            // TODO: Prompt user to login successfully
            System.out.println("Đăng ký thành công");
            showAlert("Success.");
        }catch (SQLException e){
            // TODO: Prompt user to login failed
            e.printStackTrace();
            showAlert("Failed!");
        }
        return true;
    }
    public void showAlert(String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
