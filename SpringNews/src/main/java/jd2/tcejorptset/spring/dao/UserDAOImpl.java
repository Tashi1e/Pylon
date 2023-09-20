package jd2.tcejorptset.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jd2.tcejorptset.spring.bean.User;
import jd2.tcejorptset.spring.bean.UserInfo;
import jd2.tcejorptset.spring.bean.UserToken;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUser(String login) {
		try {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <User> theQuery = currentSession.createQuery("from User where login = :userLogin", User.class);
		theQuery.setParameter("userLogin", login);
		return theQuery.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException("noLogin", e);
		}
	}
	
	@Override
	public UserToken getUserToken(String selector, String validator) {
		UserToken token = null;
		try {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserToken> theQuery = currentSession.createQuery
				("from UserToken where selector = :userSelector and validator = :userValidator", UserToken.class);
		theQuery.setParameter("userSelector", selector);
		theQuery.setParameter("userValidator", validator);
		token = theQuery.uniqueResult();
//		if (token != null) { //FLAG
//		System.out.println("getUserToken -> selector + validator = " + token.getSelector() + " + " + token.getValidator()); //FLAG
//		} //FLAG
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return token;
	}
	
	@Override
	public boolean emailExists(String login) {
		try {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserInfo> theQuery = currentSession.createQuery("from UserInfo where email = :userEmail", UserInfo.class);
		theQuery.setParameter("userEmail", login);
		return theQuery.uniqueResult()!=null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void saveUser(User user) {
		try {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void addOrUpdateToken(UserToken userToken) {
		try {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(userToken);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
