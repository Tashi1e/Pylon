package jd2.tcejorptset.spring.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jd2.tcejorptset.spring.service.NewsService;
import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.UserData;
import jd2.tcejorptset.spring.bean.UserToken;

@Controller
public class LoginationOps {

	@Autowired
	private UserService service;
	
	@Autowired
	private NewsService newsService;

	@Autowired
	private Map<String, Object> attributesContainer;

	@RequestMapping("/autoSignIn")
	public String cookiesSignIn(@CookieValue(value = ConstantName.SELECTOR, required = false) Cookie selector,
			@CookieValue(value = ConstantName.VALIDATOR, required = false) Cookie validator, Model model) {
		
		System.out.println("autoSignIn -> selector + validator = " + selector.getValue() + " + " + validator.getValue()); //FLAG
		
		if (selector == null || validator == null) {
			return "redirect:/login";
		}
		AuthorizedUserData userData = service.tokenSignIn(selector.getValue(), validator.getValue());
		if (userData != null) {
			String role = userData.getUserRole();
			System.out.println("autoSignIn -> role = " + role); //FLAG
			String nickName = userData.getUserNick();
			System.out.println("autoSignIn -> nickName = " + nickName); //FLAG

			attributesContainer.put("role", role);
			attributesContainer.put("userNick", nickName);
			attributesContainer.put("presentation", "newsList");
			model.addAllAttributes(attributesContainer);
			
//			model.addAttribute("role", role);
//			model.addAttribute("userNick", nickName);
//			model.addAttribute("presentation", "newsList");
			return "redirect:/main";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = { "/login", "/main" })
	public String showLoginPage(Model model) {
		
		model.addAllAttributes(attributesContainer);
		model.addAttribute("userData", new UserData());
		return "layouts/baseLayout";
	}

	@RequestMapping("/signin")
	public String doSignIn(@ModelAttribute("userData") UserData userData, Model model, HttpServletResponse response) {

//		System.out.printf("Login: %s\nPassword: %s\n", user.getLogin(), user.getPassword()); //FLAG

		AuthorizedUserData authorizedUserData = service.signIn(userData.getUser().getLogin(),
				userData.getUser().getPassword());
		String role = authorizedUserData.getUserRole();
		String nickName = authorizedUserData.getUserNick();

		if (role != null) {
			attributesContainer.put("role", role);
			attributesContainer.put("presentation", "newsList");
//			model.addAttribute("role", role);
//			model.addAttribute("presentation", "newsList");

			if (userData.getRememberMeCheckBox() != null) {
				UserToken userToken = service.saveUserToken(userData.getUser().getLogin());
				response.addCookie(new Cookie(ConstantName.SELECTOR, userToken.getSelector()));
				response.addCookie(new Cookie(ConstantName.VALIDATOR, userToken.getValidator()));
			}
		}
		if (nickName != null) {
			attributesContainer.put("userNick", nickName);
//			model.addAttribute("userNick", nickName);
		}
//					System.out.println(); //FLAG
		model.addAllAttributes(attributesContainer);
		return "redirect:/main";
	}

	@RequestMapping("/signout")
	public String doSignOut() {
		attributesContainer.clear();
		return "redirect:/login";
	}

	@RequestMapping("/register")
	public String doRegister(@ModelAttribute("userData") UserData userData) {
//		System.out.println(userData.getUser()); //FLAG
//		System.out.println(userData.getUserInfo()); //FLAG
		try {
			service.registration(userData.getUser(), userData.getUserInfo());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return "redirect:/login";
	}

}
