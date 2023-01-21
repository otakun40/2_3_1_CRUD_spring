package app.service;

import app.dao.UsersDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    private final UsersDao usersDao;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public User getById(long id) {
        return usersDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return usersDao.getAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        usersDao.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        usersDao.delete(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        usersDao.update(user);
    }
}
