package com.alison.clientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Data;

/**
 * {@link Address} represents the Address Entity
 *
 * @author Alison Augusto Miranda Pereira
 */

@Data
@Entity
@Table(name = "client_address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "street")
  private String street;

  @Column(name = "street_number")
  private Integer streetNumber;

  @Column(name = "city", length = 100)
  private String city;

  @Column(name = "neighborhood", length = 100)
  private String neighborhood;

  @Column(name = "zip_code")
  private Integer zipCode;

  @Column(name = "country", length = 100)
  private String country;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "created")
  private LocalDateTime created;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "updated")
  private LocalDateTime updated;

  /**
   * Updates this {@link Address} object through another {@link Address} object parameter
   *
   * @param address the {@link Client} modified object
   */
  public void updateNotNullAttributes(final Address address) {

    if (address != null) {
      this.setZipCode(address.getZipCode() != null ? address.getZipCode()
          : this.getZipCode());
      this.setStreet(address.getStreet() != null ? address.getStreet()
          : this.getStreet());
      this.setStreetNumber(address.getStreetNumber() != null ? address.getStreetNumber()
          : this.getStreetNumber());
      this.setNeighborhood(address.getNeighborhood() != null ? address.getNeighborhood()
          : this.getNeighborhood());
      this.setCountry(address.getCountry() != null ? address.getCountry()
          : this.getCountry());
      this.setCity(address.getCity() != null ? address.getCity()
          : this.getCity());
    }

  }

  /**
   * Updates this {@link Address} object through another {@link Address} object parameter
   *
   * @param address the {@link Address} modified object
   */
  public void update(final Address address) {
    this.setZipCode(address.getZipCode());
    this.setStreet(address.getStreet());
    this.setStreetNumber(address.getStreetNumber());
    this.setNeighborhood(address.getNeighborhood());
    this.setCountry(address.getCountry());
    this.setCity(address.getCity());
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
