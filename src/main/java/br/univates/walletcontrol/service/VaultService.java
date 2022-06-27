package br.univates.walletcontrol.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.entity.Vault;
import br.univates.walletcontrol.model.repository.VaultRepository;

/**
 * @author Arthur
 *
 */
@Service
public class VaultService {

	@Autowired
	private VaultRepository vaultRepo;

	public List<Vault> findAll() {
		return vaultRepo.findAll();
	}

	public Vault save(Vault entity) {
		return vaultRepo.save(entity);
	}

	public void deleteById(Long id) {
		vaultRepo.deleteById(id);
	}

	// --------------
	
	public void resetAllVaults() {
		List<Vault> allVaults = vaultRepo.findAll();

		allVaults.forEach(p -> {
			p.setAmount(BigDecimal.ZERO);
		});

		vaultRepo.saveAll(allVaults);
	}

	public void resetAllVaultsByUser(User user) {
		Vault vaultExample = new Vault();
		vaultExample.setUser(user);

		List<Vault> vaultList = vaultRepo.findAll(Example.of(vaultExample));

		vaultList.forEach(p -> {
			p.setAmount(BigDecimal.ZERO);
		});

		vaultRepo.saveAll(vaultList);
	}

	public void transferAllFromTo(Vault vaultFrom, Vault vaultTo) {
		if (vaultFrom.getAmount() == null)
			vaultFrom.setAmount(BigDecimal.ZERO);

		if (vaultTo.getAmount() == null)
			vaultTo.setAmount(BigDecimal.ZERO);

		vaultTo.setAmount(vaultTo.getAmount().add(vaultFrom.getAmount()));
		vaultFrom.setAmount(BigDecimal.ZERO);

		vaultRepo.save(vaultFrom);
		vaultRepo.save(vaultTo);
	}

	public void deleteAllByUser(User user) {
		Vault vaultExample = new Vault();
		vaultExample.setUser(user);

		vaultRepo.deleteAll(vaultRepo.findAll(Example.of(vaultExample)));
	}
	
	public void deleteAllWithDisabledUser() {
		User userExample = new User();
		userExample.setEnabled(false);
		
		Vault vaultExample = new Vault();
		vaultExample.setUser(userExample);
		
		vaultRepo.deleteAll(vaultRepo.findAll(Example.of(vaultExample)));
	}

}
