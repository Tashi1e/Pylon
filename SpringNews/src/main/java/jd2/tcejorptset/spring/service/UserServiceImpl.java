package jd2.tcejorptset.spring.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jd2.tcejorptset.spring.dao.UserDAO;
import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;
import jd2.tcejorptset.spring.util.encrypt.Encryptor;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	Encryptor encryptor;

	@Override
	@Transactional
	public String signIn(String login, String password) {
		
		User user = userDAO.getUser(login);
		System.out.println(user.getUserRoleList().get(0).getRole());
		return "admin";
//		if(encryptor.compare(password, user.getPassword())) {
//			return user.getUserRoleList().get(0).getRole();	
//		} else {
//			return "guest";
//		}
	}

	@Override
	public String signInByToken(String selector, String validator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo getUserInfo(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo getUserInfoByToken(String selector, String validator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo getUserInfo(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registration(User user, UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> addUserToken(String selector, String validator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> updateUserToken(String selector, String validator) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
