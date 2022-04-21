package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    User user = new User();

    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getConnect().createStatement()){
            String sql = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40)," +
                    " lastName VARCHAR(40), age INT)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getConnect().createStatement()){
            String sql = "DROP TABLE users";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы");
        }

    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO  users(name, lastName, age) VALUES " +
                "('" + name + "', '" + lastName + "'," + (int) age + ")";
        try (Statement statement = Util.getConnect().createStatement()){
            statement.executeUpdate(sql);
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void removeUserById(long id) {
        try (Statement statement = Util.getConnect().createStatement()){
            String sql = "DELETE FROM users WHERE id = " + id ;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnect().createStatement()){
            String sql = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                user.setAge(res.getByte("age"));
                System.out.println(user);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getConnect().createStatement()){
            String sql = "DELETE FROM users";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
