package org.alibou.demo.student;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@EnableJpaAuditing
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> ,
    JpaSpecificationExecutor<Student> {

  //1. Find by a Single Property
  Optional<Student> findByUsernameIgnoreCase(String username);

  Optional<Student> findByUsernameAndEmail(String username, String email);

  //2. Find by Multiple Properties
  Optional<Student> findByLastnameAndFirstname(String lastname, String firstname);

  //3. Find by a Nested Property
  List<Student> findBySubjectsName(String name);

  //4. Find by a Property with Specific Matching
  List<Student> findByEmailContainingIgnoreCase(String expression);

  // 6. Find First or Top N Elements
  Student findFirstByOrderByUsernameAsc();

  List<Student> findTop3ByEmailOrderByUsernameDesc(String email);

  //7. Find by Status and Sorted by a Nested Property
  List<Student> findByUsernameAndSubjectsNameOrderByLastnameDesc(String username,
      String subjectsName);
//  @Query("SELECT o FROM Student o WHERE o.firstname = :firstname AND o.email = :email ORDER BY o.lastname DESC")
//  List<Order> findOrdersByStatusAndCustomerName(@Param("firstname") String firstname, @Param("email") String email);
@Query(value = """
    select
        s.firstname as studentFn,
        s.lastname as studentLn,
        sb.name as subName,
        sb.description as subDesc,
        t.firstname as teacherFn,
        t.lastname as teacherLn
    from Student s
    inner join s.subjects sb
    inner join sb.teachers t
    where s.firstname = :param
    """)
  List<StudentSubjectResponseProjection> findByComplexQuery(String param);

  @Query("update Student set firstname = concat(firstname, '-', :s)")
  @Modifying
  void updateAllStudents(@Param(value = "s") String suffix);
  Optional<Student> findTop1StudentByFirstnameContainingIgnoreCase(String s);
  List<Student> findStudentByLastnameContainingIgnoreCase(String lastname);
//  @Query(value = """
//        select s from Student s
//        inner join  s.subjects subj
//        inner join subj.teachers t
//        where t.id = :teacherId
//        """)
  List<Student> findBySubjectsTeachersId(@Param("teacherId") Integer teacherId);}//ask ali
