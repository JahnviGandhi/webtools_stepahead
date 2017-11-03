package com.neu.stepahead.dao;

import org.hibernate.HibernateException;

import com.neu.stepahead.bean.Person;

public class PersonDAO extends DAO {
	
	public PersonDAO() {
    }
	
	public long addPerson(Person person) {
		long personId = 0;

		try {
			begin();
			getSession().save(person);
			personId = person.getPersonId();

			commit();
		} catch (HibernateException ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return personId;
	}

}
