package jd2.tcejorptset.spring.service;

import jd2.tcejorptset.spring.service.impl.NewsServiceImpl;
import jd2.tcejorptset.spring.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {}

	private final IUserService userService = new UserServiceImpl();
	private final INewsService newsService = new NewsServiceImpl();

	public INewsService getNewsService() {
		return newsService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

}
