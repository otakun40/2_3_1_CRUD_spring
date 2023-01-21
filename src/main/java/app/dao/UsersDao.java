package app.dao;

import app.model.User;

import java.util.List;


public interface UsersDao {
    User getById(long id);
    List<User> getAll();
    void save(User user);
    void delete(long id);
    void update(User user);
}
