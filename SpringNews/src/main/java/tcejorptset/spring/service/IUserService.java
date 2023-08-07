package tcejorptset.spring.service;

import java.util.Map;

import tcejorptset.spring.bean.User;
import tcejorptset.spring.bean.UserInfo;

public interface IUserService {
	
	String signIn (String login, String password) throws ServiceException;
	String signInByToken (String selector, String validator) throws ServiceException;
	UserInfo getUserInfo (String login, String password) throws ServiceException;
	UserInfo getUserInfoByToken (String selector, String validator) throws ServiceException;
	UserInfo getUserInfo (Integer userId) throws ServiceException;
	boolean registration (User user, UserInfo userInfo) throws ServiceException;
	Map <String, String> addUserToken(String selector, String validator) throws ServiceException;
	Map<String, String> updateUserToken(String selector, String validator) throws ServiceException;

}
