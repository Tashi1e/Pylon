package jd2.tcejorptset.spring.service.impl;

import java.util.List;

import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.dao.DaoProvider;
import jd2.tcejorptset.spring.dao.INewsDAO;
import jd2.tcejorptset.spring.dao.NewsDAOException;
import jd2.tcejorptset.spring.service.INewsService;
import jd2.tcejorptset.spring.service.ServiceException;

public class NewsServiceImpl implements INewsService{

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	
	
	@Override
	public void save(News news) throws ServiceException {
		try {
			newsDAO.addNews(news);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<News> find(String keyWord) throws ServiceException {
		try {
			return	newsDAO.getListByKeyword(keyWord);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(News news) throws ServiceException {
		try {
			newsDAO.updateNews(news);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public void delete(int [] newsId) throws ServiceException {
		try {
			newsDAO.deleteNews(newsId);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<News> latestList() throws ServiceException {
			return latestList(5);
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		
		try {
			return newsDAO.getLatestsList(count);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}


}
