package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.PersonneRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ThymeleafController.
 */
@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {

	/** The personne repository. */
	@Autowired
	private PersonneRepository personneRepository;
	
	/**
	 * Show view.
	 *
	 * @param model the model
	 * @return the string
	 */
	// http://localhost:8080/thymeleaf/view
	@GetMapping(value = "/view")
	public String showView(Model model) {
		model.addAttribute("message", "Hello");
		model.addAttribute("personnes", personneRepository.findAll());
		model.addAttribute("personne", personneRepository.findById(1).orElse(null));
		return "thymeleaf/index";
	}
}
