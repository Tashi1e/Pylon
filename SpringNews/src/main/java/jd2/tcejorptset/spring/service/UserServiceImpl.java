package jd2.tcejorptset.spring.service;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jd2.tcejorptset.spring.bean.AuthorizedUserData;
import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.bean.UserRole;
import jd2.tcejorptset.spring.bean.UserToken;
import jd2.tcejorptset.spring.dao.UserDAO;
import jd2.tcejorptset.spring.global.constants.ErrorCode;
import jd2.tcejorptset.spring.global.constants.UsersRole;
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
		if (user == null) {
			throw new RuntimeException(ErrorCode.WRONG_LOGIN.getCode());
		}
		if (!bcryptor.similarity(password, user.getPassword())) {
			throw new RuntimeException(ErrorCode.WRONG_PASSWORD.getCode());
		}
		AuthorizedUserData userData = new AuthorizedUserData();
		userData.setUserRole(UsersRole.GUEST.getRole());
		userData.setUserRole(user.getUserRole().getRole());
		userData.setUserInfo(user.getUserInfo());
		return userData;
	}

	@Override
	@Transactional
	public AuthorizedUserData tokenSignIn(String selector, String validator) {
		UserToken innerToken = userDAO.getUserToken(selector, validator);
		AuthorizedUserData userData = new AuthorizedUserData();
		userData.setUserRole(UsersRole.GUEST.getRole());
		if (innerToken != null) {
			userData.setUserRole(innerToken.getUser().getUserRole().getRole());
			userData.setUserInfo(innerToken.getUser().getUserInfo());
		}
		return userData;
	}

	@Override
	@Transactional
	public void registration(User user, UserInfo userInfo) {
		if (userDAO.getUser(user.getLogin()) != null) {
			throw new RuntimeException(ErrorCode.LOGIN_EXISTS.getCode());
		}
		if (userDAO.emailExists(user.getLogin())) {
			throw new RuntimeException(ErrorCode.EMAIL_EXISTS.getCode());
		}
		userInfo.setUserRegDate(new Timestamp(System.currentTimeMillis()));
		user.setPassword(bcryptor.encrypt(user.getPassword()));
		user.setUserRole(new UserRole(UsersRole.USER.getRole()));
		user.setUserInfo(userInfo);
	}

	@Override
	@Transactional
	public UserToken saveUserToken(String login) {
		User user = userDAO.getUser(login);
		String selector = scryptor.encrypt(RandomStringUtils.randomAlphabetic(16));
		String validator = scryptor.encrypt(RandomStringUtils.randomAlphabetic(16));
		UserToken userToken = user.getUserToken();
		if (userToken == null) {
			userToken = new UserToken();
		}
		userToken.setSelector(selector);
		userToken.setValidator(validator);
		user.setUserToken(userToken);
		userDAO.saveUser(user);
		return userToken;
	}

}
