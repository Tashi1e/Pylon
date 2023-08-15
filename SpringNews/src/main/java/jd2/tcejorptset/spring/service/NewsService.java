package jd2.tcejorptset.spring.service;

import java.util.List;

import jd2.tcejorptset.spring.entity.News;

public interface NewsService {
	
  void save(News news);
  void update(News news);
  void delete(int [] newsId);
  
  List<News> latestList(int count);
  List<News> latestList();
  List<News> find(String keyWord);
  News findById(int id);
}
