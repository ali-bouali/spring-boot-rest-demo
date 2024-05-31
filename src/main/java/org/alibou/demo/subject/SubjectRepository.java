package org.alibou.demo.subject;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
  //@Query("SELECT s FROM Subject s WHERE s.id IN :ids AND s.capacity > 0")
 // Set<Subject> findAllByIdAndCapacityGreaterThanZero(@Param("ids") Set<Integer> ids);
}


