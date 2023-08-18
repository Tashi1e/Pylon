package jd2.tcejorptset.spring.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jd2.tcejorptset.spring.dao.UserDAO;
import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;
import jd2.tcejorptset.spring.entity.UserRole;
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
		if (user != null && encryptor.compare(password, user.getPassword())) {
//			System.out.println(user.getUserRoleList().get(0).getRole()); // FLAG
//			return user.getUserRoleList().get(0).getRole();
			return "admin";
		} else {
			return "guest";
		}

	}

	@Override
	@Transactional
	public boolean registration(User user, UserInfo userInfo) {
		try {
			if (userDAO.getUser(user.getLogin())==null) {
				List<UserRole> list = new LinkedList<UserRole>();
				list.add(new UserRole("user"));
				userInfo.setUserRegDate(new Timestamp(System.currentTimeMillis()));
				user.setPassword(encryptor.encrypt(user.getPassword()));
//				userInfo.setUser(user);
//				user.setUserRoleList(list);
				user.setUserInfo(userInfo);
				return userDAO.saveUser(user);
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
