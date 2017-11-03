package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Package;
import com.neu.stepahead.bean.Position;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.PackageDAO;
import com.neu.stepahead.dao.PositionDAO;

@Controller
@RequestMapping(value = "/managePackages.htm")
public class ManagePackageController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					PackageDAO packageDao = new PackageDAO();
					PositionDAO positionDao = new PositionDAO();
					List<Package> packageList = packageDao.getPackages();
					List<Position> positionList = positionDao.getPositions();

					mav.setViewName("managePackages");
					mav.addObject("packages", packageList);
					mav.addObject("positions", positionList);
					mav.addObject("loggedInUser", user);
					mav.addObject("package", new Package());
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
	public ModelAndView managePackeage(@ModelAttribute("package") Package pack, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				System.out.println("session");
				User user = (User) session.getAttribute("user");

				if (user != null) {
					System.out.println("user");
					String action = request.getParameter("action");
					System.out.println(action);

					PackageDAO packageDao = new PackageDAO();
					PositionDAO positionDao = new PositionDAO();
					if (action.equalsIgnoreCase("add")) {

						Package pck = new Package();
						pck.setMinSal(pack.getMinSal());
						pck.setMaxSal(pack.getMaxSal());
						pck.setPosition(positionDao.getPositionByName(pack.getPosition().getPositionTitle()));

						long packageId = packageDao.addPackage(pck);

						if (packageId > 0) {
							List<Package> packageList = packageDao.getPackages();
							List<Position> positionList = positionDao.getPositions();

							mav.addObject("loggedInUser", user);
							mav.addObject("packages", packageList);
							mav.addObject("positions", positionList);
							mav.addObject("addedPackage", true);
							mav.setViewName("managePackages");
						}
					}

					if (action.equalsIgnoreCase("delete")) {
						System.out.println(action);
						long packId = Long.parseLong(request.getParameter("packId"));
						int result = packageDao.deletePackage(packId);

						if (result > 0) {
							List<Package> packageList = packageDao.getPackages();
							List<Position> positionList = positionDao.getPositions();

							mav.addObject("loggedInUser", user);
							mav.addObject("packages", packageList);
							mav.addObject("positions", positionList);
							mav.addObject("deletedPackage", true);
							mav.setViewName("managePackages");
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

		} catch (

		Exception ex) {
			System.out.println("Error Occurred: " + ex.getMessage());
		}

		return mav;
	}
}
