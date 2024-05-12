package org.alibou.demo.content;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
// @DiscriminatorValue("T")
public class Text extends Content {

    private String text;
    // 20 attr
}
