package br.univates.walletcontrol.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.univates.walletcontrol.controller.dto.RequestNewUserDTO;
import br.univates.walletcontrol.service.UserService;

/**
 * @author Arthur
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String register(RequestNewUserDTO request) {
		return "register";
	}

	@PostMapping("novo")
	public String novo(@Valid RequestNewUserDTO request, BindingResult result) {
		if (result.hasErrors())
			return "register";

		userService.encodePasswordAndSave(request.toUser());

		return "redirect:/login";
	}

}
