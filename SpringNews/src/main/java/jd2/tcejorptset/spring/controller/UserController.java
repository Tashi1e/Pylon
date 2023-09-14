package jd2.tcejorptset.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.UserData;
import jd2.tcejorptset.spring.bean.UserToken; 

@Controller
@SessionAttributes(names = {"role", "user"})
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/autoSignIn")
	public String cookiesSignIn(@CookieValue(value = ConstantName.SELECTOR, required = false) Cookie selector,
			@CookieValue(value = ConstantName.VALIDATOR, required = false) Cookie validator, Model model) {
		if (selector == null || validator == null) {
			return "redirect:/guest/main";
		}
		AuthorizedUserData authorizedUserData = service.tokenSignIn(selector.getValue(), validator.getValue());
		if (authorizedUserData != null) {
			model.addAttribute("role", authorizedUserData.getUserRole());
			model.addAttribute("user", authorizedUserData.getUserInfo());
			return "redirect:/"+authorizedUserData.getUserRole()+"/main";
		} else {
			return "redirect:/guest/main";
		}
	}

	@RequestMapping("*/signin") 
	public String doSignIn(@ModelAttribute("userData") UserData userData, HttpServletResponse response, Model model) {
		AuthorizedUserData authorizedUserData = service.signIn(userData.getUser().getLogin(), userData.getUser().getPassword());

		if (authorizedUserData.getUserRole() != null && userData.getRememberMeCheckBox() != null) {
				UserToken userToken = service.saveUserToken(userData.getUser().getLogin());
				response.addCookie(new Cookie(ConstantName.SELECTOR, userToken.getSelector()));
				response.addCookie(new Cookie(ConstantName.VALIDATOR, userToken.getValidator()));
		}
		model.addAttribute("role", authorizedUserData.getUserRole());
		model.addAttribute("user", authorizedUserData.getUserInfo());
		return "redirect:/"+authorizedUserData.getUserRole()+"/main";
	}

	@RequestMapping("*/signout")
	public String doSignOut(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/guest/main";
	}

	@RequestMapping("*/register")
	public String doRegister(@ModelAttribute("userData") UserData userData) {
		try {
			service.registration(userData.getUser(), userData.getUserInfo());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return "redirect:/guest/main";
	}

}
