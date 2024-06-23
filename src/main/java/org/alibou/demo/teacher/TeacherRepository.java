package org.alibou.demo.teacher;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>,
    JpaSpecificationExecutor<Teacher> {
 Optional<Teacher> findByEmail(String email );
 Optional<Teacher> findByEmailAndIdNot(String email, Integer id);

}
