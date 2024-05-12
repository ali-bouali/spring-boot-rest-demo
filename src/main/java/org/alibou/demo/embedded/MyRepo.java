package org.alibou.demo.embedded;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepo extends JpaRepository<SpecialEntity, ComposedId> {

    SpecialEntity findMyObjectById(ComposedId id);
}
