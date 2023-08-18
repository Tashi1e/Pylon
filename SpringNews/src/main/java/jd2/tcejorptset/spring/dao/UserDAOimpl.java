package jd2.tcejorptset.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jd2.tcejorptset.spring.entity.User;

@Repository
public class UserDAOimpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User getUser(String login) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User where login = :userLogin", User.class);
		theQuery.setParameter("userLogin", login);

		return theQuery.uniqueResult();
	}

	@Override
	public boolean saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
		return true;
	}

}
