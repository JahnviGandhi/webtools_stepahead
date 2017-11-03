package com.neu.stepahead.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.HrPersonDAO;
import com.neu.stepahead.dao.JobDAO;

@Controller
@RequestMapping(value = "/viewJobApplicants.htm")
public class ViewJobApplicantsController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewApplicants(HttpServletRequest request, @RequestParam("id") long jobId) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					HrPersonDAO hrDao = new HrPersonDAO();
					HrPerson hr = hrDao.getHrById(sUser.getPersonId());

					JobDAO jobDao = new JobDAO();

					Job job = jobDao.getJobById(jobId);
					List<JobSeeker> applicants = jobDao.getApplicants(jobId);

					mav.addObject("loggedInUser", hr);
					mav.addObject("applicants", applicants);
					mav.addObject("job", job);
					mav.setViewName("viewJobApplicants");

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
