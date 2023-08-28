package jd2.tcejorptset.spring.service;

import jd2.tcejorptset.spring.dto.AuthorizedUserData;
import jd2.tcejorptset.spring.dto.UserData;
import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;
import jd2.tcejorptset.spring.entity.UserToken;

public interface UserService {
	
	AuthorizedUserData signIn (String login, String password);
	AuthorizedUserData tokenSignIn(String selector, String validator);
	UserData getUserData (String login);
	boolean registration (User user, UserInfo userInfo) throws ServiceException;
	
}
