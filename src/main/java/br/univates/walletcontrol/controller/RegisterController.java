package br.univates.walletcontrol.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.univates.walletcontrol.controller.dto.RequestNewUserDTO;
import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.repository.UserRepository;

/**
 * @author Arthur
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String register(RequestNewUserDTO requisicao) {
		return "register";
	}

	@PostMapping("novo")
	public String novo(@Valid RequestNewUserDTO request, BindingResult result) {
		if (result.hasErrors())
			return "register";

		User user = request.toUser();
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepo.save(user);

		return "redirect:/login";
	}

}
