package org.alibou.demo.content;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("FILE")
@EqualsAndHashCode(callSuper = true)
public class File extends Content {
  private String filePath;

}
