package controller;


import model.entity.User;
import model.repository.UserDbManager;

import java.sql.SQLException;
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

