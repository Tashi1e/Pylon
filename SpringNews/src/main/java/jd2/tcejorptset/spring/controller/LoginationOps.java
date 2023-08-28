package jd2.tcejorptset.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.util.encrypt.Encryptor;
import jd2.tcejorptset.spring.dto.AuthorizedUserData;
import jd2.tcejorptset.spring.dto.UserData;
import jd2.tcejorptset.spring.entity.User;

@Controller
public class LoginationOps {

	@Autowired
	private UserService service;
	
	@Autowired
	@Qualifier("SCrypt")
	Encryptor scryptor;
	
	@RequestMapping("/autoSignIn")
	public String cookiesSignIn (@CookieValue(value = "selector") Cookie selector, 
			@CookieValue(value = "validator") Cookie validator, Model model) {
		if (selector == null || validator == null) {
			return "layouts/baseLayout";
		}
		AuthorizedUserData userData = service.tokenSignIn(selector.getValue(), validator.getValue());
		if (userData != null) {
			String role = userData.getUserRole();
			String nickName = userData.getUserNick();
			
			model.addAttribute("role", role);
			model.addAttribute("userNick", nickName);
			model.addAttribute("presentation", "newsList");
			return "redirect:/login";
		}else {
			return "layouts/baseLayout";
		}
	}

	@RequestMapping("/login")
	public String showLoginPage(Model model) {
		
		model.addAttribute("loginData", new User());
		model.addAttribute("userData", new UserData());
		return "layouts/baseLayout";
	}

	@RequestMapping("/signin")
	public String doSignIn(@ModelAttribute("loginData") User user, Model model) {

//		System.out.printf("Login: %s\nPassword: %s\n", user.getLogin(), user.getPassword()); //FLAG

		AuthorizedUserData userData = service.signIn(user.getLogin(), user.getPassword());
		String role = userData.getUserRole();
		String nickName = userData.getUserNick();
		
		model.addAttribute("role", role);
		model.addAttribute("userNick", nickName);
		model.addAttribute("presentation", "newsList");
//					System.out.println(); //FLAG

		return "redirect:/login";
	}

	@RequestMapping("/signout")
	public String doSignOut() {
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
