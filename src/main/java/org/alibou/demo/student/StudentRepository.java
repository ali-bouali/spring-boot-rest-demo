package org.alibou.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {


    @Query("update Student set firstname = concat(firstname, '-', :s)")
    @Modifying
    void updateAllStudents(@Param(value = "s") String suffix);

    // select * from  student where lower(firstname) like '%ali%'
    Optional<Student> findStudentByFirstnameContainingIgnoreCase(String s);


    List<Student> findBySubjectsTeachersId(Integer id);

    List<Student> findBySubjectsTeachersIdOrderByEmailDesc(Integer id);
}
