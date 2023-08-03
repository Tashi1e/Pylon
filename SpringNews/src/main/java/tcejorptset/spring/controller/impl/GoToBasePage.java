package tcejorptset.spring.controller.impl;

import java.io.IOException;
import java.util.List;

import tcejorptset.spring.bean.ErrorCode;
import tcejorptset.spring.bean.News;
import tcejorptset.spring.controller.Command;
import tcejorptset.spring.service.INewsService;
import tcejorptset.spring.service.ServiceException;
import tcejorptset.spring.service.ServiceProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> latestNews;
//		System.out.println(request.getAttribute("firstEnter")); //TEST
		try {
//				System.out.println("GoToBasePage - > errorMessage attribute = " + request.getSession().getAttribute("errorMessage")); //TEST
				latestNews = newsService.latestList();
				request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, latestNews);
				request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.LATEST_NEWS_LITS.getCode());
			response.sendRedirect("error?command=go_to_error_page");
			e.printStackTrace();
		}

	}

}
