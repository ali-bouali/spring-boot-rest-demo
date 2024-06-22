package org.alibou.demo.user;

import java.util.Optional;
import org.alibou.demo.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>,
    JpaSpecificationExecutor<User> {
  Optional<User > findUserByEmail(String email);
}
