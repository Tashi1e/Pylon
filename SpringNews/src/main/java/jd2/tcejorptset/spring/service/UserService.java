package jd2.tcejorptset.spring.service;

import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;

public interface UserService {
	
	String signIn (String login, String password);
//	String signInByToken (String selector, String validator);
//	UserInfo getUserInfo (String login, String password);
//	UserInfo getUserInfoByToken (String selector, String validator);
//	UserInfo getUserInfo (Integer userId);
	boolean registration (User user, UserInfo userInfo);
//	Map <String, String> addUserToken(String selector, String validator);
//	Map<String, String> updateUserToken(String selector, String validator);
}
