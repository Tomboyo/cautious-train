package com.github.tomboyo.cautioustrain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Profile {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  public Long getId() {
    return id;
  }

  public Profile id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Profile name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Profile profile = (Profile) o;
    return Objects.equals(id, profile.id) && Objects.equals(name, profile.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
