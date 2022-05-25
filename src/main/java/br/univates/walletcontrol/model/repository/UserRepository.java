package br.univates.walletcontrol.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univates.walletcontrol.model.entity.User;

/**
 * @author Arthur
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
