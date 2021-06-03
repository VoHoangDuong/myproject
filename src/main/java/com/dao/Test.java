package com.dao;

import com.model.User;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        List<User> userList = userDAO.searchLike("Duong");
//        List<User> userList = userDAO.searchUser("Khanh");
        List<User> userList = userDAO.sortByName();
        for (User u: userList) {
            System.out.println(u);
        }
    }
}
