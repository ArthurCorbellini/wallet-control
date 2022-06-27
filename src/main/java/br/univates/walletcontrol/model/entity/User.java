package br.univates.walletcontrol.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.univates.walletcontrol.model.entity.type.UserRoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Arthur
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	private String username;
	private String password;
	private Boolean enabled;
	@Enumerated(EnumType.STRING)
	private UserRoleType role;

	public User(String username) {
		this.username = username;
	}

}
