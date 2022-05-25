package br.univates.walletcontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.repository.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
		
    @GetMapping
    public List<User> listAll(){
        return userRepo.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userRepo.save(user);
    }
    
}
