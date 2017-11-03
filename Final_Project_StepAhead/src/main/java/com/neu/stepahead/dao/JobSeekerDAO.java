package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;

public class JobSeekerDAO extends DAO {

	public JobSeekerDAO() {
	}

	public long addJobSeeker(JobSeeker jobSeeker) {
		long jobSeekerId = 0;
		begin();
		try {
			getSession().saveOrUpdate(jobSeeker);
			jobSeekerId = jobSeeker.getPersonId();

			commit();
		} catch (HibernateException ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return jobSeekerId;
	}

	public String getResumeName(long personId) {
		String name = "";
		try {
			Query query = getSession().createQuery("SELECT resume FROM JobSeeker j WHERE j.personId = :personId");
			query.setLong("personId", personId);
			name = (String) query.uniqueResult();

		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred - getResumeName: " + ex.getMessage());
		} finally {
			close();
		}
		return name;
	}

	public String[] getKeywordsForUser(long userId) {
		String[] keywords = null;
		begin();
		try {
			Query query = getSession()
					.createQuery("SELECT js.keywords FROM JobSeeker js WHERE js.personId = :personId");
			query.setLong("personId", userId);
			String keyword = (String) query.uniqueResult();
			keywords = keyword.trim().split(",");
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred - getKeyWordsForUser: " + ex.getMessage());
		} finally {
			close();
		}

		return keywords;
	}

	public JobSeeker getJobSeekerById(long userId) {
		JobSeeker js = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM JobSeeker js WHERE js.personId = :userId");
			query.setLong("userId", userId);
			js = (JobSeeker) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return js;
	}

	public int addAppliedJob(long personId, long jobId) {
		int result = 0;
		begin();
		try {
			String sqlQuery = "INSERT INTO jobseeker_job VALUES (:personId, :jobId)";
			Query query = getSession().createSQLQuery(sqlQuery);
			query.setLong("personId", personId);
			query.setLong("jobId", jobId);
			// getSession().save(jobSeeker);
			result = query.executeUpdate();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return result;
	}

	public List<Job> getJobsApplied(long personId) {
		List<Job> appliedJobs = null;
		begin();
		try {
			Criteria jCriteria = getSession().createCriteria(Job.class);
			Criteria jsCriteria = jCriteria.createCriteria("applicants");
			jsCriteria.add(Restrictions.eq("personId", personId));

			appliedJobs = jCriteria.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return appliedJobs;
	}
}
