package com.neu.stepahead.dao;

import java.util.List;

import org.hibernate.Query;

import com.neu.stepahead.bean.Package;

public class PackageDAO extends DAO {

	public List<com.neu.stepahead.bean.Package> getPackages() {
		List<com.neu.stepahead.bean.Package> packages = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Package p");
			packages = query.list();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return packages;
	}

	public long addPackage(com.neu.stepahead.bean.Package pck) {
		long packageId = 0;
		begin();
		try {
			getSession().saveOrUpdate(pck);
			packageId = pck.getPackageId();
			commit();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return packageId;
	}

	public int deletePackage(long packageId) {
		int result = 0;
		try {
			Query query = getSession().createQuery("DELETE FROM Package p WHERE p.packageId = :packageId");
			query.setLong("packageId", packageId);
			result = query.executeUpdate();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return result;
	}

	public Package getPackageById(long packageId) {
		Package pack = null;
		begin();
		try {
			Query query = getSession().createQuery("FROM Package p WHERE p.packageId = :packageId");
			query.setLong("packageId", packageId);
			pack = (Package) query.uniqueResult();
		} catch (Exception ex) {
			rollback();
			System.out.println("Error Occurred: " + ex.getMessage());
		} finally {
			close();
		}
		return pack;
	}
}
