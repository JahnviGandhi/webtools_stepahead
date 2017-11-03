package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.Position;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.AddressDAO;
import com.neu.stepahead.dao.JobSeekerDAO;
import com.neu.stepahead.dao.PositionDAO;
import com.neu.stepahead.helper.UploadResumeHelper;

@Controller
@RequestMapping(value = "/jobSeekerProfile.htm")
public class SaveSeekerProfileController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveSeekerProfile(HttpServletRequest request,
			@ModelAttribute("loggedInUser") JobSeeker jobSeeker) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					AddressDAO addressDao = new AddressDAO();
					PositionDAO posDao = new PositionDAO();
					JobSeekerDAO jobSeekerDao = new JobSeekerDAO();

					JobSeeker seeker = new JobSeeker();
					seeker.setActive(true);
					seeker.setCompleteProfile(true);
					seeker.setContact(jobSeeker.getContact());
					seeker.setCurrentPosition(
							posDao.getPositionByName(jobSeeker.getCurrentPosition().getPositionTitle()));
					seeker.setEmailId(sUser.getEmailId());
					seeker.setFirstName(jobSeeker.getFirstName());
					seeker.setGender(jobSeeker.getGender());
					seeker.setKeywords(jobSeeker.getKeywords());
					seeker.setLastName(jobSeeker.getLastName());
					seeker.setPassword(sUser.getPassword());
					seeker.setPersonId(sUser.getPersonId());
					seeker.setResume("");
					seeker.setRole("JobSeeker");
					seeker.setUserName(sUser.getUserName());
					seeker.setVerified(true);

					String resumeName = UploadResumeHelper.uploadResume(jobSeeker.getResumeFile(), sUser.getPersonId());

					if (!resumeName.isEmpty()) {
						seeker.setResume(resumeName);

						long userId = jobSeekerDao.addJobSeeker(seeker);
						int updated = addressDao.addAddress(jobSeeker.getAddress(), userId);

						if (updated > 0) {
							seeker.setAddress(jobSeeker.getAddress());

							List<Position> positions = posDao.getPositions();
							mav.addObject("profileSaved", true);
							mav.addObject("positions", positions);
						} else {
							mav.addObject("profileSaved", false);
						}
					}

					mav.setViewName("jobSeekerProfile");
					mav.addObject("loggedInUser", seeker);
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
