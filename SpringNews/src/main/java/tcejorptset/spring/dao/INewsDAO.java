package tcejorptset.spring.dao;

import java.util.List;

import tcejorptset.spring.bean.News;


public interface INewsDAO {
	List<News> getListByKeyword(String keyword) throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int newsId) throws NewsDAOException;
	void addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNews(int [] newsId)throws NewsDAOException;
}
