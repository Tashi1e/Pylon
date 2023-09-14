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
import jd2.tcejorptset.spring.bean.UserInfo;

@Controller
@SessionAttributes(names = { "role", "user" })
public class NewsController {

	private final static String PAGE_PRESENTATION_ATTRIBUTE = "presentation";
	private final static String PRESENTATION_NEWS_LIST = "newsList";
	private final static String PRESENTATION_VIEW_NEWS = "viewNews";
	private final static String PRESENTATION_ADD_NEWS = "addNews";
	private final static String PRESENTATION_EDIT_NEWS = "editNews";
	private final static String NEWS_ATTRIBUTE = "news";
	private final static String NEWS_LIST_ATTRIBUTE = "newsList";
	private final static String USER_DATA_ATTRIBUTE = "userData";
	private final static String NEWS_DATA_ATTRIBUTE = "newsData";

	@Autowired
	private NewsService newsService;

	@ModelAttribute("role")
	public String gtRole() {
		return "";
	}

	@ModelAttribute("user")
	public UserInfo gtUserInfo() {
		return new UserInfo();
	}

	@RequestMapping("*/main")
	public String mainPage(Model model) {
		List<News> newsList = newsService.latestList();
		model.addAttribute(NEWS_LIST_ATTRIBUTE, newsList);
//		System.out.println("mainPage -> role = " + authData.getUserRole()); // FLAG
		model.addAttribute(PAGE_PRESENTATION_ATTRIBUTE, PRESENTATION_NEWS_LIST);
		model.addAttribute(USER_DATA_ATTRIBUTE, new UserData());
		model.addAttribute(NEWS_DATA_ATTRIBUTE, new News());
		return "layouts/baseLayout";
	}

	@RequestMapping(value = { "/user/news", "/admin/news" })
	public String newsPage(@ModelAttribute("newsId") int newsId, Model model) {
		News news = newsService.findById(newsId);
		model.addAttribute(NEWS_ATTRIBUTE, news);
		model.addAttribute(PAGE_PRESENTATION_ATTRIBUTE, PRESENTATION_VIEW_NEWS);
		model.addAttribute(USER_DATA_ATTRIBUTE, new UserData());
		return "layouts/baseLayout";
	}

	@RequestMapping(value = { "/user/findNews", "/admin/findNews" })
	public String fetchNews(Model model) {
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/addNews")
	public String addNewsPage(Model model) {
		model.addAttribute(PAGE_PRESENTATION_ATTRIBUTE, PRESENTATION_ADD_NEWS);
		model.addAttribute(USER_DATA_ATTRIBUTE, new UserData());
		model.addAttribute(NEWS_DATA_ATTRIBUTE, new News());
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/editNews")
	public String editNewsPage(@ModelAttribute("newsId") int newsId, Model model) {
		News news = newsService.findById(newsId);
		model.addAttribute(PAGE_PRESENTATION_ATTRIBUTE, PRESENTATION_EDIT_NEWS);
		model.addAttribute(USER_DATA_ATTRIBUTE, new UserData());
		model.addAttribute(NEWS_DATA_ATTRIBUTE, news);
		return "layouts/baseLayout";
	}

	@RequestMapping("admin/saveNews")
	public String saveNews(@ModelAttribute("newsData") News newsData, @ModelAttribute("user") UserInfo userInfo,
			Model model) {
//		System.out.println("saveNews -> title = " + newsData.getTitle()); //FLAG
		newsData.setUserInfo(userInfo);
		newsService.saveOrUpdate(newsData);
		return "redirect:/admin/main";
	}

	@RequestMapping("admin/deleteNews")
	public String deleteNews(@ModelAttribute("news") News news, Model model) {
		if (news.getMarkedNewsId() != null) {
			newsService.delete(news.getMarkedNewsId());
		} 
		return "redirect:/admin/main";
	}

}
