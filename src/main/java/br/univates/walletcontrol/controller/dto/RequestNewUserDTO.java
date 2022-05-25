package br.univates.walletcontrol.controller.dto;

import javax.validation.constraints.NotBlank;

import br.univates.walletcontrol.model.entity.User;
import br.univates.walletcontrol.model.entity.type.UserRoleType;
import lombok.Data;

/**
 * @author Arthur
 */
@Data
public class RequestNewUserDTO {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	private UserRoleType role;

	public User toUser() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role != null ? role : UserRoleType.PADRAO);
		user.setEnabled(true);

		return user;
	}

}