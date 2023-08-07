package jd2.tcejorptset.spring.controller.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class EncodingFilter extends HttpFilter {

	private static final long serialVersionUID = 4955833626067603819L;
	
	private final static String ENCODING = "utf-8";
	

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

			request.setCharacterEncoding(ENCODING);
			response.setCharacterEncoding(ENCODING);
		
			chain.doFilter(request, response);
	}


	@Override
	public void destroy() {
	}
}
