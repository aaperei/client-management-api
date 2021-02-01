package com.alison.clientmanagement.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@link ClientService} is the service layer responsible for handling client operations
 * 
 * @author Alison Augusto Miranda Pereira
 *
 */

@AllArgsConstructor
@Service
public class ClientService {
	
	private final ClientRepository clientRepository;
	
	/**
	 * Saves a given {@link Client} object into database
	 * 
	 * @param client the {@link Client} object to be persisted
	 * @return the persisted {@link Client}
	 */
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}
	
	/**
	 * Gets all persisted clients from database
	 * 
	 * @return a {@link List} of {@link Client}
	 * * @throws {@link EntityNotFoundException} if no Entity was found on database
	 */
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	/**
	 * Gets a list of clients from database according to the parameter querySearch
	 * 
	 * querySearch parameter to be searched
	 * @return a {@link List} of {@link Client}
	 */
	public List<Client> findByParameter(String querySearch) {
		List<Client> clients = new ArrayList<>();
		
		if(querySearch.isEmpty()) {
			throw new EntityNotFoundException();
		}
				
		clients.addAll(clientRepository.findByNameContainingIgnoreCase(querySearch));
		clients.addAll(clientRepository.findByEmailContainingIgnoreCase(querySearch));

		if(clients.isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		return clients;
	}

	/**
	 * Looks up for a {@link Client} by its id
	 * 
	 * @param id of {@link Client} searched
	 * @return the searched {@link Client}
	 * @throws EntityNotFoundException {@link EntityNotFoundException} is thrown if id was not found on database
	 */
	public Client findById(final Long id) {
		return clientRepository
				.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = '" + id.toString() + "' was not found"));
	}
	
	/**
	 * Deletes a {@link Client} object by its id
	 * 
	 * @param id parameter used to delete a {@link Client} object in database
	 */
	public void deleteClient(final Long id) {
		clientRepository.deleteById(id);
	}

}
