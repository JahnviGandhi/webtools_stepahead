package com.neu.stepahead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Position;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.PositionDAO;

@Controller
@RequestMapping(value = "/managePositions.htm")
public class ManagePositionController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					PositionDAO positionDao = new PositionDAO();
					List<Position> positionList = positionDao.getPositions();

					mav.setViewName("managePositions");
					mav.addObject("positions", positionList);
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
	public ModelAndView managePosition(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession();

			if (session != null) {
				User user = (User) session.getAttribute("user");

				if (user != null) {
					String action = request.getParameter("action");
					PositionDAO positionDao = new PositionDAO();

					if (action.equalsIgnoreCase("add")) {
						String positionTitle = request.getParameter("positionTitle");

						Position position = new Position();
						position.setPositionTitle(positionTitle);

						long positionId = positionDao.addPosition(position);

						if (positionId > 0) {
							List<Position> positionList = positionDao.getPositions();

							mav.addObject("positions", positionList);
							mav.addObject("loggedInUser", user);
							mav.addObject("addedPosition", true);

							mav.setViewName("managePositions");
						}
					}

					if (action.equalsIgnoreCase("delete")) {
						long positionId = Long.parseLong(request.getParameter("positionId"));

						int result = positionDao.deletePosition(positionId);

						if (result > 0) {
							List<Position> positionList = positionDao.getPositions();
							mav.addObject("positions", positionList);
							mav.addObject("loggedInUser", user);
							mav.addObject("deletedPosition", true);

							mav.setViewName("managePositions");
						}
					}

					if (action.equalsIgnoreCase("edit")) {
						long positionId = Long.parseLong(request.getParameter("positionId"));
						String positionTitle = request.getParameter("txtPosition" + positionId);

						Position position = new Position();
						position.setPositionId(positionId);
						position.setPositionTitle(positionTitle);
						long result = positionDao.addPosition(position);

						if (result > 0) {
							List<Position> positionList = positionDao.getPositions();
							mav.addObject("positions", positionList);
							mav.addObject("loggedInUser", user);
							mav.addObject("updatedPosition", true);

							mav.setViewName("managePositions");
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
