package com.neu.stepahead.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.HrPersonDAO;

@Controller
@RequestMapping(value = "/viewSavedProfiles.htm")
public class ViewSavedProfilesController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewSavedProfiles(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					HrPersonDAO hrDao = new HrPersonDAO();
					HrPerson hr = hrDao.getHrById(sUser.getPersonId());

					Set<JobSeeker> savedProfiles = hr.getSavedProfiles();

					mav.addObject("loggedInUser", hr);
					mav.addObject("savedProfile", savedProfiles);
					mav.setViewName("savedProfiles");

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
			mav.setViewName("error");
		}
		return mav;
	}
}
