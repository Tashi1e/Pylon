package tcejorptset.spring.controller.impl;

import java.io.IOException;

import tcejorptset.spring.bean.ErrorCode;
import tcejorptset.spring.bean.News;
import tcejorptset.spring.bean.UserInfo;
import tcejorptset.spring.controller.Command;
import tcejorptset.spring.service.INewsService;
import tcejorptset.spring.service.ServiceException;
import tcejorptset.spring.service.ServiceProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class DoAddNews implements Command {
	
	INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(AttributeParamName.JSP_USER_INFO_ATTRIBUTE);
		String newsTitle = request.getParameter(AttributeParamName.JSP_NEWS_TITLE_PARAM);
		String newsBrief = request.getParameter(AttributeParamName.JSP_NEWS_BRIEF_PARAM);
		String newsContent = request.getParameter(AttributeParamName.JSP_NEWS_CONTENT_PARAM);
		Part ImagePart = request.getPart(AttributeParamName.JSP_NEWS_IMAGE_PART_PARAM);
		
		
		var news = new News();
		news.setTitle(newsTitle);
		news.setBrief(newsBrief);
		news.setContent(newsContent);
		news.setUserId(userInfo.getUserId());
		news.setImgPart(ImagePart);
		
			try {
				newsService.save(news);
				response.sendRedirect("controller?command=go_to_news_list");
			} catch (ServiceException e) {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.ADD_NEWS.getCode());
				response.sendRedirect("error?command=go_to_news_list");
				e.printStackTrace();
			}
	}

}
