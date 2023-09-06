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
import jd2.tcejorptset.spring.bean.UserData;

@Controller
public class NewsOps {
	
	@Autowired
	private NewsService newsService;

	@Autowired
	private Map<String, Object> attributesContainer;


	@RequestMapping("/guest/main")
	public String guestInfo(Model model) {
//		List <News> newsList = newsService.latestList();
//		model.addAttribute("newsList", newsList);
		model.addAttribute("userData", new UserData());
		return "layouts/baseLayout";
	}
	
	@RequestMapping("*/newsList")
	public String latestNewsList(Model model) {
		model.addAttribute("userData", new UserData());
//		List <News> newsList = newsService.latestList();
//		model.addAttribute("newsList", newsList);
		return "layouts/baseLayout";
	}

	@RequestMapping("*/news")
	public String newsPage(@ModelAttribute("newsData") News news, Model model) {
		model.addAttribute("news", news);
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/editNews")
	public String editNewsPage() {
		attributesContainer.clear();
		return "layouts/baseLayout";
	}
	
	@RequestMapping("/saveNews")
	public String saveNews() {
		attributesContainer.clear();
		return "layouts/baseLayout";
	}
	
	@RequestMapping("/deleteNews")
	public String deleteNews() {
		attributesContainer.clear();
		return "layouts/baseLayout";
	}
	
	@RequestMapping("/fetchNews")
	public String fetchNews() {
		attributesContainer.clear();
		return "layouts/baseLayout";
	}
}
