package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.JobSeekerDAO;

@Controller
@RequestMapping(value = "/myJobs.htm")
public class ViewAppliedJobsController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showMyJobs(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				System.out.println("session");
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					System.out.println("user");
					JobSeekerDAO jsDao = new JobSeekerDAO();
					JobSeeker jobSeeker = jsDao.getJobSeekerById(sUser.getPersonId());

					System.out.println("Job Seeker: " + jobSeeker.getFirstName());

					List<Job> appliedJobs = jsDao.getJobsApplied(sUser.getPersonId());
					System.out.println("applied jobs no: " + appliedJobs.size());
					mav.addObject("appliedJobs", appliedJobs);
					mav.addObject("loggedInUser", jobSeeker);
					mav.setViewName("myJobs");
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
			System.out.println("Error Occurred: " + ex.getMessage());
		}
		return mav;
	}
}
