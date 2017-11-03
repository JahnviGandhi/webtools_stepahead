package com.neu.stepahead.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadResumeHelper {
	public static String uploadResume(MultipartFile uploadItem, long userId) {
		String resumeName = "";
		try {
			InputStream inputStream = null;
			OutputStream outputStream = null;

			if (uploadItem.getSize() > 0) {
				inputStream = uploadItem.getInputStream();
				String extension = ".pdf";
				String fileName = "/Resumes/" + userId + "_Resume" + extension;

				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				File file = new File(classloader.getResource(fileName).getFile());

				outputStream = new FileOutputStream(file);

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
				resumeName = fileName;
			}
		} catch (Exception ex) {
			System.out.println("Error Occurred - File Upload: " + ex.getMessage());
		}
		return resumeName;
	}

}
