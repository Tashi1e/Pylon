package jd2.tcejorptset.spring.service;

import jd2.tcejorptset.spring.service.impl.NewsServiceImpl;
import jd2.tcejorptset.spring.service.impl.UserServiceImpl;

@Deprecated
public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {}

	private final UserService userService = new UserServiceImpl();
	private final NewsService newsService = new NewsServiceImpl();

	public NewsService getNewsService() {
		return newsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

}
