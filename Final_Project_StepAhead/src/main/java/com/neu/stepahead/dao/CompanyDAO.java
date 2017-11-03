package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.Query;

import com.neu.stepahead.bean.Company;

public class CompanyDAO extends DAO {
	public CompanyDAO() {

	}

	public List<Company> getCompanies() {
		List<Company> companies = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Company c");
			companies = query.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return companies;
	}

	public Company getCompanyByName(String comName) {
		Company company = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Company c WHERE c.companyName = :companyName");
			query.setString("companyName", comName);
			company = (Company) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return company;
	}

	public long addCompany(Company company) {
		long companyId = 0;
		begin();
		try {
			getSession().saveOrUpdate(company);
			companyId = company.getCompanyId();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return companyId;
	}

	public int deleteCompany(long companyId) {
		int result = 0;
		begin();

		try {
			Query query = getSession().createQuery("DELETE FROM Company c WHERE c.companyId = :companyId");
			query.setLong("companyId", companyId);
			result = query.executeUpdate();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		}

		return result;
	}

	public Company getCompanyById(long companyId) {
		Company company = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Company c WHERE c.companyId = :companyId");
			query.setLong("companyId", companyId);
			company = (Company) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return company;
	}

	public Company getCompanyByJobId(long jobId) {
		Company company = null;
		begin();
		try {
			Query query = getSession().createQuery(
					"FROM Company c WHERE c.companyId = (SELECT companyId FROM JOb j WHERE j.jobId = :jobId");
			query.setLong("jobId", jobId);
			company = (Company) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return company;
	}
}
