package com.neu.stepahead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/error.htm")
public class ErrorController {

	@RequestMapping(method = RequestMethod.GET)
	public String showErrorPage() {
		return "error";
	}
}
