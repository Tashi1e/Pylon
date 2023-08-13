package jd2.tcejorptset.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.service.IUserService;
import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.ServiceProvider;

@Controller
public class LoginationProcess {
	
	private final IUserService service = ServiceProvider.getInstance().getUserService();

	private final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private Map <String, Object> attributesContainer = context.getBean("attributesContainer", HashMap.class);
	
	@RequestMapping ("/login")
	public String showLoginPage(Model model) {
		attributesContainer.put("loginData", new User());
		model.addAllAttributes(attributesContainer);
//		model.addAttribute("loginData", new User());
		return "layouts/baseLayout";
	}
	
	@RequestMapping ("/signin")
	public String doSignIn(@ModelAttribute("loginData") User user, Model model) {
		
		System.out.printf("Login: %s\nPassword: %s\n", user.getLogin(), user.getPassword()); //TEST
				try {
					String role = service.signIn(user.getLogin(), user.getPassword());
					UserInfo userInfo = service.getUserInfo(user.getLogin(), user.getPassword());
					if(role != null && userInfo != null) {
//						model.addAttribute("role", role);
//						model.addAttribute("presentation", "newsList");
//						model.addAttribute(userInfo);
						attributesContainer.put("role", role);
						attributesContainer.put("userInfo", userInfo);
						attributesContainer.put("presentation", "newsList");
					}
					model.addAllAttributes(attributesContainer);
					
//					System.out.println(); //TEST
					
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return "layouts/baseLayout";
	}
	
	@RequestMapping ("/signout")
	public String doSignOut(@ModelAttribute("loginData") User user, Model model) {
		attributesContainer.clear();
		model.addAllAttributes(attributesContainer);
		return "layouts/baseLayout";
	}

	
	
}
