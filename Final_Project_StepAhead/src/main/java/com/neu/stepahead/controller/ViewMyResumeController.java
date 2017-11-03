package com.neu.stepahead.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.JobSeekerDAO;

@Controller
@RequestMapping(value = "/myResume.pdf")
public class ViewMyResumeController {

	@RequestMapping(method = RequestMethod.GET)
	public String viewResume(HttpServletRequest request, HttpSession session, ModelMap model,
			HttpServletResponse response) {
		String viewName = null;

		try {
			// HttpSession session = request.getSession(false);
			System.out.println("method");
			if (session != null) {
				System.out.println("session");
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					System.out.println("user");
					JobSeekerDAO seekerDao = new JobSeekerDAO();
					String fileName = "/Resumes/" + seekerDao.getResumeName(sUser.getPersonId());
					String inlineFileName = sUser.getFirstName() + "_" + sUser.getLastName() + "_Resume.pdf";
					System.out.println(fileName);

					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
					System.out.println("class loader: " + classloader.toString());

					File file = new File(classloader.getResource(fileName).getFile());

					System.out.println("path: " + classloader.getResource(fileName));

					if (!file.exists()) {
						System.out.println("File does not exist.");
					} else {
						FileInputStream fileIn = new FileInputStream(file);
						response.setHeader("Content-Disposition", "inline; filename=\"" + inlineFileName + "\"");
						response.setDateHeader("Expires", -1);
						response.setContentType("application/pdf");

						IOUtils.copy(fileIn, response.getOutputStream());
					}
					response.flushBuffer();

				} else {
					session.invalidate();

					model.addAttribute("user", new User());
					viewName = "home";
				}
			} else {
				model.addAttribute("user", new User());
				viewName = "home";
			}
		} catch (Exception ex) {
			System.out.println("Error Occurred - View Resume: " + ex.getMessage());
		}
		return viewName;
	}
}
