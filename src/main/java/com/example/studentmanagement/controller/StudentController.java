package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.*;
import io.swagger.v3.oas.annotations.Operation;

/** Endpoints et statuts HTTP demandés dans le TP. */
@RestController
@RequestMapping("/students")
public class StudentController {
  private final StudentService service;

  public StudentController(StudentService service) {
    this.service = service;
  }

  @Operation(summary = "Ajouter ou enregistrer un étudiant")
  @PostMapping("/save")
  public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(student));
  }

  @Operation(summary = "Supprimer un étudiant")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    return service.delete(id)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Lister tous les étudiants")
  @GetMapping("/all")
  public ResponseEntity<List<Student>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @Operation(summary = "Compter les étudiants")
  @GetMapping("/count")
  public ResponseEntity<Long> countStudent() {
    return ResponseEntity.ok(service.countStudents());
  }

  @Operation(summary = "Compter par année de naissance")
  @GetMapping("/byYear")
  public ResponseEntity<Collection<?>> findByYear() {
    return ResponseEntity.ok(service.findNbrStudentByYear());
  }
}
