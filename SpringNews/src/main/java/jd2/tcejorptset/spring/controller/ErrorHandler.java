package jd2.tcejorptset.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO There MUST be Logger
		String errorCode = null;
		if (ex.getMessage() != null) {
			errorCode = ex.getMessage();
			System.out.println("ErrorHandler -> errorCode = " + errorCode); //FLAG
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		model.addObject("errorCode", errorCode);
		return model;
	}

}
