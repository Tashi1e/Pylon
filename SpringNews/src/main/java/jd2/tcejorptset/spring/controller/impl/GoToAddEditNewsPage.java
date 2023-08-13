package jd2.tcejorptset.spring.controller.impl;

import java.io.IOException;

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
public class GoToAddEditNewsPage implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		News news = new News();
		String presentation = request.getParameter(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE);
//		System.out.println(presentation); //TEST
		if (presentation.equals("editNews")) {
			try {
				int id  = Integer.parseInt(request.getParameter(AttributeParamName.JSP_NEWS_ID_PARAM)) ;
				news = newsService.findById(id);
			} catch (ServiceException e) {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.FETCH_NEWS.getCode());
				response.sendRedirect("error?command=go_to_error_page");
				e.printStackTrace();
			}
		}
		request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, news);
		request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, presentation);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

	}

}
