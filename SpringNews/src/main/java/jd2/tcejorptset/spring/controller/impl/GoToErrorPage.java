package jd2.tcejorptset.spring.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd2.tcejorptset.spring.controller.Command;

@Deprecated
public class GoToErrorPage implements Command{
	
	private String errorCode;

	public GoToErrorPage (){}
	
	public GoToErrorPage (String errorCode){
		this.errorCode = errorCode;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE) == null) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, errorCode);
		} else {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, "Unknown Error");
		}
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}
}
