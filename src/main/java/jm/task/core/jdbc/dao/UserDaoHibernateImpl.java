package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    User user = new User();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS user " +
                "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(40) NOT NULL, lastName VARCHAR(40) NOT NULL, age TINYINT NOT NULL)";
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }



    @Override
    public void dropUsersTable() {
         Session session = Util.getSessionFactory().openSession();
         session.beginTransaction();
         String sql = "DROP TABLE IF EXISTS user";
         session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
         session.getTransaction().commit();
         session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        session.save(user);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.load(User.class, id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String sql = "From " + User.class.getSimpleName();
            List<User> list = session.createQuery(sql).list();
            list.forEach(System.out::println);
            session.getTransaction().commit();
            session.close();
            return list;
        }
        catch (Exception e) {
            System.out.println("Таблица пустая");
        }
        session.getTransaction().commit();
        session.close();
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
