package app.dao;

import app.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {
    @PersistenceContext
    private EntityManager em;

    public UsersDaoImpl() {
    }

    @Override
    public User getById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void delete(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }
}
