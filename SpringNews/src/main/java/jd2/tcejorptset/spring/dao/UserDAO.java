package jd2.tcejorptset.spring.dao;

import jd2.tcejorptset.spring.entity.User;

public interface UserDAO {
	
	User getUser (String login);
	boolean saveUser (User user);
}
