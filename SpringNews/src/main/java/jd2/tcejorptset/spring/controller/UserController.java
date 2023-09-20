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

import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.UserData;
import jd2.tcejorptset.spring.bean.UserToken;

@Controller
@SessionAttributes(names = {"role", "user", "locale"})
public class UserController {

	private final static String USER_ROLE_ATTRIBUTE = "role";
	private final static String USER_INFO_ATTRIBUTE = "user";
	private final static String SELECTOR_NAME = "selector";
	private final static String VALIDATOR_NAME = "validator";
	
	@Autowired
	private UserService service;
	
//	@ModelAttribute("locale")
//	public String gtLocale() {
//		return "";
//	}
	
	@RequestMapping("/autoSignIn")
	public String cookiesSignIn(@CookieValue(value = "selector", required = false) Cookie selector,
			@CookieValue(value = "validator", required = false) Cookie validator, Model model) {
		if (selector == null || validator == null) {
			return "redirect:/guest/main";
		}
		AuthorizedUserData authorizedUserData = null;
			authorizedUserData = service.tokenSignIn(selector.getValue(), validator.getValue());
		if (authorizedUserData != null) {
			model.addAttribute(USER_ROLE_ATTRIBUTE, authorizedUserData.getUserRole());
			model.addAttribute(USER_INFO_ATTRIBUTE, authorizedUserData.getUserInfo());
			return "redirect:/"+authorizedUserData.getUserRole()+"/main";
		} else {
			return "redirect:/guest/main";
		}
	}

	@RequestMapping("*/signin") 
	public String doSignIn(@ModelAttribute("userData") UserData userData, HttpServletResponse response, Model model, RedirectAttributes redirectAttr) {
		AuthorizedUserData authorizedUserData = service.signIn(userData.getUser().getLogin(), userData.getUser().getPassword());
		if (authorizedUserData.getUserRole() != null && userData.getRememberMeCheckBox() != null) {
				UserToken userToken = null;
					userToken = service.saveUserToken(userData.getUser().getLogin());
				response.addCookie(new Cookie(SELECTOR_NAME, userToken.getSelector()));
				response.addCookie(new Cookie(VALIDATOR_NAME, userToken.getValidator()));
		}
		model.addAttribute(USER_ROLE_ATTRIBUTE, authorizedUserData.getUserRole());
		model.addAttribute(USER_INFO_ATTRIBUTE, authorizedUserData.getUserInfo());
		return "redirect:/"+authorizedUserData.getUserRole()+"/main";
	}

	@RequestMapping("*/signout")
	public String doSignOut(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/guest/main";
	}

	@RequestMapping("*/register")
	public String doRegister(@ModelAttribute("userData") UserData userData, RedirectAttributes redirectAttr) {
			service.registration(userData.getUser(), userData.getUserInfo());
		return "redirect:/guest/main";
	}

}
