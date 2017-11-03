package com.neu.stepahead.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Address;
import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.HrPersonDAO;
import com.neu.stepahead.dao.JobSeekerDAO;
import com.neu.stepahead.helper.EmailHelper;

@Controller
@RequestMapping(value = "/signup.htm")
public class SignUpController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signup");
		mav.addObject("user", new User());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView successSignUp(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam("radioType") String radioType, @RequestParam("gender") String gender) {
		ModelAndView mav = new ModelAndView();

		try {

			if (result.hasErrors()) {
				mav.setViewName("signup");
				mav.addObject("user", user);
			} else {

				long personId = 0;
				long userId = 0;
				if (radioType.equals("JobSeeker")) {
					JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
					JobSeeker person = new JobSeeker();

					person.setFirstName(user.getFirstName());
					person.setLastName(user.getLastName());
					person.setContact(user.getContact());
					person.setEmailId(user.getEmailId());
					person.setVerified(false);
					person.setCompleteProfile(false);
					person.setAddress(new Address());
					person.setGender(gender);
					person.setUserName(user.getUserName());
					person.setPassword(user.getPassword());
					person.setActive(false);
					person.setVerified(false);
					person.setRole(radioType);
					user.setPersonId(personId);

					userId = jobSeekerDAO.addJobSeeker((JobSeeker) person);

					mav.addObject("jobSeeker", person);
					mav.setViewName("home");
				}

				if (radioType.equals("HrPerson")) {
					HrPersonDAO hrPersonDAO = new HrPersonDAO();
					HrPerson person = new HrPerson();

					person.setFirstName(user.getFirstName());
					person.setLastName(user.getLastName());
					person.setContact(user.getContact());
					person.setEmailId(user.getEmailId());
					person.setVerified(false);
					person.setCompleteProfile(false);
					person.setGender(gender);
					person.setAddress(new Address());
					person.setUserName(user.getUserName());
					person.setPassword(user.getPassword());
					person.setActive(false);
					person.setVerified(false);
					person.setRole(radioType);
					user.setPersonId(personId);

					userId = hrPersonDAO.addHrPerson((HrPerson) person);

					mav.addObject("hrPerson", person);
					mav.setViewName("home");
				}

				if (userId > 0) {
					// send email with verification link
					String verificationLink = "http://localhost:8080/stepahead/verifyEmail.htm?isVerified="
							+ user.isVerified() + "_" + userId;
					String mailContent = EmailHelper.getVerificationContent(user.getFirstName(), verificationLink);
					String subject = "Email Verification Required!";

					boolean verificationSent = EmailHelper.sendEmail(subject, user.getEmailId(), mailContent);

					if (verificationSent) {
						mav.addObject("verificationSent", verificationSent);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Error Occurred: " + ex.getMessage());
		}
		return mav;
	}
}
