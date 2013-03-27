/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Usuario;
import model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hugo
 */
public class UsuarioDao {

    private Session session;
    private Transaction transaction;

    public void addUser(Usuario u) {
        try {
            
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Usuario usuario = new Usuario();
            usuario.setName(u.getName());
            usuario.setAge(u.getAge());
            usuario.setGender(u.isGender());
            usuario.setProfession(u.getProfession());

            session.save(usuario);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void removeUser(Usuario u) {
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
