package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.stepahead.bean.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public long addUser(User user) {
		begin();
		long personId = 0;
		try {
			getSession().saveOrUpdate(user);
			personId = user.getPersonId();
			commit();
		} catch (HibernateException ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return personId;
	}

	public User getUser(long userId) {
		begin();
		User user = null;
		try {
			Query query = getSession().createQuery("FROM User u WHERE u.personId = :personId");
			query.setLong("personId", userId);
			user = (User) query.uniqueResult();
		} catch (HibernateException ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return user;
	}

	public User authenticateUser(String userName, String password) {
		User user = null;

		try {
			Query query = getSession()
					.createQuery("FROM User u WHERE u.userName = :userName AND u.password = :password");
			query.setString("userName", userName);
			query.setString("password", password);
			List<User> users = query.list();

			if (users != null) {
				if (users.size() == 1) {
					user = users.get(0);
				}
			}

		} catch (HibernateException ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return user;
	}
}
