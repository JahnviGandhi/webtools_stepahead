package com.neu.stepahead.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.AddressDAO;
import com.neu.stepahead.dao.UserDAO;

@Controller
@RequestMapping(value = "/adminProfile.htm")
public class SaveAdminProfileController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveAdminProfile(@ModelAttribute("loggedInUser") User user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					AddressDAO addressDao = new AddressDAO();
					UserDAO userDao = new UserDAO();

					User admin = new User();
					admin.setPersonId(sUser.getPersonId());
					admin.setEmailId(sUser.getEmailId());
					admin.setUserName(sUser.getUserName());
					admin.setPassword(sUser.getPassword());
					admin.setFirstName(user.getFirstName());
					admin.setLastName(user.getLastName());
					admin.setContact(user.getContact());
					admin.setCompleteProfile(true);
					admin.setActive(true);
					admin.setVerified(true);
					admin.setCompleteProfile(true);
					admin.setGender(user.getGender());
					admin.setRole("Admin");

					long userId = userDao.addUser(admin);
					int updated = addressDao.addAddress(user.getAddress(), userId);

					if (updated > 0) {
						admin.setAddress(user.getAddress());
						mav.addObject("profileSaved", true);
					}

					mav.setViewName("adminProfile");
					mav.addObject("loggedInUser", admin);
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
