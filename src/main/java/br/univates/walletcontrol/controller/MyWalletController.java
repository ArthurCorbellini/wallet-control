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
@RequestMapping("myWallet")
public class MyWalletController {

	@GetMapping("list")
	public String list(Model model, Principal loggedUser) {
		return "myWallet/list";
	}

}
