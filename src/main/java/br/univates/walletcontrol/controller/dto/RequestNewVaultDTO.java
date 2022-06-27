package br.univates.walletcontrol.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.univates.walletcontrol.model.entity.Vault;
import lombok.Data;

/**
 * @author Arthur
 */
@Data
public class RequestNewVaultDTO {

	@NotBlank
	private String name;
	@NotNull
	private BigDecimal amount;

	public Vault toVault() {
		Vault vault = new Vault();
		vault.setName(name);
		vault.setAmount(amount);

		return vault;
	}

}