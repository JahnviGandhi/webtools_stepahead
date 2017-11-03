package com.neu.stepahead.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblCompany")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "companyId", nullable = false, unique = true)
	private long companyId;

	@Column(name = "companyName", nullable = false)
	private String companyName;

	/*
	 * @Column(name = "industry") private String industry;
	 */
	public Company() {

	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/*
	 * public String getIndustry() { return industry; }
	 * 
	 * public void setIndustry(String industry) { this.industry = industry; }
	 */

	@Override
	public String toString() {
		return this.companyName;
	}
}
