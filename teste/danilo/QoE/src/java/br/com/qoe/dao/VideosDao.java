/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qoe.dao;

import br.com.qoe.entity.Videos;
import br.com.qoe.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gercom
 */
public class VideosDao {

    private Session sessao;
    private Transaction trans;

    public void addVideo(Videos v) {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            Videos video = new Videos();

            video.setCgop(v.isCgop());
            video.setSameq(v.isSameq());
            video.setNome(v.getNome());
            video.setFps(v.getFps());
            video.setGop(v.getGop());
            video.setVideoType(v.getVideoType());

            sessao.save(video);
            trans.commit();


        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            sessao.close();
        }

    }

    public void deleteVideo(Videos v) {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            sessao.delete(v);
            trans.commit();


        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            sessao.close();
        }

    }
}
