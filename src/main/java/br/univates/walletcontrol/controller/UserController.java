package br.univates.walletcontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> listAll() {
		return userService.findAll();
	}

	@PostMapping
	public User save(@RequestBody User user) {
		return userService.save(user);
	}

}
