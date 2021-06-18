package com.github.tomboyo.cautioustrain.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Profile {
  @Id private final UUID id;

  @Column(name = "name")
  private String name;

  public Profile() {
    id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
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
    return Objects.equals(id, profile.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
