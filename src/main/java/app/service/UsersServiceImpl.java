package app.service;

import app.dao.UsersDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersDao usersDao;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public User getById(int id) {
        return usersDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return usersDao.getAll();
    }

    @Override
    public void save(User user) {
        usersDao.save(user);
    }

    @Override
    public void delete(int id) {
        usersDao.delete(id);
    }

    @Override
    public void update(User user) {
        usersDao.update(user);
    }
}
