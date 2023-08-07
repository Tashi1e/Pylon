package jd2.tcejorptset.spring.controller.listeners;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LoginByTokenListener implements HttpSessionListener{
	
	private static final String JSP_USER_ACTIVE_ATTRIBUTE = "user_active";
	private static final String FIRST_TIME_ENTER_ATTRIBUTE = "firstEnter";

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute(FIRST_TIME_ENTER_ATTRIBUTE, "yes");
		se.getSession().setAttribute(JSP_USER_ACTIVE_ATTRIBUTE, false);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

	
	
}
