package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.CompanyDAO;
import com.neu.stepahead.dao.DomainDAO;
import com.neu.stepahead.dao.JobDAO;
import com.neu.stepahead.dao.PositionDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession(false);

		if (session != null) {
			User user = (User) session.getAttribute("user");

			if (user != null) {

				if (user.getRole().equals("JobSeeker")) {
					long userId = user.getPersonId();
					JobDAO jobDao = new JobDAO();

					List<Job> relevantJobs = jobDao.getRelevantJobsForUser(userId);

					if (relevantJobs.size() > 0) {
						mav.addObject("relevantJobs", relevantJobs);
						mav.addObject("jobsFound", true);
					} else {
						mav.addObject("jobsFound", false);
					}

					mav.setViewName("jobSeekerHome");
					mav.addObject("loggedInUser", (JobSeeker) user);
				} else if (user.getRole().equals("HrPerson")) {

					JobDAO jobDao = new JobDAO();
					List<Job> jobList = jobDao.getJobsByPersonId(user.getPersonId());

					mav.addObject("jobPosts", jobList);
					mav.setViewName("hrPersonHome");
					mav.addObject("loggedInUser", (HrPerson) user);
				} else if (user.getRole().equals("Admin")) {
					mav.setViewName("adminHome");
					mav.addObject("loggedInUser", user);
				}
			} else {
				session.invalidate();
				mav.setViewName("home");
				mav.addObject("user", new User());
			}
		} else {

			mav.setViewName("home");
			mav.addObject("user", new User());
		}
		return mav;
	}
}
