package br.univates.walletcontrol.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univates.walletcontrol.model.entity.Vault;

/**
 * @author Arthur
 */
@Repository
public interface VaultRepository extends JpaRepository<Vault, Long> {

}
