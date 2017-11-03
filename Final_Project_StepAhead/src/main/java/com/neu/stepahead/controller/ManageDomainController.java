package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Domain;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.DomainDAO;

@Controller
@RequestMapping(value = "/manageDomains.htm")
public class ManageDomainController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					DomainDAO domainDao = new DomainDAO();
					List<Domain> domains = domainDao.getDomains();

					mav.setViewName("manageDomains");
					mav.addObject("domains", domains);
					mav.addObject("loggedInUser", user);

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

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView manageDomain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					String action = request.getParameter("action");

					DomainDAO domainDao = new DomainDAO();

					if (action.equalsIgnoreCase("add")) {
						String domainName = request.getParameter("domainName");

						Domain domain = new Domain();
						domain.setDomainName(domainName);

						long domainId = domainDao.addDomain(domain);

						if (domainId > 0) {
							List<Domain> domainList = domainDao.getDomains();
							mav.addObject("domains", domainList);
							mav.addObject("loggedInUser", user);
							mav.addObject("addedDomain", true);

							mav.setViewName("manageDomains");
						}
					}

					if (action.equalsIgnoreCase("delete")) {
						long domainId = Long.parseLong(request.getParameter("domainId"));
						System.out.println(domainId);
						int result = domainDao.deleteDomain(domainId);

						if (result > 0) {
							List<Domain> domainList = domainDao.getDomains();
							mav.addObject("domains", domainList);
							mav.addObject("loggedInUser", user);
							mav.addObject("deletedDomain", true);

							mav.setViewName("manageDomains");
						}
					}

					if (action.equalsIgnoreCase("edit")) {
						long domainId = Long.parseLong(request.getParameter("domainId"));

						String domainName = request.getParameter("txtDomain" + domainId);
						System.out.println(domainName);

						Domain domain = new Domain();
						domain.setDomainId(domainId);
						domain.setDomainName(domainName);

						long result = domainDao.addDomain(domain);

						if (result > 0) {
							List<Domain> domainList = domainDao.getDomains();
							mav.addObject("domains", domainList);
							mav.addObject("loggedInUser", user);
							mav.addObject("updatedDomain", true);

							mav.setViewName("manageDomains");
						}
					}
				} else {
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
