package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    public Role getRoleById(Integer id) {
        return em.find(Role.class, id);
    }

    public void save(Role role) {
        em.persist(role);
    }

    public void update(Role updatedRole) {
        em.merge(updatedRole);
    }

    public void delete(Integer id) {
        Role role = em.find(Role.class, id);
        em.remove(role);
    }

    @Override
    public List<Role> getDemandedRoles() {
        List<Role> list1 = em.createQuery("select r from Role r", Role.class).getResultList();
        return list1;
    }
}
