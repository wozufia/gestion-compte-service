package com.awa.centrale.gestioncompte.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String email;

  @Column(name = "mot_de_passe")
  private String motDePasse;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "utilisateur_role",
          joinColumns = @JoinColumn(name = "utilisateur_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Role> roles;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "compte_id")
  private Compte compte;

  private Boolean active;
}
