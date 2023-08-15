package jd2.tcejorptset.spring.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import jd2.tcejorptset.spring.entity.News;

@Repository
public class NewsDAOimpl implements NewsDAO {

	@Override
	public List<News> getListByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> getLatestsList(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News fetchById(int newsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNews(News news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNews(News news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNews(int[] newsId) {
		// TODO Auto-generated method stub
		
	}

}
