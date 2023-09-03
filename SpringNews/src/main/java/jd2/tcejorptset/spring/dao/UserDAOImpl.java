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
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User where login = :userLogin", User.class);
		theQuery.setParameter("userLogin", login);
		return theQuery.uniqueResult();
	}
	
	@Override
	public UserToken getUserToken(String selector) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserToken> theQuery = currentSession.createQuery("from UserToken where selector = :userSelector", UserToken.class);
		theQuery.setParameter("userSelector", selector);
		UserToken token = theQuery.uniqueResult();
		if (token != null) { //FLAG
		System.out.println("getUserToken -> selector + validator = " + token.getSelector() + " + " + token.getValidator()); //FLAG
		} //FLAG
		return token;
	}
	
	@Override
	public boolean emailExists(String login) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserInfo> theQuery = currentSession.createQuery("from UserInfo where email = :userEmail", UserInfo.class);
		theQuery.setParameter("userEmail", login);
		return theQuery.uniqueResult()!=null;
	}


	@Override
	public boolean saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
		return true;
	}
	
	@Override
	public boolean addOrUpdateToken(UserToken userToken) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(userToken);
		return true;
	}
}
