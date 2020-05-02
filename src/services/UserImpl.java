/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.util.List;

/**
 *
 * @author mac
 */
public class UserImpl implements IUser {
    private dao.UserDaoImpl userDao;
    // BUSINESS LOGIC
    @Override
    public User findById(int id) {
        if(id <= 0) 
            return null;
        else {
            // DAO = Data Access Object
            userDao = new dao.UserDaoImpl();
            return userDao.findById(id);
        } 
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    @Override
    public boolean deleteById(int id) {
        if(id <= 0) 
            return false;
        else {
            return userDao.deleteById(id);
        }
        
    }

    @Override
    public User updateById(int id, User user) {
        if(id <= 0) 
            return null;
        else {
            return userDao.updateById(id, user);
        }
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }   
}
