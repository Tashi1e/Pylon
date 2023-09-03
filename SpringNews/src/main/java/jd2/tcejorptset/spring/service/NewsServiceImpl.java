package jd2.tcejorptset.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.dao.NewsDAO;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDAO newsDAO;

	@Override
	public void saveOrUpdate(News news) {
		newsDAO.addOrUpdateNews(news);
	}

	@Override
	public void delete(int[] newsId) {
		newsDAO.deleteNews(newsId);
	}

	@Override
	public List<News> latestList(int count) {
		return newsDAO.getLatestsList(count);
	}

	@Override
	public List<News> latestList() {
		return latestList(5);
	}

	@Override
	public List<News> find(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
