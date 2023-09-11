package jd2.tcejorptset.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jd2.tcejorptset.spring.service.NewsService;
import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.bean.UserData;

@Controller
@SessionAttributes("authData")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@ModelAttribute("authData")
	public AuthorizedUserData authData() {
		return new AuthorizedUserData();
	}

	@RequestMapping("*/main")
	public String mainPage(@ModelAttribute("authData") AuthorizedUserData authData, Model model) {
		List<News> newsList = newsService.latestList();
		model.addAttribute("newsList", newsList);
		System.out.println("mainPage -> role = " + authData.getUserRole()); // FLAG
		model.addAttribute("userRole", authData.getUserRole());
		model.addAttribute("userNick", authData.getUserNick());
		model.addAttribute("presentation", "newsList");
		model.addAttribute("userData", new UserData());
		model.addAttribute("newsData", new News());
		return "layouts/baseLayout";
	}

	@RequestMapping(value = { "/user/news", "/admin/news" })
	public String newsPage(@ModelAttribute("newsId") int newsId,
			@ModelAttribute("authData") AuthorizedUserData authData, Model model) {
		News news = newsService.findById(newsId);
//		String author = news.getUserInfo().getFirstName() + " " + news.getUserInfo().getLastName();
		model.addAttribute("news", news);
//		model.addAttribute("author", author);
		model.addAttribute("userRole", authData.getUserRole());
		model.addAttribute("userNick", authData.getUserNick());
		model.addAttribute("presentation", "viewNews");
		model.addAttribute("userData", new UserData());
		return "layouts/baseLayout";
	}

	@RequestMapping(value = { "/user/findNews", "/admin/findNews" })
	public String fetchNews(Model model) {
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/addNews")
	public String addNewsPage(@ModelAttribute("authData") AuthorizedUserData authData, Model model) {
		model.addAttribute("userRole", authData.getUserRole());
		model.addAttribute("userNick", authData.getUserNick());
		model.addAttribute("presentation", "addNews");
		model.addAttribute("userData", new UserData());
		model.addAttribute("newsData", new News());
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/editNews")
	public String editNewsPage(Model model) {
		model.addAttribute("presentation", "editNews");
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/saveNews")
	public String saveNews(@ModelAttribute("newsData") News newsData, Model model) {
		newsService.saveOrUpdate(newsData);
		return "redirect:*/main";
	}

	@RequestMapping("admin/deleteNews")
	public String deleteNews(Model model) {
//		model.addAttribute("role", userRole);
		return "layouts/baseLayout";
	}

}
