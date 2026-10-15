package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/** Étudiant : la date est persistée comme DATE et échangée au format ISO. */
@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Column(nullable = false)
  private String nom;

  @NotBlank
  @Column(nullable = false)
  private String prenom;

  @Temporal(TemporalType.DATE)
  @NotNull
  @Past
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
  private Date dateNaissance;

  public Student() {}

  public Student(String nom, String prenom, Date date) {
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = date;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public Date getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(Date date) {
    dateNaissance = date;
  }
}
