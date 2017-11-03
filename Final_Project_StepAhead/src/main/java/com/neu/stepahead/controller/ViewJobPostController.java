package com.neu.stepahead.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.JobDAO;
import com.neu.stepahead.dao.JobSeekerDAO;

@Controller
@RequestMapping(value = "viewJobDetails.htm")
public class ViewJobPostController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView viewJobForm(HttpServletRequest request, @RequestParam("jobId") long jobId) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				System.out.println("session");
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					System.out.println("user");
					JobDAO jobDao = new JobDAO();
					JobSeekerDAO jsDao = new JobSeekerDAO();

					// long jobId =
					// Long.parseLong(request.getParameter("jobId"));
					System.out.println("JobId" + jobId);
					Job job = jobDao.getJobById(jobId);

					JobSeeker js = jsDao.getJobSeekerById(sUser.getPersonId());

					if (job != null) {
						mav.addObject("jobFound", true);
						mav.addObject("job", job);
					} else {
						mav.addObject("jobFound", false);
					}

					mav.addObject("loggedInUser", js);
					mav.setViewName("viewJobDetails");
				} else {
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
