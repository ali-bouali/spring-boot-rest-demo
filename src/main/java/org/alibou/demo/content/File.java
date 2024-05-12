package org.alibou.demo.content;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
// @DiscriminatorValue("F")
public class File extends Content {

    private String fileUrl;
    // 12 attr
}
