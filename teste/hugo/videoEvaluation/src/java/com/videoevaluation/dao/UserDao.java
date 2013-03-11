/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.videoevaluation.dao;

import com.videoevaluation.entity.User;
import com.videoevaluation.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hugo
 */
public class UserDao {

    private Session session;
    private Transaction transaction;

    public void addUser(User u) {
        try {
            
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            User user = new User();
            user.setName(u.getName());
            user.setAge(u.getAge());
            user.setGender(u.isGender());
            user.setProfession(u.getProfession());

            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void removeUser(User u) {
        try {
            
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(u);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
}
