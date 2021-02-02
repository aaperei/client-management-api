package com.alison.clientmanagement.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest
class ClientFacadeIntegrationTests {

  @Autowired
  private ClientFacade clientFacade;

  private Client newClient;

  private LocalDateTime localDateTimeBeforeUpdate = LocalDateTime.now();

  @BeforeEach
  void init() {
    newClient = new Client();

    newClient.setCpf(12576852143L);
    newClient.setName("Name Tests");
    newClient.setBirthDate(LocalDate.now());
    newClient.setEmail("test@test.com.br");
    newClient.setAddress(new Address());
    newClient.getAddress().setCity("City Tests");
    newClient.getAddress().setCountry("Country Tests");
    newClient.getAddress().setNeighborhood("Neighborhood Tests");
    newClient.getAddress().setStreet("Street Tests");
    newClient.getAddress().setStreetNumber(1456);
    newClient.getAddress().setZipCode(466595672);

    localDateTimeBeforeUpdate = LocalDateTime.now();
  }

  @Test
  void addClientTest() {
    final Client client = clientFacade.addClient(newClient);
    assertTrue(
        client.getId() != null && client.getCreated() != null && client.getUpdated() != null);
  }

  @Test
  void updateClientTest() {
    final String newName = "New Tests Name";
    Client client = clientFacade.addClient(newClient);
    client.setName(newName);
    client = clientFacade.updateClient(client.getId(), client);
    assertTrue(client.getUpdated().isAfter(this.localDateTimeBeforeUpdate) && newName.equals(client.getName()));
  }


  @Test
  void updateSpecificClientAttributesTest() {
    final String newName = "New Tests Name";
    Client client = clientFacade.addClient(newClient);
    client.setName(newName);

    client = clientFacade.updateSpecificClientAttributes(client.getId(), client);
    assertTrue(
        client.getUpdated().isAfter(localDateTimeBeforeUpdate) && newName.equals(client.getName()));
  }

  @Test
  void updateSpecificClientAttributesTestInvalidId() {
    Client client = clientFacade.addClient(newClient);
    client.setId(null);

    assertThrows(InvalidDataAccessApiUsageException.class, () -> {
      clientFacade.updateSpecificClientAttributes(null, client);
    });

  }

  @Test
  void findByIdTest() {
    Client client = clientFacade.addClient(newClient);
    clientFacade.findById(client.getId());
    assertNotNull(client);
  }

  @Test
  void findAllTest() {
    clientFacade.addClient(newClient);
    List<Client> clients = clientFacade.findAll();

    assertFalse(clients.isEmpty());
  }

  @Test
  void deleteClientTest() {
    Client client = clientFacade.addClient(newClient);
    assertTrue(clientFacade.deleteClient(client.getId()));
  }

  @Test
  void deleteClientInvalidId() {

    assertThrows(InvalidDataAccessApiUsageException.class, () -> {
      assertTrue(clientFacade.deleteClient(null));
    });
  }

}
