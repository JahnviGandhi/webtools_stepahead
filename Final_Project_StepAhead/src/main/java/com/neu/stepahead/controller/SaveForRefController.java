package com.neu.stepahead.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.HrPersonDAO;
import com.neu.stepahead.dao.JobSeekerDAO;

@Controller
@RequestMapping(value = "/saveProfile.htm")
public class SaveForRefController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveProfile(HttpServletRequest request, @RequestParam("personId") long jobSeekerId) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					HrPersonDAO hrDao = new HrPersonDAO();
					HrPerson hr = hrDao.getHrById(sUser.getPersonId());
					JobSeekerDAO jsDao = new JobSeekerDAO();
					JobSeeker jobSeeker = jsDao.getJobSeekerById(jobSeekerId);

					Set<JobSeeker> saved = hr.getSavedProfiles();

					if (saved.contains(jobSeeker)) {
						mav.addObject("alredySaved", true);
					} else {
						int result = hrDao.saveProfile(hr.getPersonId(), jobSeekerId);
						if (result > 0) {
							mav.addObject("profileSaved", true);
						} else {
							mav.addObject("profileSaved", false);
						}
					}

					mav.addObject("loggedInUser", hr);
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
		} catch (

		Exception ex) {
			System.out.println("Error Occurred - saveProfile: " + ex.getMessage());
		}
		return mav;
	}

}
