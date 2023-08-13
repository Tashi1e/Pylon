package jd2.tcejorptset.spring.dao;

import java.util.List;

import jd2.tcejorptset.spring.bean.News;


public interface NewsDAO {
	List<News> getListByKeyword(String keyword) throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int newsId) throws NewsDAOException;
	void addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNews(int [] newsId)throws NewsDAOException;
}
