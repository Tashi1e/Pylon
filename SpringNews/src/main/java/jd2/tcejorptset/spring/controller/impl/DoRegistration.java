package jd2.tcejorptset.spring.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd2.tcejorptset.spring.bean.ErrorCode;
import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.controller.Command;
import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.ServiceProvider;

@Deprecated
public class DoRegistration implements Command {

	private final UserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName;
		String lastName;
		String nickName;
		String email;
		String login;
		String password;

		firstName = request.getParameter(AttributeParamName.JSP_FIRST_NAME_PARAM);
		lastName = request.getParameter(AttributeParamName.JSP_LAST_NAME_PARAM);
		nickName = request.getParameter(AttributeParamName.JSP_NICK_NAME_ATTRIBUTE);
		email = request.getParameter(AttributeParamName.JSP_EMAIL_PARAM);
		login = request.getParameter(AttributeParamName.JSP_LOGIN_PARAM);
		password = request.getParameter(AttributeParamName.JSP_PASSWORD_PARAM);

		User user = new User();
		user.setLogin(login);
		user.setPassword(password);

		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setNickName(nickName);
		userInfo.setEmail(email);

		try {
			if (service.registration(user, userInfo))
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE,
						ErrorCode.REGISTRATION_SUCCESSFUL.getCode());
			response.sendRedirect("error?command=go_to_base_page");
		} catch (ServiceException e) {
			if (e.getMessage() != null) {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, e.getMessage().replaceFirst(".+: ", ""));
				System.out.println(e.getMessage()); //TEST
			} else {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE,
						ErrorCode.REGISTRATION_FAILED.getCode());
			}
			response.sendRedirect("error?command=go_to_base_page");
			e.printStackTrace();
		}

	}

}
