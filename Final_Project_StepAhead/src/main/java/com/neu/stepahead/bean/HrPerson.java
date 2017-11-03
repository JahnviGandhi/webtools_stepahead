package com.neu.stepahead.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tblHrPerson")
@PrimaryKeyJoinColumn(name = "personId")
public class HrPerson extends User {

	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * @Column(name = "hrPersonId", nullable = false, unique = true) private
	 * long hrPersonId;
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private Company company;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "positionId")
	private Position currentPosition;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "domainId")
	private Domain domain;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Blog> myBlogs = new HashSet<Blog>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "postedBy")
	private Set<Job> jobsPosted = new HashSet<Job>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Saved_Profile", joinColumns = {
			@JoinColumn(name = "hrPersonId", referencedColumnName = "personId") }, inverseJoinColumns = @JoinColumn(name = "jobSeekerId", referencedColumnName = "personId"))
	private Set<JobSeeker> savedProfiles = new HashSet<JobSeeker>();

	public HrPerson() {

	}

	/*
	 * public long getHrPersonId() { return hrPersonId; }
	 * 
	 * public void setHrPersonId(long hrPersonId) { this.hrPersonId =
	 * hrPersonId; }
	 */

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Set<Blog> getMyBlogs() {
		return myBlogs;
	}

	public void setMyBlogs(Set<Blog> myBlogs) {
		this.myBlogs = myBlogs;
	}

	public Set<Job> getJobsPosted() {
		return jobsPosted;
	}

	public void setJobsPosted(Set<Job> jobsPosted) {
		this.jobsPosted = jobsPosted;
	}

	public Set<JobSeeker> getSavedProfiles() {
		return savedProfiles;
	}

	public void setSavedProfiles(Set<JobSeeker> savedProfiles) {
		this.savedProfiles = savedProfiles;
	}

}
