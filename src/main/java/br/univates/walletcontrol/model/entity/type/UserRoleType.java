package br.univates.walletcontrol.model.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Arthur
 */
@Getter
@AllArgsConstructor
public enum UserRoleType {

	ADMIN("ADM", "Administrador"),
	PADRAO("PAD", "Padão");

	private String id;
	private String name;

}
