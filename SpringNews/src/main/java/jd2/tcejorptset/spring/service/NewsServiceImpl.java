package jd2.tcejorptset.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.dao.NewsDAO;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDAO newsDAO;

	@Override
	@Transactional
	public void saveOrUpdate(News news) {
		newsDAO.addOrUpdateNews(news);
	}

	@Override
	@Transactional
	public void delete(int[] newsId) {
		newsDAO.deleteNews(newsId);
	}

	@Override
	@Transactional
	public List<News> latestList(int count) {
		UserInfo temp = newsDAO.getLatestsList(count).get(0).getUserInfo();
		System.out.println(temp.getFirstName() + " " + temp.getLastName()); //FLAG
		return newsDAO.getLatestsList(count);
	}

	@Override
	public List<News> latestList() {
		return latestList(5);
	}

	@Override
	@Transactional
	public List<News> find(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public News findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
