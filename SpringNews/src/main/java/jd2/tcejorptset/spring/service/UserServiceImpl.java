package jd2.tcejorptset.spring.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserData;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.bean.UserRole;
import jd2.tcejorptset.spring.bean.UserToken;
import jd2.tcejorptset.spring.dao.UserDAO;
import jd2.tcejorptset.spring.util.encrypt.Encryptor;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	@Qualifier("BCrypt") 
	Encryptor bcryptor;

	@Autowired
	@Qualifier("SCrypt")
	Encryptor scryptor;

	@Override
	@Transactional
	public AuthorizedUserData signIn(String login, String password) {

		User user = userDAO.getUser(login);
		AuthorizedUserData userData = new AuthorizedUserData();
		if (user != null && bcryptor.compare(password, user.getPassword())) {
			userData.setUserRole(user.getUserRole().getRole());
			System.out.println("role = " + user.getUserRole().getRole()); // FLAG
		}
		if (user.getUserInfo() != null) {
			userData.setUserNick(user.getUserInfo().getNickName()); 
		}
		return userData;
	}

	@Override
	@Transactional
	public AuthorizedUserData tokenSignIn(String selector, String validator) {
		System.out.println("tokenSignIn -> selector + validator = " + selector + " + " + validator); //FLAG
		UserToken innerToken = userDAO.getUserToken(selector);
		if (innerToken != null) { //FLAG
		System.out.println("tokenSignIn -> innerToken -> selector + validator = " + innerToken.getSelector() + " + " + innerToken.getValidator()); //FLAG
		} //FLAG
		AuthorizedUserData userData = new AuthorizedUserData();
		
		if (innerToken != null) {
			System.out.println("tokenSignIn -> role = " + innerToken.getUser().getUserRole().getRole());// FLAG
			userData.setUserRole(innerToken.getUser().getUserRole().getRole());
		}
		if (innerToken != null && innerToken.getUser().getUserInfo() != null) {
			userData.setUserNick(innerToken.getUser().getUserInfo().getNickName());
		}
		return userData;
	}

	@Override
//	@Transactional
	public UserData getUserData(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean registration(User user, UserInfo userInfo) {
//		try {
//			if (userDAO.getUser(user.getLogin()) != null) {
//				throw new ServiceException("Login Exists");
//			}
//			if (userDAO.emailExists(user.getLogin())) {
//				throw new ServiceException("Email Exists");
//			}
		userInfo.setUserRegDate(new Timestamp(System.currentTimeMillis()));
		user.setPassword(bcryptor.encrypt(user.getPassword()));
		user.setUserRole(new UserRole("USER_ROLE"));
		user.setUserInfo(userInfo);
		return userDAO.saveUser(user);
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		}
	}

	@Override
	@Transactional
	public UserToken saveUserToken(String login) {
		User user = userDAO.getUser(login);
		String selector = scryptor.encrypt(RandomStringUtils.randomAlphabetic(16));
		String validator = scryptor.encrypt(RandomStringUtils.randomAlphabetic(16));
		UserToken userToken = user.getUserToken();
		if (userToken==null) {
			userToken = new UserToken();
		}
		userToken.setSelector(selector);
		userToken.setValidator(validator);
		user.setUserToken(userToken);
		userDAO.saveUser(user);
		return userToken;
	}

}
