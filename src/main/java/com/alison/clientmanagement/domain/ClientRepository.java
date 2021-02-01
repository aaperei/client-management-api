package com.alison.clientmanagement.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} is the repository layer responsible for handling database operations for {@link Client} objects
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	List<Client> findByCpfContaining(final String cpf);
	List<Client> findByNameContaining(final String name);
	List<Client> findByBirthDateContaining(final String birthDate);
	
}
