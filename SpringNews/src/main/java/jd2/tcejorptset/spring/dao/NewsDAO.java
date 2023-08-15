package jd2.tcejorptset.spring.dao;

import java.util.List;

import jd2.tcejorptset.spring.entity.News;


public interface NewsDAO {
	List<News> getListByKeyword(String keyword);
	List<News> getLatestsList(int count);
	News fetchById(int newsId);
	void addNews(News news);
	void updateNews(News news);
	void deleteNews(int [] newsId);
}
