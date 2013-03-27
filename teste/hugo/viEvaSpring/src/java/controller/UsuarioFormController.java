/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.HibernateUtil;
import model.Usuario;
import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class UsuarioFormController extends SimpleFormController {

    public UsuarioFormController() {
        setCommandClass(Usuario.class);
        setCommandName("usuario");
        setSuccessView("usuario");
        setFormView("new_usuario_form");
    }

    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        Usuario user = (Usuario) command;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("usuario.htm"));
    }
}