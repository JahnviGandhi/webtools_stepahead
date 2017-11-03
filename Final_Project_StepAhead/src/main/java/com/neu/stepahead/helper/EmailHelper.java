package com.neu.stepahead.helper;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailHelper {
	public static boolean sendEmail(String subject, String to, String content) {
		boolean emailSent = false;
		String msgId = "";
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("jahnvi2610@gmail.com", "USA_itis@2015"));
			email.setSSLOnConnect(true);

			email.setFrom("no-reply@stepahead.com");
			email.setSubject(subject);
			email.setMsg(content);
			email.addTo(to);
			msgId = email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!msgId.isEmpty()) {
			emailSent = true;
		}
		return emailSent;
	}

	public static String getVerificationContent(String fName, String verificationLink) {
		String content = "Hello " + fName + ",";
		content = content + "\n";
		content = content + "Please verify your email id by visiting the following link:";
		content = content + "\n";
		content = content + verificationLink;
		content = content + "\n";
		content = content + "\n";
		content = content + "Regards,\nStepAhead Team";

		return content;
	}

	public static String getApplicationContent(String firstName, String lastName, String hrName, String jobTitle,
			String jobId) {
		String content = "Hello " + hrName + ",";
		content = content + "\n";
		content = content + "Please find my resume in the attachments.\nFor:\nJob Id: " + jobId + "- Job Title: "
				+ jobTitle;
		content = content + "\n";
		content = content + "\n";
		content = content + "Regards,\n" + firstName + lastName;

		return content;
	}

	public static boolean sendResumeEmail(String firstName, String lastName, String subject, String from, String to,
			String content, String resumePath) {
		boolean emailSent = false;
		String msgId = "";
		try {
			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(resumePath);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Resume");
			attachment.setName(firstName + " " + lastName);

			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("jahnvi2610@gmail.com", "USA_itis@2015"));
			email.setSSLOnConnect(true);

			email.setFrom(from);
			email.setSubject(subject);
			email.setMsg(content);
			email.addTo(to);

			// add the attachment
			email.attach(attachment);

			// send the email
			msgId = email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Occurred - sendResumeEmail: " + e.getMessage());
		}

		if (!msgId.isEmpty()) {
			emailSent = true;
		}
		return emailSent;
	}
}
