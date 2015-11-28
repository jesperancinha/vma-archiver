package com.steelzack.vma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VmaIndex {

	@RequestMapping("/welcome")
	public ModelAndView vmaIntroPage() {
		String welcomeMessage = "Welcome to the VMA page";

		return new ModelAndView("welcome", "message", welcomeMessage);
	}
}
