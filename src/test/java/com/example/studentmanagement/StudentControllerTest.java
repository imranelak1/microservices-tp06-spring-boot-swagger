package com.example.studentmanagement;
import com.example.studentmanagement.controller.StudentController;import com.example.studentmanagement.service.StudentService;import com.example.studentmanagement.entity.Student;
import org.junit.jupiter.api.Test;import org.junit.jupiter.api.extension.ExtendWith;import org.mockito.*;import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;
/** Tests unitaires isolés, sans démarrer la base. */
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Mock StudentService service;@InjectMocks StudentController controller;
    @Test void save(){Student s=new Student();s.setNom("Mido");when(service.save(s)).thenReturn(s);var response=controller.save(s);assertEquals(201,response.getStatusCode().value());assertEquals("Mido",response.getBody().getNom());}
    @Test void delete(){when(service.delete(1)).thenReturn(true);assertEquals(204,controller.delete(1).getStatusCode().value());assertEquals(404,controller.delete(2).getStatusCode().value());}
    @Test void findAll(){when(service.findAll()).thenReturn(List.of(new Student(),new Student()));assertEquals(2,controller.findAll().getBody().size());}
    @Test void count(){when(service.countStudents()).thenReturn(10L);assertEquals(10L,controller.countStudent().getBody());}
    @Test void byYear(){when(service.findNbrStudentByYear()).thenReturn(List.of());assertTrue(controller.findByYear().getBody().isEmpty());}
}
