package jd2.tcejorptset.spring.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.bean.User;

@Repository
public class NewsDAOImpl implements NewsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> getListByKeyword(String keyword) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <News> theQuery = currentSession.createQuery("from News where title like :%?%", News.class);
		return theQuery.getResultList();
	}

	@Override
	@Transactional
	public List<News> getLatestsList(int count) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <News> theQuery = currentSession.createQuery("from News", News.class);
		theQuery.setFirstResult(0);
		theQuery.setMaxResults(count);
		return theQuery.getResultList();
	}

	@Override
	public News fetchById(int newsId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<News> theQuery = currentSession.createQuery("from News where id = :newsId", News.class);
		theQuery.setParameter("newsId", newsId);
		return theQuery.uniqueResult();
	}

	@Override
	public void addOrUpdateNews(News news) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(news);
	}

	

	@Override
	public void deleteNews(int[] newsId) {
		Session currentSession = sessionFactory.getCurrentSession();
		for (int id : newsId) {
		Query <News> theQuery = currentSession.createQuery("delete from News where id = :newsId", News.class);
		theQuery.setParameter("newsId", id);
		theQuery.executeUpdate();
		}
	}

}
