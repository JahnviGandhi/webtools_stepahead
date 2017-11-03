package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.JobSeeker;

public class HrPersonDAO extends DAO {

	public HrPersonDAO() {

	}

	public long addHrPerson(HrPerson hrPerson) {
		long hrPersonId = 0;
		begin();
		try {
			getSession().saveOrUpdate(hrPerson);
			hrPersonId = hrPerson.getPersonId();

			commit();
		} catch (HibernateException ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return hrPersonId;
	}

	public HrPerson getHrById(long personId) {
		HrPerson hr = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM HrPerson h WHERE h.personId = :personId");
			query.setLong("personId", personId);
			hr = (HrPerson) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return hr;
	}

	public int saveProfile(long hrPersonId, long jobSeekerId) {
		int result = 0;
		begin();
		try {
			String sqlQuery = "INSERT INTO saved_profile VALUES (:hrPersonId, :jobSeekerId)";
			Query query = getSession().createSQLQuery(sqlQuery);
			query.setLong("hrPersonId", hrPersonId);
			query.setLong("jobSeekerId", jobSeekerId);
			result = query.executeUpdate();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred - saveProfile: " + ex.getMessage());
		} finally {
			close();
		}
		return result;
	}

	public List<Long> getSavedProfiles(long hrPersonId) {
		List<Long> savedProfiles = null;
		begin();
		try {
			String sqlQuery = "SELECT jobSeekerId FROM saved_profile WHERE hrPersonId = :hrPersonId";
			Query query = getSession().createSQLQuery(sqlQuery);
			query.setLong("hrPersonId", hrPersonId);
			/*
			 * Criteria hrCriteria =
			 * getSession().createCriteria(HrPerson.class); Criteria jsCriteria
			 * = hrCriteria.createCriteria("savedProfiles");
			 * hrCriteria.add(Restrictions.eq("personId", hrPersonId));
			 * savedProfiles = jsCriteria.list();
			 */
			savedProfiles = query.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred - getSavedProfiles: " + ex.getMessage());
		} finally {
			close();
		}
		return savedProfiles;
	}
}
