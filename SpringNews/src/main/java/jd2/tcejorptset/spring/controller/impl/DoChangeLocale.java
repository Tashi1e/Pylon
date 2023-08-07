package jd2.tcejorptset.spring.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd2.tcejorptset.spring.controller.Command;

public class DoChangeLocale implements Command {
	
	private final static String CURRENT_PAGE_URL_ATTRIBUTE = "pageURL";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String locale = request.getParameter(AttributeParamName.LOCALE_NAME_PARAM_ATTRIBUTE);
		String previousPageURL = (String) request.getSession().getAttribute(CURRENT_PAGE_URL_ATTRIBUTE);
		request.getSession(true).setAttribute(AttributeParamName.LOCALE_NAME_PARAM_ATTRIBUTE, locale);
//		System.out.println(previousPageURL); //TEST
		
		if (previousPageURL != null) {
			response.sendRedirect(previousPageURL);
		} else {
			response.sendRedirect("controller?command=go_to_base_page");
		}
	}
}
