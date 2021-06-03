package com.dao;

import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO  implements IUserDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "duong6046237";

    public Connection connection = null;
    public PreparedStatement statement;
    public ResultSet resultSet = null;
    User user = null;

    public UserDAO(){

    }

    protected Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        String query = "insert into users" + " (name,email,country) values " + "(?,?,?);";
        System.out.println(query);
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setString(3, user.getCountry());
            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
         String query = "select * from users where id = ?;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
            }
        }catch (SQLException se){
            printSQLException(se);
        }
        return user;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> userList = new ArrayList<>();
        String query = "select * from users;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
                userList.add(user);
            }
        }catch (SQLException se){
            printSQLException(se);
        }
        return userList;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted = false;
        String query = "delete from users where id = ?;";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;
        String query = "update users set name = ?, email = ?,country = ?\n" +
                "where id = ?;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getCountry());
            statement.setInt(4,user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e){
            printSQLException(e);
        }
        return rowUpdated;
    }

    public List<User> searchLike(String keyword){
        List<User> userList = new ArrayList<>();
        keyword = "%" + keyword + "%";
        String query = "select * from users where name like ? or email like ? or country like ?;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,keyword);
            statement.setString(2,keyword);
            statement.setString(3,keyword);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
                userList.add(user);
            }
        }catch (SQLException se){
            printSQLException(se);
        }
        return userList;
    }

    public List<User> searchUser(String keyword){
        List<User> userList = new ArrayList<>();
        String query = "select * from users where name = ? or email = ? or country = ?;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,keyword);
            statement.setString(2,keyword);
            statement.setString(3,keyword);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
                userList.add(user);
            }
        }catch (SQLException se){
            printSQLException(se);
        }
        return userList;
    }

    public List<User> sortByName(){
        List<User> userList = new ArrayList<>();
        String query = "select * from users order by name;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
                userList.add(user);
            }
        }catch (SQLException se){
            printSQLException(se);
        }
         return userList;
    }

    @Override
    public void insertUserStore(User user) throws SQLException {

    }

    @Override
    public List<User> displayUserStore() {
        return null;
    }

    @Override
    public void addUserTransaction(User user, int[] permisions) {

    }

    @Override
    public void insertUpdateWithoutTransaction() {

    }

    @Override
    public void insertUpdateUseTransaction() {

    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
