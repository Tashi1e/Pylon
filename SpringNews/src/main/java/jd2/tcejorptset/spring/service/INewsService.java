package jd2.tcejorptset.spring.service;

import java.util.List;

import jd2.tcejorptset.spring.bean.News;

public interface INewsService {
	
  void save(News news) throws ServiceException;
  void update(News news) throws ServiceException;
  void delete(int [] newsId) throws ServiceException;
  
  List<News> latestList(int count)  throws ServiceException;
  List<News> latestList()  throws ServiceException;
  List<News> find(String keyWord) throws ServiceException;
  News findById(int id) throws ServiceException;
}
