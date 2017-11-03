package com.neu.stepahead.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.stepahead.bean.Job;
import com.neu.stepahead.bean.JobSeeker;
import com.neu.stepahead.bean.Position;
import com.neu.stepahead.bean.User;
import com.neu.stepahead.dao.JobDAO;
import com.neu.stepahead.dao.JobSeekerDAO;
import com.neu.stepahead.dao.PositionDAO;
import com.neu.stepahead.helper.EmailHelper;

@Controller
@RequestMapping(value = "/applyForJob.htm")
public class ApplyForJobController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView applyForJob(HttpServletRequest request, @RequestParam("jobId") long jobId) {
		ModelAndView mav = new ModelAndView();
		try {
			HttpSession session = request.getSession(false);

			if (session != null) {
				System.out.println("sessio");
				User sUser = (User) session.getAttribute("user");

				if (sUser != null) {
					System.out.println("user");
					JobSeekerDAO jsDao = new JobSeekerDAO();
					JobSeeker js = jsDao.getJobSeekerById(sUser.getPersonId());

					JobDAO jobDao = new JobDAO();

					Job job = jobDao.getJobById(jobId);
					String id = String.valueOf(jobId);
					String jobTitle = job.getJobTitle();
					String to = job.getPostedBy().getEmailId();
					String hrName = job.getPostedBy().getFirstName();

					String firstName = sUser.getFirstName();
					String lastName = sUser.getLastName();
					String from = sUser.getEmailId();

					String subject = "Application For Job Id: " + jobId;
					String content = EmailHelper.getApplicationContent(firstName, lastName, hrName, jobTitle, id);

					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
					String fileName = "/Resumes/" + sUser.getPersonId() + "_Resume.pdf";
					String resumePath = classloader.getResource(fileName).toString().substring(6);

					boolean emailSent = EmailHelper.sendResumeEmail(firstName, lastName, subject, from, to, content,
							resumePath);

					PositionDAO posDao = new PositionDAO();
					Position position = posDao.getPositionById(js.getCurrentPosition().getPositionId());

					Position p = new Position();
					p.setPositionId(position.getPositionId());
					p.setPositionTitle(position.getPositionTitle());

					if (emailSent) {
						System.out.println("Email Sent!");

						Set<JobSeeker> applicants = job.getApplicants();

						if (applicants == null) {
							applicants = new HashSet<JobSeeker>();
						}
						applicants.add(js);
						System.out.println("JobSeeker added to Jobs.");

						Set<Job> jobsApplied = js.getJobsApplied();

						if (jobsApplied == null) {
							System.out.println("Jobs Applied Null-----------------");
							jobsApplied = new HashSet<Job>();
						}
						jobsApplied.add(job);

						System.out.println("job added to HashSet.");
						js.setJobsApplied(jobsApplied);

						int result = jsDao.addAppliedJob(sUser.getPersonId(), jobId);

						if (result > 0) {
							mav.addObject("applied", true);
						} else {
							mav.addObject("applied", false);
						}
					} else {
						mav.addObject("applied", false);
					}

					mav.addObject("loggedInUser", js);
					mav.addObject("job", job);
					mav.addObject("jobFound", true);
					mav.setViewName("viewJobDetails");
				} else {
					session.invalidate();
					mav.addObject("user", new User());
					mav.setViewName("home");
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
