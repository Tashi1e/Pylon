package jd2.tcejorptset.spring.dao;

import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserToken;

public interface UserDAO {
	
	User getUser (String login);
	UserToken getUserToken(String selector);
	boolean emailExists(String login);
	boolean saveUser (User user);
	boolean addOrUpdateToken (UserToken userToken);
}
