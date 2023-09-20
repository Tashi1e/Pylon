package jd2.tcejorptset.spring.service;

import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.bean.UserToken;

public interface UserService {
	
	AuthorizedUserData signIn (String login, String password);
	AuthorizedUserData tokenSignIn(String selector, String validator);
	UserToken saveUserToken (String login);  
	void registration (User user, UserInfo userInfo);
	
}
