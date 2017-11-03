package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Company;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.CompanyDAO;

@Controller
@RequestMapping(value = "/manageCompanies.htm")
public class ManageCompanyController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					CompanyDAO companyDao = new CompanyDAO();
					List<Company> companiesList = companyDao.getCompanies();
					mav.setViewName("manageCompanies");
					mav.addObject("companies", companiesList);
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
	public ModelAndView manageCompany(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					String action = request.getParameter("action");
					CompanyDAO companyDao = new CompanyDAO();

					if (action.equalsIgnoreCase("add")) {
						System.out.println(action);
						String companyName = request.getParameter("companyName");
						System.out.println("Company Name: " + companyName);

						Company newCompany = new Company();
						newCompany.setCompanyName(companyName);

						long companyId = companyDao.addCompany(newCompany);

						if (companyId > 0) {
							List<Company> companiesList = companyDao.getCompanies();
							mav.addObject("companies", companiesList);
							mav.addObject("loggedInUser", user);
							mav.addObject("addedCompany", true);

							mav.setViewName("manageCompanies");
						}
					}

					if (action.equalsIgnoreCase("delete")) {
						System.out.println(action);
						long companyId = Long.parseLong(request.getParameter("companyId"));
						System.out.println(companyId);
						int result = companyDao.deleteCompany(companyId);

						if (result > 0) {
							List<Company> companiesList = companyDao.getCompanies();
							mav.addObject("companies", companiesList);
							mav.addObject("loggedInUser", user);
							mav.addObject("deletedCompany", true);

							mav.setViewName("manageCompanies");
						}
					}

					if (action.equalsIgnoreCase("edit")) {
						System.out.println(action);
						long companyId = Long.parseLong(request.getParameter("companyId"));
						String companyName = request.getParameter("txtCompany" + companyId);

						Company company = new Company();
						company.setCompanyId(companyId);
						company.setCompanyName(companyName);
						long result = companyDao.addCompany(company);

						if (result > 0) {
							List<Company> companiesList = companyDao.getCompanies();
							mav.addObject("companies", companiesList);
							mav.addObject("loggedInUser", user);
							mav.addObject("updatedCompany", true);

							mav.setViewName("manageCompanies");
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
