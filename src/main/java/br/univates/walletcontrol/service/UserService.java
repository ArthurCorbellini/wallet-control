package br.univates.walletcontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.entity.type.UserRoleType;
import br.univates.walletcontrol.model.repository.UserRepository;

/**
 * @author Arthur
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User save(User entity) {
		return userRepo.save(entity);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User encodePasswordAndSave(User entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		return userRepo.save(entity);
	}

	// -------------- 
	
	public List<User> findAllPadrao() {
		User userExample = new User();
		userExample.setRole(UserRoleType.PADRAO);

		return userRepo.findAll(Example.of(userExample));
	}
	
	public List<User> findAllAdmin() {
		User userExample = new User();
		userExample.setRole(UserRoleType.ADMIN);

		return userRepo.findAll(Example.of(userExample));
	}

	public void disableUser(User user) {
		user.setEnabled(false);
		userRepo.save(user);
	}

	public void enableUser(User user) {
		user.setEnabled(true);
		userRepo.save(user);
	}

}
