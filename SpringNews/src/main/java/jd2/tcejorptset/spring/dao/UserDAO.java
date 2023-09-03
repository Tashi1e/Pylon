package jd2.tcejorptset.spring.dao;

import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserToken;

public interface UserDAO {
	
	User getUser (String login);
	UserToken getUserToken(String selector);
	boolean emailExists(String login);
	boolean saveUser (User user);
	boolean addOrUpdateToken (UserToken userToken);
}
