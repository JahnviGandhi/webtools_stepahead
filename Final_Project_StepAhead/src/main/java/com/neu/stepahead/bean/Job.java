package com.neu.stepahead.bean;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblJob")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jobId", nullable = false, unique = true)
	private long jobId;

	@Column(name = "jobTitle")
	private String jobTitle;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private Company company;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "packageId")
	private Package jobPackage;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personId")
	private Person postedBy;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "domainId")
	private Domain domain;

	@Column(name = "jobResponsibilities")
	private String jobResponsibilities;

	@Column(name = "skills")
	private String skills;

	@Column(name = "additionalSkills")
	private String additionalSkills;

	@Column(name = "postedDate")
	private Date postedDate;

	@Column(name = "isActive")
	private boolean isActive;

	@Column(name = "jobLocation")
	private String jobLocation;

	@ManyToMany(mappedBy = "jobsApplied", fetch = FetchType.EAGER)
	private Set<JobSeeker> applicants = new HashSet<JobSeeker>();

	public Job() {

	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Package getJobPackage() {
		return jobPackage;
	}

	public void setJobPackage(Package jobPackage) {
		this.jobPackage = jobPackage;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Person getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(Person postedBy) {
		this.postedBy = postedBy;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public String getJobResponsibilities() {
		return jobResponsibilities;
	}

	public void setJobResponsibilities(String jobResponsibilities) {
		this.jobResponsibilities = jobResponsibilities;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getAdditionalSkills() {
		return additionalSkills;
	}

	public void setAdditionalSkills(String additionalSkills) {
		this.additionalSkills = additionalSkills;
	}

	public Set<JobSeeker> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<JobSeeker> applicants) {
		this.applicants = applicants;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	@Override
	public String toString() {
		return this.jobTitle;
	}
}
