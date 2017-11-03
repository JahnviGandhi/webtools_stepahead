package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;

public class JobDAO extends DAO {

	// fetch all the jobs posted by person
	public List<Job> getJobsByPersonId(long personId) {
		List<Job> jobsList = null;
		begin();
		try {
			Query query = getSession().createQuery(
					"FROM Job j WHERE j.postedBy IN (FROM Person p WHERE p.personId = :personId) ORDER BY postedDate DESC, jobTitle ASC");
			query.setLong("personId", personId);
			jobsList = query.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return jobsList;
	}

	public long createJob(Job job) {
		long jobId = 0;
		begin();
		try {
			getSession().saveOrUpdate(job);
			jobId = job.getJobId();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return jobId;
	}

	public Job getJobById(long jobId) {
		Job job = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Job j WHERE j.jobId = :jobId");
			query.setLong("jobId", jobId);
			job = (Job) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred - getJobById: " + ex.getMessage());
		} finally {
			close();
		}

		return job;
	}

	public int deleteJob(long jobId) {
		int result = 0;
		begin();
		try {
			Query query = getSession().createQuery("DELETE FROM Job j WHERE j.jobId = :jobId");
			query.setLong("jobId", jobId);
			result = query.executeUpdate();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return result;
	}

	public List<Job> getRelevantJobsForUser(long userId) {
		JobSeekerDAO jsDao = new JobSeekerDAO();
		List<Job> jobList = null;
		String[] keywords = null;
		begin();
		try {
			keywords = jsDao.getKeywordsForUser(userId);
			Criteria jobCrit = getSession().createCriteria(Job.class);

			Disjunction disjunction = Restrictions.disjunction();

			for (String key : keywords) {
				Criterion skill = Restrictions.ilike("skills", key, MatchMode.ANYWHERE);
				Criterion additionalSkill = Restrictions.ilike("additionalSkills", key, MatchMode.ANYWHERE);
				Criterion title = Restrictions.ilike("jobTitle", key, MatchMode.ANYWHERE);
				disjunction.add(skill);
				disjunction.add(additionalSkill);
				disjunction.add(title);
			}

			jobCrit.add(Restrictions.eq("isActive", true));
			jobCrit.add(disjunction);
			jobCrit.addOrder(Order.desc("postedDate"));
			jobList = jobCrit.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occured - getRelevantJobsForUser: " + ex.getMessage());
		} finally {
			close();
		}
		return jobList;
	}

	public List<JobSeeker> getApplicants(long jobId) {
		List<JobSeeker> applicants = null;
		begin();

		try {
			Criteria jsCriteria = getSession().createCriteria(JobSeeker.class);
			Criteria jCriteria = jsCriteria.createCriteria("jobsApplied");
			jCriteria.add(Restrictions.eq("jobId", jobId));
			applicants = jsCriteria.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return applicants;
	}
}
