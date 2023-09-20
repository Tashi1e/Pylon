package jd2.tcejorptset.spring.dao;

import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserToken;

public interface UserDAO {
	
	User getUser (String login);
	UserToken getUserToken(String selector, String validator);
	boolean emailExists(String login);
	void saveUser (User user);
	void addOrUpdateToken (UserToken userToken);
}
