package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value = "editJobPost.htm")
public class EditJobController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeEditForm(HttpServletRequest request, @RequestParam("jobId") long jobId,
			@ModelAttribute("job") Job job) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					CompanyDAO companyDao = new CompanyDAO();
					PackageDAO packageDAO = new PackageDAO();
					DomainDAO domainDao = new DomainDAO();
					HrPersonDAO hrDao = new HrPersonDAO();
					// JobDAO jobDao = new JobDAO();

					List<Company> companyList = companyDao.getCompanies();
					List<Package> packageList = packageDAO.getPackages();
					List<Domain> domainList = domainDao.getDomains();

					mav.addObject("companies", companyList);
					mav.addObject("packages", packageList);
					mav.addObject("domains", domainList);
					mav.addObject("loggedInUser", user);
					mav.addObject("job", job);

					mav.setViewName("myPostedJobs");

				} else {
					session.invalidate();
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

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView editPostedJob(HttpServletRequest request, @ModelAttribute("job") Job job) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					CompanyDAO companyDao = new CompanyDAO();
					PackageDAO packageDAO = new PackageDAO();
					DomainDAO domainDao = new DomainDAO();
					HrPersonDAO hrDao = new HrPersonDAO();
					JobDAO jobDao = new JobDAO();

					HrPerson hr = hrDao.getHrById(user.getPersonId());

					long jobId = Long.parseLong(request.getParameter("jobId"));

					Job updatedJob = new Job();
					updatedJob.setJobId(jobId);
					updatedJob.setActive(true);
					updatedJob.setAdditionalSkills(job.getAdditionalSkills());
					updatedJob.setCompany(companyDao.getCompanyById(hr.getCompany().getCompanyId()));
					updatedJob.setDomain(domainDao.getDomainByName(job.getDomain().getDomainName()));
					updatedJob.setJobLocation(job.getJobLocation());
					updatedJob.setJobPackage(packageDAO.getPackageById(job.getJobPackage().getPackageId()));
					updatedJob.setJobResponsibilities(job.getJobResponsibilities());
					updatedJob.setJobTitle(job.getJobTitle());
					updatedJob.setPostedDate(HelperClass.getCurrentDate());
					updatedJob.setSkills(job.getSkills());
					updatedJob.setPostedBy(job.getPostedBy());

					Person person = new Person();
					person.setPersonId(hr.getPersonId());
					person.setFirstName(hr.getFirstName());
					person.setLastName(hr.getLastName());
					person.setEmailId(hr.getEmailId());
					person.setContact(hr.getContact());
					person.setGender(hr.getGender());
					person.setVerified(hr.isVerified());
					person.setCompleteProfile(hr.isCompleteProfile());

					updatedJob.setPostedBy(person);

					long result = jobDao.createJob(updatedJob);

					if (result > 0) {
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
						mav.addObject("updatedJob", true);

						mav.setViewName("myPostedJobs");
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
