package jd2.tcejorptset.spring.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd2.tcejorptset.spring.bean.ErrorCode;
import jd2.tcejorptset.spring.bean.News;
import jd2.tcejorptset.spring.controller.Command;
import jd2.tcejorptset.spring.service.NewsService;
import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.ServiceProvider;

@Deprecated
public class GoToBasePage implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

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
