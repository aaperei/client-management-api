package com.alison.clientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Data;

/**
 * {@link Client} represents the Client Entity
 *
 * @author Alison Augusto Miranda Pereira
 */

@Data
@Entity
@Table(name = "client")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "cpf", nullable = false)
  private Long cpf;

  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  private Address address;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Email(message = "It is not a valid email address")
  @Column(name = "email")
  private String email;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
  @Column(name = "created", nullable = false)
  private LocalDateTime created;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
  @Column(name = "updated", nullable = false)
  private LocalDateTime updated;


  /**
   * Updates this {@link Client} object through another {@link Client} object parameter
   *
   * @param client the {@link Client} modified object
   */
  public void update(final Client client) {
    this.setCpf(client.getCpf());
    this.setName(client.getName());
    this.setAddress(new Address());
    this.getAddress().update(client.getAddress());
    this.setBirthDate(client.getBirthDate());
  }

  /**
   * Updates this {@link Client} object through another {@link Client} object parameter
   *
   * @param client the {@link Client} modified object
   */
  public void updateNotNullAttributes(final Client client) {

    this.setCpf(client.getCpf() != null ? client.getCpf() : this.getCpf());
    this.setName(client.getName() != null ? client.getName() : this.getName());
    this.setBirthDate(client.getBirthDate() != null ? client.getBirthDate() : this.getBirthDate());
    if (client.getAddress() != null && !this.getAddress().equals(client.getAddress())) {
      this.setAddress(new Address());
      this.getAddress().updateNotNullAttributes(client.getAddress());
    }

  }

  @PrePersist
  private void onCreation() {
    this.setCreated(LocalDateTime.now());
    this.setUpdated(LocalDateTime.now());
  }

  @PreUpdate
  private void onUpdate() {
    this.setUpdated(LocalDateTime.now());
  }

}
