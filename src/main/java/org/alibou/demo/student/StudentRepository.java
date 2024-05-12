package org.alibou.demo.student;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

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
  List<Student> findByUsernameAndSubjectsNameOrderByLastnameDesc(String username, String subjectsName);
  @Query("SELECT o FROM Order o WHERE o.status = :status AND o.customer.name = :name ORDER BY o.date DESC")
  List<Order> findOrdersByStatusAndCustomerName(@Param("status") String status, @Param("name") String customerName);
}
