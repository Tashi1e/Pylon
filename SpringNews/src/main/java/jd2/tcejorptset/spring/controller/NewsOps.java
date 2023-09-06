package jd2.tcejorptset.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jd2.tcejorptset.spring.service.NewsService;
import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.bean.UserData;

@Controller
public class NewsOps {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("*/main")
	public String mainPage(Model model) {
//		List <News> newsList = newsService.latestList()
//		model.addAttribute("newsList", newsList);
//		String userRole = (String)model.asMap().get("userRole");
//		System.out.println("mainPage -> role = " + userRole); //FLAG
//		model.addAttribute("userRole", userRole);
//		model.addAttribute("userNick", userNick);
		model.addAttribute("presentation", "newsList");
		model.addAttribute("userData", new UserData());
		return "layouts/baseLayout";
	}

	@RequestMapping(value = {"/user/news", "/admin/news"})
	public String newsPage(@ModelAttribute("newsData") News news, Model model) {
		model.addAttribute("news", news);
//		model.addAttribute("userRole", userRole);
//		model.addAttribute("userNick", userNick);
		return "layouts/baseLayout";
	}
	
	@RequestMapping(value = {"/user/fetchNews", "/admin/fetchNews"})
	public String fetchNews(Model model) {
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/addNews")
	public String addNewsPage(Model model) {
		model.addAttribute("presentation", "addNews");
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}
	
	@RequestMapping("admin/editNews")
	public String editNewsPage(Model model) {
		model.addAttribute("presentation", "editNews");
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}
	
	@RequestMapping("/saveNews")
	public String saveNews(Model model) {
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}
	
	@RequestMapping("/deleteNews")
	public String deleteNews(Model model) {
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}
	
}
