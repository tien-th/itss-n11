package controller.login;

import entity.User;

import java.sql.SQLException;

public class LoginController {

    public static String getUrlScreenActor(User u) {
        if (u.getRole() == 0) {
            return "./src/main/resources/com/screen/user.fxml";
        }
        else if (u.getRole() == 1) {
            return "./src/main/resources/com/screen/adminUI.fxml";
        }
        else if (u.getRole() == 2) {
            return "./src/main/resources/com/screen/listAppoint.fxml";
        }
        System.out.println("Login failed");
        return null ;
    }

    public User getUser(String username, String password) throws SQLException, ClassNotFoundException {
        User u = UserQuerier.getUser(username, password);
        return u  ;
    }

    public String signUp(User u) throws SQLException, ClassNotFoundException {
        // TODO
        String username = u.getUsername();

        String regex = "^[a-zA-Z0-9]{6,}$";
        if (!username.matches(regex)) {
            return "Tên đăng nhập không hợp lệ" ;
        }

        boolean usernameExist = UserQuerier.checkUserName(username);
        if (usernameExist) {
            return "Tên đăng nhập đã tồn tại" ;
        }
        if (UserQuerier.saveUserToDb(u)) {
            return "Đăng ký thành công" ;
        }
        return "Đăng ký không thành công" ;
    }
}
