/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qoe.bean;

import br.com.qoe.dao.PlanoAvaliacaoDao;
import br.com.qoe.entity.PlanoAvaliacao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gercom
 */
@ManagedBean
@SessionScoped
public class PlanoAvaliacaoBean {

    public PlanoAvaliacaoBean() {
    }
    private PlanoAvaliacao planoAval = new PlanoAvaliacao();
    private PlanoAvaliacaoDao planoAvalDao = new PlanoAvaliacaoDao();

    public String addPlanoAvaliacao() {
        planoAvalDao.addPlanoAvaliacao(planoAval);
        planoAval.setMechanism(null);
        return "sucessoCadastroPlanoAvaliacao";
    }

    public String deletePlanoAvaliacao() {
        planoAvalDao.deletePlanoAvaliacao(planoAval);
        return "deleteSucessoPlanoAvaliacao";
    }

    public PlanoAvaliacao getPlanoAval() {
        return planoAval;
    }

    public void setPlanoAval(PlanoAvaliacao planoAval) {
        this.planoAval = planoAval;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.planoAval != null ? this.planoAval.hashCode() : 0);
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
        final PlanoAvaliacaoBean other = (PlanoAvaliacaoBean) obj;
        if (this.planoAval != other.planoAval && (this.planoAval == null || !this.planoAval.equals(other.planoAval))) {
            return false;
        }
        return true;
    }
}
