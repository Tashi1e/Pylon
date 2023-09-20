package jd2.tcejorptset.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.global.constants.ErrorCode;

@Repository
public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> getListByKeyword(String keyword) {
		List<News> list = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> theQuery = currentSession.createQuery("from News where title like :%?%", News.class);
			list = theQuery.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(ErrorCode.LATEST_NEWS_LITS.getCode(), e);
		}
		return list;
	}

	@Override
	@Transactional
	public List<News> getLatestsList(int count) {
		List<News> list = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> theQuery = currentSession.createQuery("from News", News.class);
			theQuery.setFirstResult(0);
			theQuery.setMaxResults(count);
			list = theQuery.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(ErrorCode.LATEST_NEWS_LITS.getCode(), e);
		}
		return list;
	}

	@Override
	public News fetchById(int newsId) {
		News news = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> theQuery = currentSession.createQuery("from News where id = :newsId", News.class);
			theQuery.setParameter("newsId", newsId);
			news = theQuery.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(ErrorCode.FETCH_NEWS.getCode(), e);
		}
		return news;
	}

	@Override
	public void addOrUpdateNews(News news) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(news);
		} catch (Exception e) {
			throw new RuntimeException(ErrorCode.ADD_NEWS.getCode(), e);
		}
	}

	@Override
	public void deleteNews(Integer [] newsId) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
				currentSession.createQuery("delete from News where id in (:newsId)")
				.setParameterList("newsId", newsId)
				.executeUpdate(); 
		} catch (Exception e) {
			throw new RuntimeException(ErrorCode.DELETE_NEWS.getCode() ,e);
		}

	}
}
