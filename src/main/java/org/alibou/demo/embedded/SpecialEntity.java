package org.alibou.demo.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SpecialEntity {

    @EmbeddedId
    private ComposedId id;

    private String attr;
}
