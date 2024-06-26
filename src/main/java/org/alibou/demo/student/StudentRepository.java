package org.alibou.demo.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {


    @Query("update Student set firstname = concat(firstname, '-', :s)")
    @Modifying
    void updateAllStudents(@Param(value = "s") String suffix);

    // select * from  student where lower(firstname) like '%ali%'
    Optional<Student> findStudentByFirstnameContainingIgnoreCase(String s);


    List<Student> findBySubjectsTeachersId(Integer id);

    List<Student> findBySubjectsTeachersIdOrderByEmailDesc(Integer id);


    @Query(value = """
            select * from student s
            where s.firstname = :param
            """, nativeQuery = true)
    List<Student> findByComplexQuery();


    /**
     * use projection to customise the response objects
     * @return
     */
    @Query(value = """
            select
            s.firstname as studentFn,
            s.lastname as studentLn,
            sb.name as SubName,
            sb.description as SubDesc,
            t.firstname as teacherFn,
            t.lastname as teacherLn
            
            from Student s
            inner join Subject sb
            inner join Teacher t
            where s.firstname = :param
            """)
    List<StudentSubjectResponseProjection> findByProjection(String param);



    /*----------------------------------*/


    Page<Student> findAlByCreatedAt(LocalDateTime ca, Pageable pageable);

}
