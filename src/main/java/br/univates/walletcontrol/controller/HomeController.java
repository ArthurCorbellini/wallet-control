package br.univates.walletcontrol.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Arthur
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String home(Model model, Principal loggedUser) {
		return "home";
	}

}
