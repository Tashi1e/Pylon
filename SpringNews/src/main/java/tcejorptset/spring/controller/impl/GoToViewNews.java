package tcejorptset.spring.controller.impl;

import java.io.IOException;

import tcejorptset.spring.bean.ErrorCode;
import tcejorptset.spring.bean.News;
import tcejorptset.spring.bean.UserInfo;
import tcejorptset.spring.controller.Command;
import tcejorptset.spring.service.INewsService;
import tcejorptset.spring.service.IUserService;
import tcejorptset.spring.service.ServiceException;
import tcejorptset.spring.service.ServiceProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		UserInfo userInfo;
		String author;

		String id = request.getParameter(AttributeParamName.JSP_NEWS_ID_PARAM);

		if (id == null) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.FETCH_NEWS.getCode());
			response.sendRedirect("controller?command=go_to_error_page");
		}

		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, news);
			request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, "viewNews");

			userInfo = userService.getUserInfo(news.getUserId());
			author = userInfo.getFirstName() + " " + userInfo.getLastName();
			request.setAttribute(AttributeParamName.JSP_NEWS_AUTHOR_ATTRIBUTE, author);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE,
					ErrorCode.FETCH_NEWS.getCode());
			response.sendRedirect("controller?command=go_to_error_page");
			e.printStackTrace();
		}

	}

}
