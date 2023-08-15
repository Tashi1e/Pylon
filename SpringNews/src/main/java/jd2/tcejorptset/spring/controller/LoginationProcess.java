package jd2.tcejorptset.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.dto.UserData;
import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;

@Controller
public class LoginationProcess {
	
	@Autowired
	private UserService service;

	@Autowired
	private Map <String, Object> attributesContainer; 
	
	@RequestMapping ("/login")
	public String showLoginPage(Model model) {
		attributesContainer.put("loginData", new User());
		attributesContainer.put("userData", new UserData());
		model.addAllAttributes(attributesContainer);
//		model.addAttribute("loginData", new User());
		return "layouts/baseLayout";
	}
	
	@RequestMapping ("/signin")
	public String doSignIn(@ModelAttribute("loginData") User user, Model model) {
		
//		System.out.printf("Login: %s\nPassword: %s\n", user.getLogin(), user.getPassword()); //TEST
		
					String role = service.signIn(user.getLogin(), user.getPassword());
					UserInfo userInfo = new UserInfo();
					userInfo.setNickName("Test Name");
					if(role != null) {
						attributesContainer.put("role", role);
						attributesContainer.put("userInfo", userInfo);
						attributesContainer.put("presentation", "newsList");
					}
					model.addAllAttributes(attributesContainer);
//					System.out.println(); //TEST
				
		return "redirect:/login";
	}
	
	@RequestMapping ("/signout")
	public String doSignOut(Model model) {
		attributesContainer.clear();
		model.addAllAttributes(attributesContainer);
		return "redirect:/login";
	}	
	
	@RequestMapping ("/register")
	public String doRegister (@ModelAttribute("userData") UserData userData) {
		System.out.println(userData); //TEST
	return "redirect:/login";	
	}
	
}
