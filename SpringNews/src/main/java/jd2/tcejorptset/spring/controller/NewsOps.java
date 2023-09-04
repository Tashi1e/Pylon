package jd2.tcejorptset.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jd2.tcejorptset.spring.service.NewsService;
import jd2.tcejorptset.spring.bean.News;

@Controller
public class NewsOps {
	
	@Autowired
	private NewsService newsService;

	@Autowired
	private Map<String, Object> attributesContainer;


	@RequestMapping(value = { "*/mainPage", "*/newsListPage"})
	public String latestNewsList(Model model) {
//		List <News> newsList = newsService.latestList();
//		model.addAttribute("newsList", newsList);
		return "layouts/baseLayout";
	}

	@RequestMapping("*/newsPage")
	public String newsPage(@ModelAttribute("newsData") News news, Model model) {
		model.addAttribute("news", news);
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/editNewsPage")
	public String doSignOut() {
		attributesContainer.clear();
		return "layouts/baseLayout";
	}
}
