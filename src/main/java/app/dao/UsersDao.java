package app.dao;

import app.model.User;

import java.util.List;


public interface UsersDao {
    public User getById(int id);
    public List<User> getAll();
    public void save(User user);
    public void delete(int id);
    public void update(User user);
}
