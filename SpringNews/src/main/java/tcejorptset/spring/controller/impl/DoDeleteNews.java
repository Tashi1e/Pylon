package tcejorptset.spring.controller.impl;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tcejorptset.spring.bean.ErrorCode;
import tcejorptset.spring.controller.Command;
import tcejorptset.spring.service.INewsService;
import tcejorptset.spring.service.ServiceException;
import tcejorptset.spring.service.ServiceProvider;

public class DoDeleteNews implements Command {
	
	INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String [] newsId = request.getParameterValues(AttributeParamName.JSP_NEWS_ID_PARAM);
			try {
				if(newsId != null) {
				int [] id = Stream.of(newsId)
						  .mapToInt(Integer::parseInt)
						  .toArray();
				newsService.delete(id);
				}
				response.sendRedirect("controller?command=go_to_news_list");
			} catch (ServiceException e) {
				request.getSession().setAttribute (AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.DELETE_NEWS.getCode());
				response.sendRedirect("error?command=go_to_news_list");
				e.printStackTrace();
			}
		}
	}

