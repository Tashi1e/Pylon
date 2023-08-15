package jd2.tcejorptset.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import jd2.tcejorptset.spring.entity.News;

@Service
public class NewsServiceImpl implements NewsService{
	
	

	@Override
	public void save(News news) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(News news) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int[] newsId) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<News> latestList(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> latestList() {
		// TODO Auto-generated method stub
		return null;
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
