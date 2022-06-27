package br.univates.walletcontrol.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.univates.walletcontrol.controller.dto.RequestNewVaultDTO;
import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.entity.Vault;
import br.univates.walletcontrol.service.UserService;
import br.univates.walletcontrol.service.VaultService;

/**
 * @author Arthur
 */
@Controller
@RequestMapping("myWallet")
public class MyWalletController {

	@Autowired
	private VaultService vaultService;
	
	@Autowired
	private UserService userService;

	@GetMapping("list")
	public String list(Model model, Principal loggedUser) {
		model.addAttribute("vaultList", vaultService.findAll());

		return "myWallet/list";
	}

	@GetMapping("edit")
	public String edit(RequestNewVaultDTO request) {
		return "myWallet/edit";
	}

	@PostMapping("save")
	public String save(@Valid RequestNewVaultDTO request, BindingResult result) {
		if (result.hasErrors())
			return "myWallet/edit";

		Vault entity = request.toVault();
		entity.setUser(new User(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));

		System.out.println("------------------");
		System.out.println(userService.findAllPadrao());
		
		
		vaultService.save(entity);

		return "redirect:/myWallet/list";
	}

	@GetMapping(value = "delete/{id}")
	public String deleteVault(@PathVariable("id") Long id) {
		vaultService.deleteById(id);
		return "redirect:/myWallet/list";
	}

}
