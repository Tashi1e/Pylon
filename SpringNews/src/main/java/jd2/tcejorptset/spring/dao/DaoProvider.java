package jd2.tcejorptset.spring.dao;

import jd2.tcejorptset.spring.dao.impl.NewsDAOimpl;
import jd2.tcejorptset.spring.dao.impl.UserDAOimpl;

@Deprecated
public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final UserDAO userDao = new UserDAOimpl();
	private final NewsDAO newsDAO = new NewsDAOimpl();
	
	
	private DaoProvider() {
	}
	
	
	public UserDAO getUserDao() {
		return userDao;
	}
	
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
}
