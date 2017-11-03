package com.neu.stepahead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.UserDAO;

@Controller
@RequestMapping(value = "/verifyEmail.htm")
public class VerifyEmailController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView verifyEmail(@RequestParam("isVerified") String isVerified) {
		ModelAndView mav = new ModelAndView();

		try {
			System.out.println(isVerified);
			if (!isVerified.isEmpty()) {
				String verified = isVerified.substring(0, isVerified.indexOf("_"));
				long userId = Long.parseLong(isVerified.substring(isVerified.indexOf("_") + 1));
				UserDAO userDao = new UserDAO();

				if (verified.equals("false")) {
					User user = userDao.getUser(userId);
					if (user != null) {
						user.setVerified(true);
						user.setActive(true);
						userDao.addUser(user);
					}
				}
			}

			mav.setViewName("verifyEmail");
			mav.addObject("verified", "true");
		} catch (Exception ex) {
			System.out.println("Error Occurred: " + ex.getMessage());
		}
		return mav;
	}

}
