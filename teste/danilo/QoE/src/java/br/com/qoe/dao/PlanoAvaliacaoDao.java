/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qoe.dao;

import br.com.qoe.entity.PlanoAvaliacao;
import br.com.qoe.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gercom
 */
public class PlanoAvaliacaoDao {

    private Session sessao;
    private Transaction trans;

    public void addPlanoAvaliacao(PlanoAvaliacao p) {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            PlanoAvaliacao planoAval = new PlanoAvaliacao();
                    
            planoAval.setBandwidth(p.getBandwidth());
            planoAval.setCongest_max(p.getCongest_max());
            planoAval.setCongest_min(p.getCongest_min());
            planoAval.setCongest_var(p.getCongest_var());
            planoAval.setMechanism(p.getMechanism());
            planoAval.setNode_id_source(p.getNode_id_source());
            planoAval.setNode_id_destination(p.getNode_id_destination());
            
            sessao.save(planoAval);
            trans.commit();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            sessao.close();

        }

    }
    
        public void deletePlanoAvaliacao(PlanoAvaliacao p) {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            sessao.delete(p);
            trans.commit();


        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            sessao.close();
        }

    }
}
