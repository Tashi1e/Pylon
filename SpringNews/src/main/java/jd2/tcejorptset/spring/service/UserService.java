package jd2.tcejorptset.spring.service;

import java.util.Map;

import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserData;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.bean.UserToken;

public interface UserService {
	
	AuthorizedUserData signIn (String login, String password);
	AuthorizedUserData tokenSignIn(String selector, String validator);
	UserToken saveUserToken (String login);  
	boolean registration (User user, UserInfo userInfo) throws ServiceException;
	
}
