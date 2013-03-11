/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.videoevaluation.bean;

import com.videoevaluation.dao.UserDao;
import com.videoevaluation.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Hugo
 */
@ManagedBean
@RequestScoped
public class UserBean {

    /**
     * Creates a new instance of UserBean
     */
    
    private User user = new User();
    private UserDao userDao = new UserDao();
    
    public UserBean() {
        
    }

    public String addUser() {
        userDao.addUser(user);
        user.setName(null);
        //user.setAge(null);
        //user.setGender(null);
        user.setProfession(null);
        return "Added Sucessfully";
    }

    public String removeUser() {
        userDao.removeUser(user);
        return "Removed Sucessfully";
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserBean other = (UserBean) obj;
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }
    
}
