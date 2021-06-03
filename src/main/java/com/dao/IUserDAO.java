package com.dao;

import com.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;

    public User selectUser(int id);

    public List<User> selectAllUser();

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    //Produce Storage
    public void insertUserStore(User user)throws SQLException;
    public List<User> displayUserStore();
    //Transaction
    public void addUserTransaction(User user, int[] permisions);
    public void insertUpdateWithoutTransaction();
    public void insertUpdateUseTransaction();
}
