package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Company;
import com.neu.stepahead.bean.Domain;
import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.Position;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.CompanyDAO;
import com.neu.stepahead.dao.DomainDAO;
import com.neu.stepahead.dao.PositionDAO;
import com.neu.stepahead.dao.UserDAO;

@Controller
@RequestMapping(value = "/myProfile.htm")
public class MyProfileController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserDAO userDao = new UserDAO();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					if (user.getRole().equals("JobSeeker")) {
						PositionDAO positionDao = new PositionDAO();
						List<Position> positions = positionDao.getPositions();

						mav.addObject("positions", positions);
						mav.setViewName("jobSeekerProfile");
						mav.addObject("loggedInUser", (JobSeeker) user);
					} else if (user.getRole().equals("HrPerson")) {
						CompanyDAO companyDao = new CompanyDAO();
						List<Company> companies = companyDao.getCompanies();

						DomainDAO domainDao = new DomainDAO();
						List<Domain> domains = domainDao.getDomains();

						PositionDAO positionDao = new PositionDAO();
						List<Position> positions = positionDao.getPositions();

						mav.setViewName("hrPersonProfile");
						mav.addObject("loggedInUser", (HrPerson) user);
						mav.addObject("companies", companies);
						mav.addObject("domains", domains);
						mav.addObject("positions", positions);
					} else if (user.getRole().equals("Admin")) {
						User loggedUser = userDao.getUser(user.getPersonId());
						mav.setViewName("adminProfile");
						mav.addObject("loggedInUser", loggedUser);
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
		} catch (Exception ex) {
			System.out.println("Error Occurred: " + ex.getMessage());
		}

		return mav;
	}
}
