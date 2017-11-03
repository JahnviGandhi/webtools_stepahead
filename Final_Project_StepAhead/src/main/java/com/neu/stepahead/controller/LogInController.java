package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Company;
import com.neu.stepahead.bean.Domain;
import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.Position;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.CompanyDAO;
import com.neu.stepahead.dao.DomainDAO;
import com.neu.stepahead.dao.HrPersonDAO;
import com.neu.stepahead.dao.JobDAO;
import com.neu.stepahead.dao.PositionDAO;
import com.neu.stepahead.dao.UserDAO;

@Controller
@RequestMapping(value = "/login.htm")
public class LogInController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		if (action != null) {
			if (action.equals("logout")) {
				if (session != null) {
					session.invalidate();
				}
			}
		}
		mav.setViewName("home");
		mav.addObject("user", new User());

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView successLogIn(@ModelAttribute("user") User user, @RequestParam("userName") String userName,
			@RequestParam("password") String password, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		try {
			UserDAO userDao = new UserDAO();
			User validUser = userDao.authenticateUser(userName, password);
			HttpSession session = request.getSession();

			if (validUser != null) {

				if (validUser.isVerified()) {
					if (validUser.getRole().equals("JobSeeker")) {
						if (validUser.isCompleteProfile()) {
							long userId = validUser.getPersonId();
							JobDAO jobDao = new JobDAO();

							List<Job> relevantJobs = jobDao.getRelevantJobsForUser(userId);

							if (relevantJobs.size() > 0) {
								mav.addObject("relevantJobs", relevantJobs);
								mav.addObject("jobsFound", true);
							} else {
								mav.addObject("jobsFound", false);
							}
							mav.setViewName("jobSeekerHome");
						} else {
							PositionDAO positionDao = new PositionDAO();
							List<Position> positions = positionDao.getPositions();

							mav.addObject("positions", positions);
							mav.setViewName("jobSeekerProfile");
						}
						mav.addObject("loggedInUser", (JobSeeker) validUser);
					} else if (validUser.getRole().equals("HrPerson")) {
						CompanyDAO companyDao = new CompanyDAO();
						PositionDAO positionDao = new PositionDAO();
						DomainDAO domainDao = new DomainDAO();
						JobDAO jobDao = new JobDAO();

						if (validUser.isCompleteProfile()) {
							List<Job> jobList = jobDao.getJobsByPersonId(validUser.getPersonId());

							mav.addObject("jobPosts", jobList);
							mav.setViewName("hrPersonHome");
						} else {

							List<Company> companies = companyDao.getCompanies();

							List<Domain> domains = domainDao.getDomains();

							List<Position> positions = positionDao.getPositions();

							mav.setViewName("hrPersonProfile");
							mav.addObject("companies", companies);
							mav.addObject("domains", domains);
							mav.addObject("positions", positions);
						}
						mav.addObject("loggedInUser", (HrPerson) validUser);
					} else if (validUser.getRole().equals("Admin")) {
						if (validUser.isCompleteProfile()) {
							mav.setViewName("adminHome");
						} else {
							mav.setViewName("adminProfile");
						}
						mav.addObject("loggedInUser", validUser);
					}
					session.setAttribute("user", validUser);
				} else {
					session.invalidate();
					mav.setViewName("home");
					mav.addObject("user", validUser);
					mav.addObject("verified", false);
				}
			} else {
				mav.setViewName("home");
				mav.addObject("user", user);
				mav.addObject("authenticated", false);
			}

		} catch (Exception ex) {
			System.out.println("Error Occurred: " + ex.getMessage());
		}
		return mav;
	}
}
