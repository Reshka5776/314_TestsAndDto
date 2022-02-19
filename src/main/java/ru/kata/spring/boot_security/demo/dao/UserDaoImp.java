package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public User getUserById(Integer id) {
        return em.find(User.class, id);
    }

    public void save(User user) {
        em.persist(user);
    }

    public void update(User updatedUser) {
        em.merge(updatedUser);
    }

    public void delete(Integer id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public List<User> getDemandedUsers() {
        List<User> list1 = em.createQuery("select c from User c", User.class).getResultList();
        return list1;
    }

    public User findByUsername(String usernameReq) {
        User user = null;

        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :userRequest", User.class);
            user = query.setParameter("userRequest", usernameReq).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            System.out.printf("User '%s' не найден%n", usernameReq);
        } finally {
            em.close();
        }
        return user;
    }
}


