package br.univates.walletcontrol.service;

import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.entity.type.UserRoleType;
import br.univates.walletcontrol.model.repository.UserRepository;

/**
 * @author Arthur
 *
 */
class UserServiceTest {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository userRepo;
	@Mock
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void encodePasswordAndSave() {
		// Parametrização
		User userExample = userExample();
		String encryptedPassword = "senha criptografada";
		Mockito.when(passwordEncoder.encode(anyString())).thenReturn(encryptedPassword);

		// Método a ser testado
		service.encodePasswordAndSave(userExample);

		// Validações
		Assertions.assertEquals(encryptedPassword, userExample.getPassword());
		Mockito.verify(userRepo).save(userExample);
	}

	@Test
	void findAllPadrao() {
		User userExample = new User();
		userExample.setRole(UserRoleType.PADRAO);

		service.findAllPadrao();

		Mockito.verify(userRepo).findAll(Example.of(userExample));
	}
	
	@Test
	void findAllAdmin() {
		User userExample = new User();
		userExample.setRole(UserRoleType.ADMIN);

		service.findAllAdmin();

		Mockito.verify(userRepo).findAll(Example.of(userExample));
	}

	@Test
	void disableUser() {
		User userExample = userExample();

		service.disableUser(userExample);

		Mockito.verify(userRepo).save(userExample);
	}

	@Test
	void enableUser() {
		User userExample = userExample();

		service.enableUser(userExample);

		Mockito.verify(userRepo).save(userExample);
	}

	private User userExample() {
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setRole(UserRoleType.PADRAO);
		user.setEnabled(false);

		return user;
	}

}
