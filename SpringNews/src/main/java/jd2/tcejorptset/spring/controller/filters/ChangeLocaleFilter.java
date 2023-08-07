package jd2.tcejorptset.spring.controller.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ChangeLocaleFilter extends HttpFilter {

	private static final long serialVersionUID = 7451266832053915507L;
	
	private final static String CHANGE_PAGE_COMMAND_REGEX = "(command=go_to).+";
	private final static String CURRENT_PAGE_URL_ATTRIBUTE = "pageURL";

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		String query = request.getQueryString();
		String pageURL = request.getRequestURL().toString()+"?"+query;
//		System.out.println(pageURL); //TEST
		
		if(query!=null && query.matches(CHANGE_PAGE_COMMAND_REGEX)) {
		request.getSession().setAttribute(CURRENT_PAGE_URL_ATTRIBUTE, pageURL);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
