package jd2.tcejorptset.spring.service;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jd2.tcejorptset.spring.dao.UserDAO;
import jd2.tcejorptset.spring.dto.AuthorizedUserData;
import jd2.tcejorptset.spring.dto.UserData;
import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;
import jd2.tcejorptset.spring.entity.UserRole;
import jd2.tcejorptset.spring.entity.UserToken;
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
		}
		if (user.getUserInfo() != null) {
			userData.setUserNick(user.getUserInfo().getNickName());
		}
		return userData;
	}
	
	@Override
	@Transactional
	public AuthorizedUserData tokenSignIn(String selector, String validator) {

		UserToken innerToken = userDAO.getUserToken(selector);
		AuthorizedUserData userData = new AuthorizedUserData();
		if (innerToken != null && scryptor.compare(selector, innerToken.getSelector()) 
				&& scryptor.compare(validator, innerToken.getValidator())) {
			userData.setUserRole(innerToken.getUser().getUserRole().getRole());
		}
		if (innerToken.getUser().getUserInfo() != null) {
			userData.setUserNick(innerToken.getUser().getUserInfo().getNickName());
		}
		return userData;
	}

	@Override
	@Transactional
	public UserData getUserData(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public boolean registration(User user, UserInfo userInfo) throws ServiceException {
		try {
			if (userDAO.getUser(user.getLogin()) != null) {
				throw new ServiceException("Login Exists");
			}
			if (userDAO.emailExists(user.getLogin())) {
				throw new ServiceException("Email Exists");
			}
			userInfo.setUserRegDate(new Timestamp(System.currentTimeMillis()));
			user.setPassword(bcryptor.encrypt(user.getPassword()));
			user.setUserRole(new UserRole("USER_ROLE"));
			user.setUserInfo(userInfo);
			return userDAO.saveUser(user);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
