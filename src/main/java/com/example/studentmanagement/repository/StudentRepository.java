package com.example.studentmanagement.repository;
import com.example.studentmanagement.entity.Student;import org.springframework.data.jpa.repository.*;import org.springframework.stereotype.Repository;import java.util.Collection;
/** Le comptage par année est exécuté dans MySQL via JPQL. */
@Repository public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findById(int id);
    @Query("select year(s.dateNaissance),count(s) from Student s group by year(s.dateNaissance) order by year(s.dateNaissance)") Collection<Object[]> findNbrStudentByYear();
}
