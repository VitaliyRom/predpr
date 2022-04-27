package jm.task.core.jdbc.service;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
    UserDaoHibernateImpl daoh = new UserDaoHibernateImpl();
    User user = new User();

    @Override
    public void createUsersTable() {
        daoh.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        daoh.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        daoh.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) {
        daoh.removeUserById(id);

    }
    @Override
    public List<User> getAllUsers() {
        if(daoh.getAllUsers() != null) {
            return daoh.getAllUsers();
        }
        return null;
    }
    @Override
    public void cleanUsersTable() {
        daoh.cleanUsersTable();
    }
}
