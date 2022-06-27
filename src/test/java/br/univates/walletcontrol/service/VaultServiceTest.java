package br.univates.walletcontrol.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.entity.Vault;
import br.univates.walletcontrol.model.entity.type.UserRoleType;
import br.univates.walletcontrol.model.repository.VaultRepository;

/**
 * @author Arthur
 *
 */
class VaultServiceTest {

	@InjectMocks
	private VaultService service;

	@Mock
	private VaultRepository vaultRepo;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void resetAllVaults() {
		List<Vault> vaultListExample = vaultListExample();
		Mockito.when(vaultRepo.findAll()).thenReturn(vaultListExample);

		service.resetAllVaults();

		vaultListExample.stream().map(Vault::getAmount).forEach(p -> Assertions.assertEquals(BigDecimal.ZERO, p));
		Mockito.verify(vaultRepo).saveAll(vaultListExample);
	}
	
	@Test
	void resetAllVaultsByUser() {
		User userExample = userExample();
		Vault vaultExample = new Vault();
		vaultExample.setUser(userExample);
		List<Vault> vaultListExample = vaultListExample();
		Mockito.when(vaultRepo.findAll(Example.of(vaultExample))).thenReturn(vaultListExample);
		
		service.resetAllVaultsByUser(userExample);
		
		vaultListExample.stream().map(Vault::getAmount).forEach(p -> Assertions.assertEquals(BigDecimal.ZERO, p));
		Mockito.verify(vaultRepo).saveAll(vaultListExample);
	}
	
	@Test
	void transferAllFromTo() {
		Vault vaultFrom = vaultListExample().get(0);
		Vault vaultTo = vaultListExample().get(1);
		BigDecimal finalAmount = vaultFrom.getAmount().add(vaultTo.getAmount());
		
		service.transferAllFromTo(vaultFrom, vaultTo);
		
		Assertions.assertEquals(BigDecimal.ZERO, vaultFrom.getAmount());
		Assertions.assertEquals(finalAmount, vaultTo.getAmount());
		Mockito.verify(vaultRepo).save(vaultFrom);
		Mockito.verify(vaultRepo).save(vaultTo);
	}
	
	@Test
	void deleteAllByUser() {
		User user = userExample();
		Vault vault = new Vault();
		vault.setUser(user);
		List<Vault> vaultListExample = vaultListExample();
		Mockito.when(vaultRepo.findAll(Example.of(vault))).thenReturn(vaultListExample);
		
		service.deleteAllByUser(user);
		
		Mockito.verify(vaultRepo).findAll(Example.of(vault));
		Mockito.verify(vaultRepo).deleteAll(vaultListExample);
	}
	
	@Test
	void deleteAllWithDisabledUser() {
		User userExample = new User();
		userExample.setEnabled(false);
		Vault vaultExample = new Vault();
		vaultExample.setUser(userExample);
		List<Vault> vaultListExample = vaultListExample();
		Mockito.when(vaultRepo.findAll(Example.of(vaultExample))).thenReturn(vaultListExample);
		
		service.deleteAllWithDisabledUser();
		
		Mockito.verify(vaultRepo).findAll(Example.of(vaultExample));
		Mockito.verify(vaultRepo).deleteAll(vaultListExample);
	}
	
	private User userExample() {
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setRole(UserRoleType.PADRAO);
		user.setEnabled(false);

		return user;
	}

	private List<Vault> vaultListExample() {
		List<Vault> vaultList = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Vault vault = new Vault();
			vault.setId(Long.valueOf(i));
			vault.setName("Vault Example" + i);
			vault.setAmount(BigDecimal.TEN.add(BigDecimal.valueOf(i)));
			vault.setUser(new User("username"));

			vaultList.add(vault);
		}

		return vaultList;
	}

}
