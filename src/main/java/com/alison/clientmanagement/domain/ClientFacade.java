package com.alison.clientmanagement.domain;

import com.alison.clientmanagement.rest.ClientController;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@link ClientFacade} implements the facade design pattern in order to hide implementation details
 * from the {@link ClientController}
 *
 * @author Alison Augusto Miranda Pereira
 */

@Component
@AllArgsConstructor
public class ClientFacade {

  private final ClientService clientService;

  /**
   * Saves a given {@link Client} object into database
   *
   * @param client the {@link Client} object
   */
  @Transactional
  public Client addClient(final Client client) {
    return clientService.saveClient(client);
  }

  /**
   * Updates all {@link Client} attributes
   *
   * @param id             the identifier to {@link Client} instance
   * @param modifiedClient {@link Client} object with new values
   */
  @Transactional
  public Client updateClient(final Long id, final Client modifiedClient) {
    final Client persistedClient = this.findById(id);

    if (modifiedClient.getId().equals(persistedClient.getId())) {
      persistedClient.update(modifiedClient);
      return clientService.saveClient(persistedClient);
    } else {
      throw new IllegalArgumentException("Specified id does not matches with client object id");
    }
  }

  /**
   * Updates some {@link Client} attributes
   *
   * @param id             the identifier to {@link Client} instance
   * @param modifiedClient {@link Client} object with new values
   */
  @Transactional
  public Client updateSpecificClientAttributes(final Long id, final Client modifiedClient) {
    final Client persistedClient = this.findById(id);
    if (modifiedClient.getId().equals(persistedClient.getId())) {
      persistedClient.updateNotNullAttributes(modifiedClient);
      return clientService.saveClient(persistedClient);
    } else {
      throw new IllegalArgumentException("Specified id does not matches with client object id");
    }
  }

  /**
   * Finds a list of all persisted Clients
   *
   * @return a {@link List} of {@link Client}
   */
  public List<Client> findAll() {
    return clientService.findAll();
  }

  /**
   * Search an {@link Client} by its id
   *
   * @param id of the {@link Client} desired
   * @return if founded, a fulfilled {@link Client} object, otherwise a new one
   */
  public Client findById(final Long id) {
    return clientService.findById(id);
  }

  /**
   * Gets a list of clients from database according to the parameter querySearch
   * <p>
   * querySearch parameter to be searched
   *
   * @return a {@link List} of {@link Client}
   */
  public List<Client> findByParameter(final String querySearch) {
    return clientService.findByParameter(querySearch);
  }

  /**
   * Deletes an {@link Client} object by its id
   *
   * @param id parameter used to delete an {@link Client} object in database
   */
  @Transactional
  public boolean deleteClient(final Long id) {
    clientService.deleteClient(id);
    return true;
  }

}
