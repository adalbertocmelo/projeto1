/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class UsuarioController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr,
                                      HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("usuario");
        String out = "paranguisdf: ";
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List result = session.createQuery("from Usuario").list();
            mv.addObject("usuarios", result);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("message", out);
        return mv;
    }
}
