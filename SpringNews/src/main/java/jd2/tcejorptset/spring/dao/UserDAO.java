package jd2.tcejorptset.spring.dao;

import java.util.Map;

import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;

public interface UserDAO {
	
	User getUser (String login);
	
}
