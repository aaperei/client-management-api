package com.alison.clientmanagement.rest;

import com.alison.clientmanagement.domain.Client;
import com.alison.clientmanagement.domain.ClientFacade;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link ClientController} is the controller layer responsible for handling HTTP requests for all
 * Client Management APIs
 *
 * @author Alison Augusto Miranda Pereira
 */

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

  private final ClientFacade clientFacade;

  private static final Logger logger = Logger.getLogger(ClientController.class);

  /**
   * Gets all clients in database
   *
   * @return a {@link List} of all {@link Client} in database
   */
  @GetMapping
  public ResponseEntity<List<Client>> getAllClients() {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(clientFacade.findAll());
    } catch (Exception e) {
      logger.error("Error getting all clients.", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  /**
   * Gets a list of clients from database according to the parameter querySearch
   * <p>
   * querySearch parameter to be searched
   *
   * @return a {@link List} of {@link Client}
   */
  @GetMapping("/find")
  public ResponseEntity<List<Client>> findByParameter(
      @RequestParam(value = "q", required = true, defaultValue = "") final String querySearch) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(clientFacade.findByParameter(querySearch));
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

  /**
   * Finds a {@link Client} by its id
   *
   * @param id used to search
   * @return an {@link Client} object
   */
  @GetMapping(path = "{id}")
  public ResponseEntity<Client> findClientById(@PathVariable final Long id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(clientFacade.findById(id));
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

  }

  /**
   * Creates a new {@link Client}
   *
   * @param client new client object to be persisted
   * @return an {@link Client} object
   */
  @PostMapping()
  public ResponseEntity<Client> addClient(@Valid @RequestBody final Client client) {
    try {

      clientFacade.addClient(client);
      return ResponseEntity.status(HttpStatus.OK).build();

    } catch (DataIntegrityViolationException e) {
      logger.error("Error while creating Client object. Constraints violated.", e);
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

  }

  /**
   * Updates all {@link Client} object attributes
   *
   * @param id     identifier to a {@link Client} object
   * @param client modified {@link Client} object
   * @return a {@link ResponseEntity} with request status
   */
  @PutMapping(path = "{id}")
  public ResponseEntity<?> updateAllClientsAttributes(@PathVariable final Long id,
      @Valid @RequestBody final Client client) {
    try {

      clientFacade.updateClient(id, client);
      return ResponseEntity.status(HttpStatus.OK).build();

    } catch (EntityNotFoundException e) {
      logger.error("Error while updating Client object. Entity not found.", e);
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    } catch (DataIntegrityViolationException e) {
      logger.error("Error while updating Client object. Constraints violated.", e);
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

  }

  /**
   * Updates some {@link Client} object attributes
   *
   * @param id     identifier to a {@link Client} object
   * @param client modified {@link Client} object
   * @return a {@link ResponseEntity} with request status
   */
  @PatchMapping(path = "{id}")
  public ResponseEntity<?> updateSpecificClientAttributes(@PathVariable final Long id,
      @Valid @RequestBody final Client client) {
    try {

      clientFacade.updateSpecificClientAttributes(id, client);
      return ResponseEntity.status(HttpStatus.OK).build();

    } catch (EntityNotFoundException e) {
      logger.error("Error while updating Client object. Entity not found.", e);
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    } catch (DataIntegrityViolationException e) {
      logger.error("Error while updating Client object. Constraints violated.", e);
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }
  }

  /**
   * Method to delete a {@link Client} object by its id
   *
   * @param id given to delete an {@link Client} object
   * @return a {@link ResponseEntity} with request status
   */
  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteClient(@PathVariable final Long id) {
    clientFacade.deleteClient(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
