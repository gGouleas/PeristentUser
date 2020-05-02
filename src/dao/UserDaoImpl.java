/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author mac
 */
public class UserDaoImpl implements IUserDao {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public UserDaoImpl() {
        emf = Persistence.createEntityManagerFactory("PeristentUserPU");
        em = emf.createEntityManager();
    }

    @Override
    public User findById(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT zoukou FROM User zoukou", User.class).getResultList();
    }
    
    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM `users` WHERE `email` = '" + email + "';";
        Query q = em.createNativeQuery(query, User.class);
        return (User)q.getSingleResult(); // if more than one results, javax.persistence.NonUniqueResultException FIX
    }

    @Override
    public boolean deleteById(int id) {
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if(user != null) {
            em.remove(user);
            em.getTransaction().commit();
            return true;
        }
        else {
            em.getTransaction().commit();
            return false;
        }
            
    }

    @Override
    public User updateById(int id, User user) {
        em.getTransaction().begin();
        User db_user = em.find(User.class, id);
        if(db_user != null) {
            db_user.setFirstName(user.getFirstName());
            db_user.setLastName(user.getLastName());
            db_user.setTel(user.getTel());
            db_user.setEmail(user.getEmail());
            em.getTransaction().commit();
        }
        return db_user;
    }

    @Override
    public User save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return findByEmail(user.getEmail());
    }
}
