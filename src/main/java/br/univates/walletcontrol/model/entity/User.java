package br.univates.walletcontrol.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.univates.walletcontrol.model.entity.type.UserRoleType;
import lombok.Data;

/**
 * @author Arthur
 */
@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	private String username;
	private String password;
	private boolean enabled;
	@Enumerated(EnumType.STRING)
	private UserRoleType role;
	
}
