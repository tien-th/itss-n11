package controller;

import model.entity.User;
import utils.Check;
import model.repository.UserQuerier;


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
        String username = u.getUsername();

        if (!Check.checkUsername(username)) {
            return "Invalid username" ;
        }
        if (!Check.checkPassword(u.getPassword())) {
            return "Invalid password" ;
        }
        if(!Check.checkEmail(u.getEmail())) {
            return "Invalid email" ;
        }

        boolean usernameExist = UserQuerier.checkUserName(username);
        if (usernameExist) {
            return "Can't use this username, it already exist" ;
        }
        if (UserQuerier.saveUserToDb(u)) {
            return "Sign up successfully" ;
        }
        return "Sign up failed" ;
    }
}
