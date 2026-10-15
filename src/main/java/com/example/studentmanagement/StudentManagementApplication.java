package com.example.studentmanagement;
import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;
/** Point d'entrée du service de gestion des étudiants. */
@SpringBootApplication public class StudentManagementApplication {
    static { java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC")); }
public static void main(String[] args){SpringApplication.run(StudentManagementApplication.class,args);}}
