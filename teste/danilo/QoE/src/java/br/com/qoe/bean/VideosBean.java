/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qoe.bean;

import br.com.qoe.dao.VideosDao;
import br.com.qoe.entity.Videos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gercom
 */
@ManagedBean
@SessionScoped
public class VideosBean {

    private Videos video = new Videos();
    private VideosDao videoDao = new VideosDao();

    public VideosBean() {
    }

    public String addVideo() {
        videoDao.addVideo(video);
        video.setNome(null);
        video.setVideoType(null);
        return "sucesso";
    }

    public String deleteVideo() {
        videoDao.deleteVideo(video);
        return "sucessoDelete";
    }

    public Videos getVideo() {
        return video;
    }

    public void setVideo(Videos video) {
        this.video = video;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.video != null ? this.video.hashCode() : 0);
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
        final VideosBean other = (VideosBean) obj;
        if (this.video != other.video && (this.video == null || !this.video.equals(other.video))) {
            return false;
        }
        return true;
    }
}
