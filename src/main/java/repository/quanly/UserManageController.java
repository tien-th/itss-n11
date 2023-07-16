package repository.quanly;


import utils.connection.DbConnection;
import entity.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserManageController {
    public ArrayList<User> userList ;

    public ArrayList<User> getUserList() throws SQLException, ClassNotFoundException {
        if (userList == null) {
            userList = UserDbManager.getUserList();
        }
        return userList;
    }

    public void deleteUser(User user) {
        UserDbManager.deleteUser(user.getUsername());
        userList.remove(user);
    }

    public void addUser(User user) throws ClassNotFoundException {
        UserDbManager.addUser(user);
        userList.add(user);
    }


    // add user to database in userManageController


}

