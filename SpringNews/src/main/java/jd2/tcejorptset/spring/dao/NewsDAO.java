package jd2.tcejorptset.spring.dao;

import java.util.List;

import jd2.tcejorptset.spring.bean.News;


public interface NewsDAO {
	List<News> getListByKeyword(String keyword);
	List<News> getLatestsList(int count);
	News fetchById(int newsId);
	void addOrUpdateNews(News news);
	void deleteNews(Integer [] newsId);
}
