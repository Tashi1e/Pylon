package jd2.tcejorptset.spring.service;

import java.util.List;

import jd2.tcejorptset.spring.bean.News;

public interface NewsService {
	
  void saveOrUpdate(News news);
  void delete(Integer [] newsId);
  
  List<News> latestList(int count);
  List<News> latestList();
  List<News> find(String keyWord);
  News findById(int id);
}
