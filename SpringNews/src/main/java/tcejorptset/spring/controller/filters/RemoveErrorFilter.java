package tcejorptset.spring.controller.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class RemoveErrorFilter extends HttpFilter {

	private static final long serialVersionUID = 4955833626067603819L;
	
	private final static String ERROR_CODE_ATTRIBUTE = "errorCode";
	

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (request.getSession().getAttribute(ERROR_CODE_ATTRIBUTE) != null) { 
			request.getSession().removeAttribute(ERROR_CODE_ATTRIBUTE);
		}
		chain.doFilter(request, response);
	}


	@Override
	public void destroy() {
	}
}
