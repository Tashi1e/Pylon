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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.UserData;
import jd2.tcejorptset.spring.bean.UserToken; 

@Controller
public class LoginationOps {

	@Autowired
	private UserService service;
	
	@RequestMapping("/autoSignIn")
	public String cookiesSignIn(@CookieValue(value = ConstantName.SELECTOR, required = false) Cookie selector,
			@CookieValue(value = ConstantName.VALIDATOR, required = false) Cookie validator, RedirectAttributes redirectAttributes) {
		
//		System.out.println("autoSignIn -> selector + validator = " + selector.getValue() + " + " + validator.getValue()); //FLAG
		
		if (selector == null || validator == null) {
			return "redirect:/guest/main";
		}
		AuthorizedUserData authorizedUserData = service.tokenSignIn(selector.getValue(), validator.getValue());
		if (authorizedUserData != null) {
			String userRole = authorizedUserData.getUserRole();
			String userNick = authorizedUserData.getUserNick(); 
			
			redirectAttributes.addFlashAttribute("userRole", userRole);
			redirectAttributes.addAttribute("userNick", userNick);
//			System.out.println("autoSignIn -> userNick = " + userNick); //FLAG
//			System.out.println("autoSignIn -> userRole = " + userRole); //FLAG
//			model.addAttribute("userRole", userRole);
//			model.addAttribute("userNick", userNick);
			return "redirect:/"+userRole+"/main";
		} else {
			return "redirect:/guest/main";
		}
	}

	@RequestMapping("*/signin") 
	public String doSignIn(@ModelAttribute("userData") UserData userData, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		AuthorizedUserData authorizedUserData = service.signIn(userData.getUser().getLogin(), userData.getUser().getPassword());
		String userRole = authorizedUserData.getUserRole();
		String userNick = authorizedUserData.getUserNick();

		if (userRole != null && userData.getRememberMeCheckBox() != null) {
				UserToken userToken = service.saveUserToken(userData.getUser().getLogin());
				response.addCookie(new Cookie(ConstantName.SELECTOR, userToken.getSelector()));
				response.addCookie(new Cookie(ConstantName.VALIDATOR, userToken.getValidator()));
		}
		redirectAttributes.addAttribute("userRole", userRole);
		redirectAttributes.addAttribute("userNick", userNick);
		System.out.println("doSignIn -> role = " + userRole); //FLAG
		return "redirect:/"+userRole+"/main";
	}

	@RequestMapping("*/signout")
	public String doSignOut() {
		return "redirect:/guest/main";
	}

	@RequestMapping("*/register")
	public String doRegister(@ModelAttribute("userData") UserData userData) {
//		System.out.println(userData.getUser()); //FLAG
//		System.out.println(userData.getUserInfo()); //FLAG
		try {
			service.registration(userData.getUser(), userData.getUserInfo());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return "redirect:/guest/main";
	}

}
