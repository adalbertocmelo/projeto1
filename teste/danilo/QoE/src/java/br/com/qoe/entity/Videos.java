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
@Table (name="videos")
public class Videos implements Serializable{
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    
    private String nome;
    private int gop;
    private int fps;
    private boolean cgop;
    private boolean sameq;
    
    @Column (name="video_type")
    private String videoType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getGop() {
        return gop;
    }

    public void setGop(int gop) {
        this.gop = gop;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isCgop() {
        return cgop;
    }

    public void setCgop(boolean cgop) {
        this.cgop = cgop;
    }

    public boolean isSameq() {
        return sameq;
    }

    public void setSameq(boolean sameq) {
        this.sameq = sameq;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final Videos other = (Videos) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
