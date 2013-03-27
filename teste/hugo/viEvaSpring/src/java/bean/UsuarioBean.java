/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Hugo
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    /**
     * Creates a new instance of UserBean
     */
    
    private Usuario user = new Usuario();
    private UsuarioDao userDao = new UsuarioDao();
    
    public UsuarioBean() {
        
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
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
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
        final UsuarioBean other = (UsuarioBean) obj;
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }
    
}
