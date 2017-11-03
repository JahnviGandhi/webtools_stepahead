package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.Query;

import com.neu.stepahead.bean.Domain;

public class DomainDAO extends DAO {
	public DomainDAO() {

	}

	public List<Domain> getDomains() {
		List<Domain> domains = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Domain d");
			domains = query.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}

		return domains;
	}

	public Domain getDomainByName(String domainName) {
		Domain domain = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Domain d WHERE d.domainName = :domainName");
			query.setString("domainName", domainName);
			domain = (Domain) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return domain;
	}

	public long addDomain(Domain domain) {
		long domainId = 0;
		begin();

		try {
			getSession().saveOrUpdate(domain);
			domainId = domain.getDomainId();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return domainId;
	}

	public int deleteDomain(long domainId) {
		int result = 0;
		begin();

		try {
			Query query = getSession().createQuery("DELETE FROM Domain d WHERE d.domainId = :domainId");
			query.setLong("domainId", domainId);
			result = query.executeUpdate();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		}

		return result;
	}
}
