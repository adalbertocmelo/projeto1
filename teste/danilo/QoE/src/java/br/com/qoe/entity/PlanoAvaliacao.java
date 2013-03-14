/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qoe.entity;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author Gercom
 */
@Entity
@Table(name = "plano_avaliacao")
public class PlanoAvaliacao implements Serializable {

    public PlanoAvaliacao() {
    }
    
    @Id
    private int id;
    private int node_id_source;
    private int node_id_destination;
    private int bandwidth;
    private String mechanism;
    private int congest_max;
    private int congest_min;
    private int congest_var;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNode_id_source() {
        return node_id_source;
    }

    public void setNode_id_source(int node_id_source) {
        this.node_id_source = node_id_source;
    }

    public int getNode_id_destination() {
        return node_id_destination;
    }

    public void setNode_id_destination(int node_id_destination) {
        this.node_id_destination = node_id_destination;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public int getCongest_max() {
        return congest_max;
    }

    public void setCongest_max(int congest_max) {
        this.congest_max = congest_max;
    }

    public int getCongest_min() {
        return congest_min;
    }

    public void setCongest_min(int congest_min) {
        this.congest_min = congest_min;
    }

    public int getCongest_var() {
        return congest_var;
    }

    public void setCongest_var(int congest_var) {
        this.congest_var = congest_var;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
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
        final PlanoAvaliacao other = (PlanoAvaliacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
