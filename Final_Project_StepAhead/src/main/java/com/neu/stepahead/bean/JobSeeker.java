package com.neu.stepahead.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "tblJobSeeker")
@PrimaryKeyJoinColumn(name = "personId")
public class JobSeeker extends User {

	@Column(name = "resume")
	private String resume;

	@Transient
	private CommonsMultipartFile resumeFile;

	@Column(name = "keywords")
	private String keywords;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "positionId")
	private Position currentPosition;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Blog> myBlogs = new HashSet<Blog>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "JobSeeker_Job", joinColumns = { @JoinColumn(name = "personId") }, inverseJoinColumns = {
			@JoinColumn(name = "jobId") })
	private Set<Job> jobsApplied = new HashSet<Job>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Saved_Job", joinColumns = {
			@JoinColumn(name = "personId") }, inverseJoinColumns = @JoinColumn(name = "jobId"))
	private Set<Job> jobsSaved = new HashSet<Job>();

	public JobSeeker() {

	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Set<Blog> getMyBlogs() {
		return myBlogs;
	}

	public void setMyBlogs(Set<Blog> myBlogs) {
		this.myBlogs = myBlogs;
	}

	public Set<Job> getJobsApplied() {
		return jobsApplied;
	}

	public void setJobsApplied(Set<Job> jobsApplied) {
		this.jobsApplied = jobsApplied;
	}

	public Set<Job> getJobsSaved() {
		return jobsSaved;
	}

	public void setJobsSaved(Set<Job> jobsSaved) {
		this.jobsSaved = jobsSaved;
	}

	public CommonsMultipartFile getResumeFile() {
		return resumeFile;
	}

	public void setResumeFile(CommonsMultipartFile resumeFile) {
		this.resumeFile = resumeFile;
	}
}
