package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Company;
import com.neu.stepahead.bean.Domain;
import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.Package;
import com.neu.stepahead.bean.Person;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.CompanyDAO;
import com.neu.stepahead.dao.DomainDAO;
import com.neu.stepahead.dao.HrPersonDAO;
import com.neu.stepahead.dao.JobDAO;
import com.neu.stepahead.dao.PackageDAO;
import com.neu.stepahead.helper.HelperClass;

@Controller
@RequestMapping(value = "/myPostedJobs.htm")
public class PostJobsController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request, @ModelAttribute("newJob") Job job) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					JobDAO jobDao = new JobDAO();
					CompanyDAO companyDao = new CompanyDAO();
					PackageDAO packageDao = new PackageDAO();
					DomainDAO domainDao = new DomainDAO();
					HrPersonDAO hrDao = new HrPersonDAO();

					HrPerson hrUser = hrDao.getHrById(user.getPersonId());

					List<Job> jobList = jobDao.getJobsByPersonId(user.getPersonId());
					System.out.println("no of jobs by person: " + jobList.size());

					List<Company> companyList = companyDao.getCompanies();
					List<Package> packageList = packageDao.getPackages();
					List<Domain> domainList = domainDao.getDomains();

					if (job.getPostedDate() != null) {
						mav.addObject("newJob", job);
						System.out.println("Assigned Existing Job:");
					} else {
						Job newJob = new Job();
						newJob.setCompany(companyDao.getCompanyById(hrUser.getCompany().getCompanyId()));
						newJob.setActive(true);

						mav.addObject("newJob", newJob);
						System.out.println("Assigned New Job: " + newJob.getPostedDate());
					}

					mav.addObject("loggedInUser", user);
					mav.addObject("jobPosts", jobList);
					mav.addObject("companies", companyList);
					mav.addObject("packages", packageList);
					mav.addObject("domains", domainList);

					mav.setViewName("myPostedJobs");
				} else {
					session.invalidate();
					mav.addObject("user", new User());
					mav.setViewName("home");
				}
			} else {
				mav.addObject("user", new User());
				mav.setViewName("home");
			}
		} catch (Exception ex) {
			System.out.println("Error Occurred:" + ex.getMessage());
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postJob(HttpServletRequest request, @ModelAttribute("newJob") Job newJob) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					String action = request.getParameter("action");
					CompanyDAO companyDao = new CompanyDAO();
					PackageDAO packageDAO = new PackageDAO();
					DomainDAO domainDao = new DomainDAO();
					HrPersonDAO hrDao = new HrPersonDAO();
					JobDAO jobDao = new JobDAO();

					if (action.equalsIgnoreCase("create")) {

						HrPerson hr = hrDao.getHrById(user.getPersonId());

						Job job = new Job();
						job.setActive(newJob.isActive());
						job.setAdditionalSkills(newJob.getAdditionalSkills());
						job.setCompany(companyDao.getCompanyById(hr.getCompany().getCompanyId()));
						job.setDomain(domainDao.getDomainByName(newJob.getDomain().getDomainName()));
						job.setJobLocation(newJob.getJobLocation());
						job.setJobPackage(packageDAO.getPackageById(newJob.getJobPackage().getPackageId()));
						job.setJobResponsibilities(newJob.getJobResponsibilities());
						job.setJobTitle(newJob.getJobTitle());
						job.setPostedDate(HelperClass.getCurrentDate());
						job.setSkills(newJob.getSkills());

						Person person = new Person();
						person.setPersonId(hr.getPersonId());
						person.setFirstName(hr.getFirstName());
						person.setLastName(hr.getLastName());
						person.setEmailId(hr.getEmailId());
						person.setContact(hr.getContact());
						person.setGender(hr.getGender());
						person.setVerified(hr.isVerified());
						person.setCompleteProfile(hr.isCompleteProfile());

						job.setPostedBy(person);

						long jobId = jobDao.createJob(job);

						if (jobId > 0) {
							List<Company> companyList = companyDao.getCompanies();
							List<Package> packageList = packageDAO.getPackages();
							List<Domain> domainList = domainDao.getDomains();
							List<Job> jobList = jobDao.getJobsByPersonId(hr.getPersonId());

							Job objJob = new Job();
							objJob.setCompany(companyDao.getCompanyById(hr.getCompany().getCompanyId()));
							objJob.setActive(true);

							mav.addObject("companies", companyList);
							mav.addObject("packages", packageList);
							mav.addObject("domains", domainList);
							mav.addObject("newJob", objJob);
							mav.addObject("loggedInUser", user);
							mav.addObject("jobPosts", jobList);
							mav.addObject("postedJob", true);

							mav.setViewName("myPostedJobs");
						}
					}

					if (action.equalsIgnoreCase("delete")) {
						long jobId = Long.parseLong(request.getParameter("jobId"));

						int result = jobDao.deleteJob(jobId);

						if (result > 0) {
							List<Company> companyList = companyDao.getCompanies();
							List<Package> packageList = packageDAO.getPackages();
							List<Domain> domainList = domainDao.getDomains();

							HrPerson hr = hrDao.getHrById(user.getPersonId());

							List<Job> jobList = jobDao.getJobsByPersonId(hr.getPersonId());

							Job objJob = new Job();
							objJob.setCompany(companyDao.getCompanyById(hr.getCompany().getCompanyId()));
							objJob.setActive(true);

							mav.addObject("companies", companyList);
							mav.addObject("packages", packageList);
							mav.addObject("domains", domainList);
							mav.addObject("newJob", objJob);
							mav.addObject("loggedInUser", user);
							mav.addObject("jobPosts", jobList);
							mav.addObject("deletedJob", true);

							mav.setViewName("myPostedJobs");
						}
					}

					if (action.equalsIgnoreCase("edit")) {
						long jobId = Long.parseLong(request.getParameter("jobId"));
						System.out.println(action + " " + jobId);
						Job job = jobDao.getJobById(jobId);

						mav.addObject("loggedInUser", user);
						mav.addObject("job", job);

						List<Company> companyList = companyDao.getCompanies();
						List<Package> packageList = packageDAO.getPackages();
						List<Domain> domainList = domainDao.getDomains();
						mav.addObject("companies", companyList);
						mav.addObject("packages", packageList);
						mav.addObject("domains", domainList);
						mav.setViewName("editJobPost");

					}
				} else {
					mav.setViewName("home");
					mav.addObject("user", new User());
				}
			} else {
				mav.setViewName("home");
				mav.addObject("user", new User());
			}
		} catch (Exception ex) {
			System.out.println("Error Occurred: " + ex.getMessage());
		}
		return mav;
	}
}
