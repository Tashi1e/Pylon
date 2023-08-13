package jd2.tcejorptset.spring.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd2.tcejorptset.spring.bean.UserRole;
import jd2.tcejorptset.spring.controller.Command;

@Deprecated
public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, false);
			request.getSession().setAttribute(AttributeParamName.JSP_ROLE_ATTRIBUTE, UserRole.GUEST.getRole());
			request.getSession().removeAttribute(AttributeParamName.JSP_NICK_NAME_ATTRIBUTE);
			response.sendRedirect("controller?command=go_to_base_page");
		
	}

}
