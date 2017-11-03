package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Company;
import com.neu.stepahead.bean.Domain;
import com.neu.stepahead.bean.HrPerson;
import com.neu.stepahead.bean.Position;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.AddressDAO;
import com.neu.stepahead.dao.CompanyDAO;
import com.neu.stepahead.dao.DomainDAO;
import com.neu.stepahead.dao.HrPersonDAO;
import com.neu.stepahead.dao.PositionDAO;

@Controller
@RequestMapping(value = "/hrPersonProfile.htm")
public class SaveHrProfileController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveHrProfile(@ModelAttribute("loggedInUser") HrPerson user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User sUser = (User) session.getAttribute("user");
				if (sUser != null) {
					AddressDAO addressDao = new AddressDAO();
					HrPersonDAO hrDao = new HrPersonDAO();
					DomainDAO domainDao = new DomainDAO();
					CompanyDAO comDao = new CompanyDAO();
					PositionDAO posDao = new PositionDAO();

					HrPerson person = new HrPerson();
					person.setActive(true);
					person.setCompany(comDao.getCompanyByName(user.getCompany().getCompanyName()));
					person.setCompleteProfile(true);
					person.setContact(user.getContact());
					person.setCurrentPosition(posDao.getPositionByName(user.getCurrentPosition().getPositionTitle()));
					person.setDomain(domainDao.getDomainByName(user.getDomain().getDomainName()));
					person.setEmailId(sUser.getEmailId());
					person.setFirstName(user.getFirstName());
					person.setGender(user.getGender());
					person.setLastName(user.getLastName());
					person.setPassword(sUser.getPassword());
					person.setPersonId(sUser.getPersonId());
					person.setRole("HrPerson");
					person.setUserName(sUser.getUserName());
					person.setVerified(true);

					long userId = hrDao.addHrPerson(person);
					int updated = addressDao.addAddress(user.getAddress(), userId);

					if (updated > 0) {
						person.setAddress(user.getAddress());

						List<Company> companies = comDao.getCompanies();
						List<Position> positions = posDao.getPositions();
						List<Domain> domains = domainDao.getDomains();

						mav.addObject("profileSaved", true);
						mav.addObject("companies", companies);
						mav.addObject("domains", domains);
						mav.addObject("positions", positions);
					}

					mav.setViewName("hrPersonProfile");
					mav.addObject("loggedInUser", person);

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
