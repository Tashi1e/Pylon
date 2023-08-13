package jd2.tcejorptset.spring.controller.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd2.tcejorptset.spring.bean.ErrorCode;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.bean.UserRole;
import jd2.tcejorptset.spring.controller.Command;
import jd2.tcejorptset.spring.service.UserService;
import jd2.tcejorptset.spring.service.ServiceException;
import jd2.tcejorptset.spring.service.ServiceProvider;
import jd2.tcejorptset.spring.util.cookies.CookiesOps;
import jd2.tcejorptset.spring.util.validation.UserDataValidation;
import jd2.tcejorptset.spring.util.validation.ValidationProvider;

@Deprecated
public class DoSignIn implements Command {
	
	private static final String FIRST_TIME_ENTER_ATTRIBUTE = "firstEnter";

	private final UserService service = ServiceProvider.getInstance().getUserService();
	private final UserDataValidation userAuthValidation = ValidationProvider.getInstance().getUserDataValidation();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String role = UserRole.GUEST.getRole();
		UserInfo userInfo = null;
		String selector = null;
		String validator = null;
//		System.out.println("DoSignIn start -> role = " + role); // TEST

		if (request.getSession().getAttribute(FIRST_TIME_ENTER_ATTRIBUTE) != null) {
			CookiesOps cookiesOps = new CookiesOps();
			selector = cookiesOps.findCookie(request, AttributeParamName.SELECTOR_PARAM);
			validator = cookiesOps.findCookie(request, AttributeParamName.VALIDATOR_PARAM);
//			System.out.println(selector + " " + validator); // TEST
		}

		try {
			if (selector != null && validator != null) {
				role = service.signInByToken(selector, validator);
				userInfo = service.getUserInfoByToken(selector, validator);
//				System.out.println(role); // TEST
			}
		
			if (role != null && !role.equals(UserRole.GUEST.getRole())) {
//				System.out.println(role); //TEST
//				System.out.println("DoSignIn -> update user token"); // TEST
				response = addCookie(response, service.updateUserToken(selector, validator));
			}
		} catch (ServiceException e) {
			// TODO only some logging
			e.printStackTrace();
	}

			String login = request.getParameter(AttributeParamName.JSP_LOGIN_PARAM);
			String password = request.getParameter(AttributeParamName.JSP_PASSWORD_PARAM);
			boolean checkbox = request.getParameter(AttributeParamName.JSP_REMEMBER_ME_PARAM) == null ? false : true;

			try {
			if (userAuthValidation.checkAUthData(login, password)) {
//				System.out.println("DoSignIn -> user Auth Validation"); // TEST
				role = service.signIn(login, password);
				userInfo = service.getUserInfo(login, password);
			}
			if (role != null && !role.equals(UserRole.GUEST.getRole())) {
				if (checkbox) {
					response = addCookie(response, service.addUserToken(login, password));
				}
				signinSuccessful(request, response, role, userInfo);
			} else {
//				System.out.println("NOT exception"); // TEST
				signinFailed(request, response);
			}

		} catch (ServiceException e) {
//			System.out.println("exception"); // TEST
				signinFailed(request, response);
		}
	}

	private void signinSuccessful(HttpServletRequest request, HttpServletResponse response, String role,
			UserInfo userInfo) throws ServletException, IOException {
//		System.out.println(role + " " + userInfo); //TEST
		request.getSession().removeAttribute(FIRST_TIME_ENTER_ATTRIBUTE);
		request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, true);
		request.getSession().setAttribute(AttributeParamName.JSP_ROLE_ATTRIBUTE, role);
		request.getSession().setAttribute(AttributeParamName.JSP_USER_INFO_ATTRIBUTE, userInfo);
		response.sendRedirect("controller?command=go_to_news_list");
	}

	private void signinFailed(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("DoSIgnIn -> signInFailed -> message = " + message); // TEST
		request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, false);
		if (request.getSession().getAttribute(FIRST_TIME_ENTER_ATTRIBUTE) == null) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.SIGN_IN.getCode());
		}
		request.getSession().removeAttribute(FIRST_TIME_ENTER_ATTRIBUTE);
		response.sendRedirect("error?command=go_to_base_page");
	}

	private HttpServletResponse addCookie(HttpServletResponse response, Map <String, String> token) {
//		System.out.println("add Cookie"); // TEST
		String tokenKey = token.get(AttributeParamName.SELECTOR_PARAM);
		String tokenValue = token.get(AttributeParamName.VALIDATOR_PARAM);
		response.addCookie(new Cookie(AttributeParamName.SELECTOR_PARAM, tokenKey));
		response.addCookie(new Cookie(AttributeParamName.VALIDATOR_PARAM, tokenValue));
		return response;
	}
}
